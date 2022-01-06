package hr.foka.rezijiser.api.water.resource;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.converters.LocalDateConverter;
import hr.foka.rezijiser.api.common.resources.BillResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.User;
import hr.foka.rezijiser.persistence.domain.Bill.Type;

@Service
public class WaterResourceAssembler extends BillResourceAssembler<WaterResource> {

    public WaterResourceAssembler(LocalDateConverter dateConverter) {
        super(dateConverter);
    }

    @Override
    public WaterResource toResource(Bill entity) {
        WaterResource resource = super.toResource(entity);
        resource.setCounter(entity.getSpent().toString());
        return resource;
    }

    @Override
    public Bill toEntity(User user, WaterResource resource) {
        Bill entity = super.toEntity(user, resource);
        entity.setSpent(new BigDecimal(resource.getCounter()));
        return entity;
    }

    @Override
    protected Type getType() {
        return Type.WATER;
    }

    @Override
    protected WaterResource getResource() {
        return new WaterResource();
    }

}