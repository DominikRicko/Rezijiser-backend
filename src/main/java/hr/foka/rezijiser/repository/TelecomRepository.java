package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;

import hr.foka.rezijiser.domain.Telecom;

public interface TelecomRepository extends CrudRepository<Telecom, Long> {
    
}
