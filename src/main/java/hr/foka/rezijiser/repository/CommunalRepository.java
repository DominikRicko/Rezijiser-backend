package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.domain.Communal;

@Repository
public interface CommunalRepository extends CrudRepository<Communal, Integer> {
    
}
