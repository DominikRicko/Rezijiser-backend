package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.domain.Water;

@Repository
public interface WaterRepository extends CrudRepository<Water, Integer> {
    
}
