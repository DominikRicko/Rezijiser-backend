package hr.foka.rezijiser.services.notifications;

import java.time.LocalDate;
import java.util.stream.StreamSupport;

import com.querydsl.core.types.dsl.BooleanExpression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import hr.foka.rezijiser.ApplicationProperties;
import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.Notification;
import hr.foka.rezijiser.persistence.domain.User;
import hr.foka.rezijiser.persistence.domain.Notification.Level;
import hr.foka.rezijiser.persistence.repository.BillRepository;
import hr.foka.rezijiser.persistence.repository.NotificationRepository;
import hr.foka.rezijiser.persistence.repository.UserRepository;
import hr.foka.rezijiser.persistence.service.BillFilteringService;
import hr.foka.rezijiser.persistence.service.UserFilteringService;

@Service
@EnableAsync
public class NotificationGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationGenerator.class);
    
    private final UserRepository userRepository;
    private final BillRepository billRepository;
    private final NotificationRepository notificationRepository;
    private final BillFilteringService billFilteringService;
    private final UserFilteringService userFilteringService;
    private final ApplicationProperties applicationProperties;

    public NotificationGenerator(
        UserRepository userRepository,
        BillRepository billRepository,
        NotificationRepository notificationRepository,
        BillFilteringService billFilteringService,
        UserFilteringService userFilteringService,
        ApplicationProperties applicationProperties
    ){
        this.userRepository = userRepository;
        this.billRepository = billRepository;
        this.notificationRepository = notificationRepository;
        this.billFilteringService = billFilteringService;
        this.userFilteringService = userFilteringService;
        this.applicationProperties = applicationProperties;
        LOGGER.info("Notification generator initialized.");
    }

    @Scheduled(cron = "0/30 * * * * ?")
    @Async
    public void generateNotification(){
        LOGGER.info("Generating new notifications for missed payments.");

        StreamSupport.stream(userRepository.findAll().spliterator(), true).forEach(this::generateNotificationForUser);

        LOGGER.info("Missed payment notifications generated.");
    }

    @Async
    public void generateNotificationForUser(User user){

        BooleanExpression filter = userFilteringService.filterForUser(user)
            .and(billFilteringService.filterByPaydayBetween(LocalDate.MIN, LocalDate.now().minusDays(1)))
            .and(billFilteringService.filterByIsPaid().not());

        StreamSupport.stream(billRepository.findAll(filter).spliterator(), true).forEach(this::generateMissedPaydayNotification);

        filter = userFilteringService.filterForUser(user)
            .and(billFilteringService.filterByPaydayBetween(LocalDate.now(), LocalDate.now().plusDays(applicationProperties.getReminderDays())))
            .and(billFilteringService.filterByIsPaid().not());

        StreamSupport.stream(billRepository.findAll(filter).spliterator(), true).forEach(this::generateReminderNotification);
    }

    @Async
    public void generateMissedPaydayNotification(Bill bill){
        
        String message = applicationProperties.getLateMessageTemplate();
        String title = applicationProperties.getLateTitle();

        message = String.format(message, bill.getType().getCroatianName(), bill.getPayday().toString());

        createNotification(bill, title, message, Level.CRITICAL);

    }

    @Async
    public void generateReminderNotification(Bill bill){

        String message = applicationProperties.getReminderMessageTemplate();
        String title = applicationProperties.getReminderTitle();

        message = String.format(message, bill.getType().getCroatianName(), bill.getPayday().toString());

        createNotification(bill, title, message, Level.WARNING);

    }

    private Notification createNotification(Bill bill, String title, String message, Notification.Level level){
        Notification entity = new Notification();

        entity.setUser(bill.getUser());
        entity.setTitle(title);
        entity.setMessage(message);
        entity.setLevel(level);
        entity.setChecked(false);

        return notificationRepository.save(entity);
    }

}
