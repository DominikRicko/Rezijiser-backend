package hr.foka.rezijiser.api.notification.service;

import org.springframework.http.ResponseEntity;

import hr.foka.rezijiser.api.notification.resource.NotificationRequest;
import hr.foka.rezijiser.persistence.domain.User;

public interface NotificationService {

    ResponseEntity<?> getNotifications(User user);

    ResponseEntity<?> getNotifications(User user, NotificationRequest request);

    ResponseEntity<?> checkNotification(User user, Integer id, Boolean check);

}