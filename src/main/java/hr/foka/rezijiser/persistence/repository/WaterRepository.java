package hr.foka.rezijiser.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.persistence.domain.Water;

@Repository
public interface WaterRepository extends CrudRepository<Water, Integer> {
    
}
