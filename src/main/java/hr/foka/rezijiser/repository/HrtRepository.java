package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;

import hr.foka.rezijiser.domain.Hrt;

public interface HrtRepository extends CrudRepository<Hrt, Long> {
    
}
