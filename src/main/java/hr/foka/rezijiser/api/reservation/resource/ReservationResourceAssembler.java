package hr.foka.rezijiser.api.reservation.resource;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.converters.LocalDateConverter;
import hr.foka.rezijiser.api.common.resources.BillResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Bill.Type;

@Service
public class ReservationResourceAssembler extends BillResourceAssembler<ReservationResource> {

    public ReservationResourceAssembler(LocalDateConverter dateConverter) {
        super(dateConverter);
    }

    @Override
    protected Type getType() {
        return Type.RESERVATION;
    }

    @Override
    protected ReservationResource getResource() {
        return new ReservationResource();
    }

}