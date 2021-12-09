package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    
}
