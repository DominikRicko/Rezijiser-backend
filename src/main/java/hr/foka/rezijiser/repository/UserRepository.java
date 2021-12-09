package hr.foka.rezijiser.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}
