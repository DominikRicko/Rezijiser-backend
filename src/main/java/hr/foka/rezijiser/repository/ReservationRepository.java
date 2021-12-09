package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.domain.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    
}
