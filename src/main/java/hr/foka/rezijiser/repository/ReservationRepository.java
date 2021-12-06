package hr.foka.rezijiser.repository;

import org.springframework.data.repository.CrudRepository;

import hr.foka.rezijiser.domain.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    
}
