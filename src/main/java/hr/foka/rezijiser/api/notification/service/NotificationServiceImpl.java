package hr.foka.rezijiser.api.notification.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.querydsl.core.types.dsl.BooleanExpression;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.notification.resource.NotificationRequest;
import hr.foka.rezijiser.api.notification.resource.NotificationResource;
import hr.foka.rezijiser.api.notification.resource.NotificationResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Notification;
import hr.foka.rezijiser.persistence.domain.User;
import hr.foka.rezijiser.persistence.repository.NotificationRepository;
import hr.foka.rezijiser.persistence.service.NotificationFilteringService;
import hr.foka.rezijiser.persistence.service.UserFilteringService;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationResourceAssembler assembler;
    private final NotificationRepository repository;
    private final NotificationFilteringService notificationFilteringService;
    private final UserFilteringService userFilteringService;

    public NotificationServiceImpl(
        NotificationResourceAssembler assembler, 
        NotificationRepository repository,
        NotificationFilteringService notificationFilteringService,
        UserFilteringService userFilteringService) {
        this.assembler = assembler;
        this.repository = repository;
        this.notificationFilteringService = notificationFilteringService;
        this.userFilteringService = userFilteringService;
    }

    @Override
    public ResponseEntity<?> getNotifications(User user) {
        return new ResponseEntity<>(assembler.toResources(repository.findByUser(user)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getNotifications(User user, NotificationRequest request) {
        Pageable pageable;
        if(request.getSortDirection() != null){
            pageable = PageRequest.of(request.getPageNumber(), request.getPageSize(), request.getSortDirection(), request.getSortBy().getColumnName());
        } else {
            pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
        }

        BooleanExpression filter = notificationFilteringService.processFilters(userFilteringService.filterForUser(user), request.getFilters());

        Page<Notification> page = repository.findAll(filter, pageable);
        Page<NotificationResource> resource = page.map(it -> assembler.toResource(it));
        return new ResponseEntity<>(resource, HttpStatus.OK);
        
    }

    @Override
    public ResponseEntity<?> checkNotification(User user, Integer id, Boolean check) {
        Optional<Notification> notificationOptional = repository.findById(id);

        if (notificationOptional.isEmpty()) {
            throw new EntityNotFoundException("Notification with id " + id + " not found.");
        }

        Notification notification = notificationOptional.get();

        if (notification.getUser() != user) {
            throw new RuntimeException("Notification does not belong to user " + user.getEmail());
        }

        notification.setChecked(check);

        return new ResponseEntity<>(assembler.toResource(repository.save(notification)), HttpStatus.OK);

    }

}