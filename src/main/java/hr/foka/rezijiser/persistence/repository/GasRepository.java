package hr.foka.rezijiser.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.persistence.domain.Gas;

@Repository
public interface GasRepository extends CrudRepository<Gas, Integer> {
    
}
