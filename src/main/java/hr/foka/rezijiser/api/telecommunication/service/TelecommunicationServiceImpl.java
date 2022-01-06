package hr.foka.rezijiser.api.telecommunication.service;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.service.AbstractCommonBillResourceService;
import hr.foka.rezijiser.api.telecommunication.resource.TelecommunicationResource;
import hr.foka.rezijiser.api.telecommunication.resource.TelecommunicationResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.Bill.Type;
import hr.foka.rezijiser.persistence.repository.BillRepository;
import hr.foka.rezijiser.persistence.service.BillFilteringService;
import hr.foka.rezijiser.persistence.service.UserFilteringService;

@Service
public class TelecommunicationServiceImpl extends AbstractCommonBillResourceService<TelecommunicationResource> implements TelecommunicationService {

    public TelecommunicationServiceImpl(
        TelecommunicationResourceAssembler assembler, 
        BillRepository repository, 
        BillFilteringService filteringService,
        UserFilteringService userFilteringService) {
            super(assembler, repository, filteringService, userFilteringService);
    }

    @Override
    protected Type getType() {
        return Bill.Type.TELECOMMUNICATION;
    }

}