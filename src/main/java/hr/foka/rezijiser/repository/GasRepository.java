package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.domain.Gas;

@Repository
public interface GasRepository extends CrudRepository<Gas, Integer> {
    
}
