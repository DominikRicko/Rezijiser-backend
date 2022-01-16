package hr.foka.rezijiser.api.water.service;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.resources.MessageResourceAssembler;
import hr.foka.rezijiser.api.common.service.AbstractCommonBillResourceService;
import hr.foka.rezijiser.api.water.resource.WaterResource;
import hr.foka.rezijiser.api.water.resource.WaterResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.Bill.Type;
import hr.foka.rezijiser.persistence.repository.BillRepository;
import hr.foka.rezijiser.persistence.service.BillFilteringService;
import hr.foka.rezijiser.persistence.service.UserFilteringService;

@Service
public class WaterServiceImpl extends AbstractCommonBillResourceService<WaterResource> implements WaterService {

    public WaterServiceImpl(
        WaterResourceAssembler assembler, 
        BillRepository repository, 
        BillFilteringService filteringService,
        UserFilteringService userFilteringService,
        MessageResourceAssembler messageResourceAssembler) {
            super(assembler, repository, filteringService, userFilteringService, messageResourceAssembler);
    }

    @Override
    protected Type getType() {
        return Bill.Type.WATER;
    }

}