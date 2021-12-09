package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.domain.Power;

@Repository
public interface PowerRepository extends CrudRepository<Power, Integer> {
    
}
