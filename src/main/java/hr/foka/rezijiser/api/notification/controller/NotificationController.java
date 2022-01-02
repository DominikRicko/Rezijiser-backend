package hr.foka.rezijiser.api.notification.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hr.foka.rezijiser.api.notification.resource.NotificationRequest;
import hr.foka.rezijiser.api.notification.service.NotificationService;
import hr.foka.rezijiser.persistence.domain.User;

@Controller
@RequestMapping(path = "/e/api/v1/notification")
public class NotificationController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);
    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getNotifications(@AuthenticationPrincipal User user) {
        LOGGER.debug("Received GET request for notifications");
        return service.getNotifications(user);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/paged")
    public ResponseEntity<?> getNotifications(@AuthenticationPrincipal User user, @RequestBody NotificationRequest page){
        LOGGER.debug("Received POST request to fetch paged notifications");
        return service.getNotifications(user, page);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/check/{id:.+}")
    public ResponseEntity<?> checkNotification(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        LOGGER.debug("Received GET request to check notification with id {}", id);
        return service.checkNotification(user, id, true);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/uncheck/{id:.+}")
    public ResponseEntity<?> uncheckNotification(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        LOGGER.debug("Received GET request to uncheck notification with id {}", id);
        return service.checkNotification(user, id, true);
    }

}
