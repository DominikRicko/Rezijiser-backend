package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;

import hr.foka.rezijiser.domain.Gas;

public interface GasRepository extends CrudRepository<Gas, Long> {
    
}
