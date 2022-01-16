package hr.foka.rezijiser.api.reservation.service;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.resources.MessageResourceAssembler;
import hr.foka.rezijiser.api.common.service.AbstractCommonBillResourceService;
import hr.foka.rezijiser.api.reservation.resource.ReservationResource;
import hr.foka.rezijiser.api.reservation.resource.ReservationResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.Bill.Type;
import hr.foka.rezijiser.persistence.repository.BillRepository;
import hr.foka.rezijiser.persistence.service.BillFilteringService;
import hr.foka.rezijiser.persistence.service.UserFilteringService;

@Service
public class ReservationServiceImpl extends AbstractCommonBillResourceService<ReservationResource> implements ReservationService {

    public ReservationServiceImpl(
        ReservationResourceAssembler assembler, 
        BillRepository repository, 
        BillFilteringService filteringService,
        UserFilteringService userFilteringService,
        MessageResourceAssembler messageResourceAssembler) {
            super(assembler, repository, filteringService, userFilteringService, messageResourceAssembler);
    }

    @Override
    protected Type getType() {
        return Bill.Type.RESERVATION;
    }

}