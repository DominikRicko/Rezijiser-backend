package hr.foka.rezijiser.api.power.resources;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.resources.GenericResourceAssembler;
import hr.foka.rezijiser.api.common.service.LocalDateParser;
import hr.foka.rezijiser.persistence.domain.Power;
import hr.foka.rezijiser.persistence.domain.User;

@Service
public class PowerResourceAssembler extends GenericResourceAssembler<Power, PowerResource> {

    public PowerResourceAssembler(LocalDateParser dateConverter) {
        super(dateConverter);
    }

    @Override
    public PowerResource toResource(Power entity) {
        PowerResource resource = new PowerResource();

        resource.setId(entity.getId());
        resource.setCost(entity.getCost().toString());
        resource.setCounter(entity.getCounter().toString());

        if (entity.getDatePaid() != null) {
            resource.setDatePaid(entity.getDatePaid().toString());
        } else {
            resource.setDatePaid(null);
        }

        resource.setPayday(entity.getPayday().toString());

        return resource;
    }

    @Override
    public Power toEntity(User user, PowerResource resource) {

        Power entity = new Power();

        entity.setId(resource.getId());
        entity.setCost(new BigDecimal(resource.getCost()));
        entity.setCounter(new BigDecimal(resource.getCounter()));

        if (resource.getDatePaid() != null) {
            entity.setDatePaid(dateConverter.parseString(resource.getDatePaid()));
        }
        entity.setPayday(dateConverter.parseString(resource.getPayday()));
        entity.setUser(user);

        return entity;

    }

}