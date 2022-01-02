package hr.foka.rezijiser.persistence.repository;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.persistence.domain.Notification;
import hr.foka.rezijiser.persistence.domain.User;

@Repository
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Integer>, QuerydslPredicateExecutor<Notification>{

    public List<Notification> findByUser(User user);

}
