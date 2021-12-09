package hr.foka.rezijiser.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.domain.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}
