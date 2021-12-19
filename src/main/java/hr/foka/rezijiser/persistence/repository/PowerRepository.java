package hr.foka.rezijiser.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.persistence.domain.Power;
import hr.foka.rezijiser.persistence.domain.User;

@Repository
public interface PowerRepository extends PagingAndSortingRepository<Power, Integer> {

    List<Power> findByUser(User user);

    Page<Power> findAllByUser(User user, Pageable pageable);

}