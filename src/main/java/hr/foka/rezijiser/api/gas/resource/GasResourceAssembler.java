package hr.foka.rezijiser.api.gas.resource;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.converters.LocalDateConverter;
import hr.foka.rezijiser.api.common.resources.BillResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.User;
import hr.foka.rezijiser.persistence.domain.Bill.Type;

@Service
public class GasResourceAssembler extends BillResourceAssembler<GasResource> {

    public GasResourceAssembler(LocalDateConverter dateConverter) {
        super(dateConverter);
    }

    @Override
    public GasResource toResource(Bill entity) {
        GasResource resource = super.toResource(entity);
        resource.setCounter(entity.getSpent().toString());
        return resource;
    }

    @Override
    public Bill toEntity(User user, GasResource resource) {
        Bill entity = super.toEntity(user, resource);
        entity.setSpent(new BigDecimal(resource.getCounter()));
        return entity;
    }

    @Override
    protected Type getType() {
        return Type.GAS;
    }

    @Override
    protected GasResource getResource() {
        return new GasResource();
    }

}