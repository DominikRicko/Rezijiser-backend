package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;

import hr.foka.rezijiser.domain.Trash;

public interface TrashRepository extends CrudRepository<Trash, Long> {
    
}
