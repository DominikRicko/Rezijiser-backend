package hr.foka.rezijiser.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.foka.rezijiser.persistence.domain.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

}