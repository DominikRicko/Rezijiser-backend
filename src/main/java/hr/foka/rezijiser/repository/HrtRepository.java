package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.domain.Hrt;

@Repository
public interface HrtRepository extends CrudRepository<Hrt, Integer> {
    
}
