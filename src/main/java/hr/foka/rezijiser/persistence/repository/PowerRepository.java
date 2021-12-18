package hr.foka.rezijiser.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.persistence.domain.Power;
import hr.foka.rezijiser.persistence.domain.User;

@Repository
public interface PowerRepository extends CrudRepository<Power, Integer> {

    List<Power> findByUser(User user);

}