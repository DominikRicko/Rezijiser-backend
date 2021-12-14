package hr.foka.rezijiser.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.persistence.domain.Power;

@Repository
public interface PowerRepository extends CrudRepository<Power, Integer> {
    
}
