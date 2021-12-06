package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;

import hr.foka.rezijiser.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
}
