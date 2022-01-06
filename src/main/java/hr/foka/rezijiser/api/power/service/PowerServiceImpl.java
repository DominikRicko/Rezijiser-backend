package hr.foka.rezijiser.api.power.service;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.service.AbstractCommonBillResourceService;
import hr.foka.rezijiser.api.power.resource.PowerResource;
import hr.foka.rezijiser.api.power.resource.PowerResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.Bill.Type;
import hr.foka.rezijiser.persistence.repository.BillRepository;
import hr.foka.rezijiser.persistence.service.BillFilteringService;
import hr.foka.rezijiser.persistence.service.UserFilteringService;

@Service
public class PowerServiceImpl extends AbstractCommonBillResourceService<PowerResource> implements PowerService {

    public PowerServiceImpl(
        PowerResourceAssembler assembler, 
        BillRepository repository, 
        BillFilteringService filteringService,
        UserFilteringService userFilteringService) {
            super(assembler, repository, filteringService, userFilteringService);
    }

    @Override
    protected Type getType() {
        return Bill.Type.POWER;
    }

}