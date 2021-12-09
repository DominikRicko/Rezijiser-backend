package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.domain.Telecom;

@Repository
public interface TelecomRepository extends CrudRepository<Telecom, Integer> {
    
}
