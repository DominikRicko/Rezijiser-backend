package hr.foka.rezijiser.api.power.resource;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.converters.LocalDateConverter;
import hr.foka.rezijiser.api.common.resources.BillResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.User;
import hr.foka.rezijiser.persistence.domain.Bill.Type;

@Service
public class PowerResourceAssembler extends BillResourceAssembler<PowerResource> {

    public PowerResourceAssembler(LocalDateConverter dateConverter) {
        super(dateConverter);
    }

    @Override
    public PowerResource toResource(Bill entity) {
        PowerResource resource = super.toResource(entity);
        resource.setCounter(entity.getSpent().toString());
        return resource;
    }

    @Override
    public Bill toEntity(User user, PowerResource resource) {
        Bill entity = super.toEntity(user, resource);
        entity.setSpent(new BigDecimal(resource.getCounter()));
        return entity;
    }

    @Override
    protected Type getType() {
        return Type.POWER;
    }

    @Override
    protected PowerResource getResource() {
        return new PowerResource();
    }

}