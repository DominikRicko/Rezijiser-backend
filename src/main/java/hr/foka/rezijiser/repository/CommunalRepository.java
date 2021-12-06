package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;

import hr.foka.rezijiser.domain.Communal;

public interface CommunalRepository extends CrudRepository<Communal, Long> {
    
}
