package hr.foka.rezijiser.persistence.repository;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.User;

@Repository
public interface BillRepository extends PagingAndSortingRepository<Bill, Integer>, QuerydslPredicateExecutor<Bill> {

    List<Bill> findByUser(User user);

}