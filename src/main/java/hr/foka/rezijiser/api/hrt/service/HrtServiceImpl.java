package hr.foka.rezijiser.api.hrt.service;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.service.AbstractCommonBillResourceService;
import hr.foka.rezijiser.api.hrt.resource.HrtResource;
import hr.foka.rezijiser.api.hrt.resource.HrtResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.Bill.Type;
import hr.foka.rezijiser.persistence.repository.BillRepository;
import hr.foka.rezijiser.persistence.service.BillFilteringService;
import hr.foka.rezijiser.persistence.service.UserFilteringService;

@Service
public class HrtServiceImpl extends AbstractCommonBillResourceService<HrtResource> implements HrtService {

    public HrtServiceImpl(
        HrtResourceAssembler assembler, 
        BillRepository repository, 
        BillFilteringService filteringService,
        UserFilteringService userFilteringService) {
            super(assembler, repository, filteringService, userFilteringService);
    }

    @Override
    protected Type getType() {
        return Bill.Type.HRT;
    }

}