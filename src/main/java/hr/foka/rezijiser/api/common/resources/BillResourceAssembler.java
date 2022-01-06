package hr.foka.rezijiser.api.common.resources;

import java.math.BigDecimal;

import hr.foka.rezijiser.api.common.converters.LocalDateConverter;
import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.User;
import hr.foka.rezijiser.persistence.domain.Bill.Type;

public abstract class BillResourceAssembler<T extends CommonResource> extends GenericResourceAssembler<Bill, T> {

    protected BillResourceAssembler(LocalDateConverter dateConverter) {
        super(dateConverter);
    }
    
    @Override
    public T toResource(Bill entity) {
        T resource = getResource();

        resource.setId(entity.getId());
        resource.setCost(entity.getCost().toString());

        if (entity.getDatePaid() != null) {
            resource.setDatePaid(entity.getDatePaid());
        } else {
            resource.setDatePaid(null);
        }

        resource.setPayday(entity.getPayday());

        return resource;
    }

    @Override
    public Bill toEntity(User user, T resource) {

        Bill entity = new Bill();

        entity.setId(resource.getId());
        entity.setCost(new BigDecimal(resource.getCost()));
        entity.setType(getType());

        if (resource.getDatePaid() != null) {
            entity.setDatePaid(resource.getDatePaid());
        }
        entity.setPayday(resource.getPayday());
        entity.setUser(user);

        return entity;

    }

    protected abstract Type getType();
    protected abstract T getResource();

}
