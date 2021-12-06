package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;

import hr.foka.rezijiser.domain.Water;

public interface WaterRepository extends CrudRepository<Water, Long> {
    
}
