package hr.foka.rezijiser.api.power.resources;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.converters.LocalDateConverter;
import hr.foka.rezijiser.api.common.resources.GenericResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.User;
import hr.foka.rezijiser.persistence.domain.Bill.Type;

@Service
public class PowerResourceAssembler extends GenericResourceAssembler<Bill, PowerResource> {

    public PowerResourceAssembler(LocalDateConverter dateConverter) {
        super(dateConverter);
    }

    @Override
    public PowerResource toResource(Bill entity) {
        PowerResource resource = new PowerResource();

        resource.setId(entity.getId());
        resource.setCost(entity.getCost().toString());
        resource.setCounter(entity.getSpent().toString());

        if (entity.getDatePaid() != null) {
            resource.setDatePaid(entity.getDatePaid());
        } else {
            resource.setDatePaid(null);
        }

        resource.setPayday(entity.getPayday());

        return resource;
    }

    @Override
    public Bill toEntity(User user, PowerResource resource) {

        Bill entity = new Bill();

        entity.setId(resource.getId());
        entity.setCost(new BigDecimal(resource.getCost()));
        entity.setSpent(new BigDecimal(resource.getCounter()));
        entity.setType(Type.POWER);

        if (resource.getDatePaid() != null) {
            entity.setDatePaid(resource.getDatePaid());
        }
        entity.setPayday(resource.getPayday());
        entity.setUser(user);

        return entity;

    }

}