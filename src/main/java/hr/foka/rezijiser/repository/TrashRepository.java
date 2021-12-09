package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.domain.Trash;

@Repository
public interface TrashRepository extends CrudRepository<Trash, Integer> {
    
}
