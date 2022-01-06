package hr.foka.rezijiser.api.gas.service;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.service.AbstractCommonBillResourceService;
import hr.foka.rezijiser.api.gas.resource.GasResource;
import hr.foka.rezijiser.api.gas.resource.GasResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.Bill.Type;
import hr.foka.rezijiser.persistence.repository.BillRepository;
import hr.foka.rezijiser.persistence.service.BillFilteringService;
import hr.foka.rezijiser.persistence.service.UserFilteringService;

@Service
public class GasServiceImpl extends AbstractCommonBillResourceService<GasResource> implements GasService {

    public GasServiceImpl(
        GasResourceAssembler assembler, 
        BillRepository repository, 
        BillFilteringService filteringService,
        UserFilteringService userFilteringService) {
            super(assembler, repository, filteringService, userFilteringService);
    }

    @Override
    protected Type getType() {
        return Bill.Type.GAS;
    }

}