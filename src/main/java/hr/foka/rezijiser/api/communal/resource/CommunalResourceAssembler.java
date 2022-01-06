package hr.foka.rezijiser.api.communal.resource;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.converters.LocalDateConverter;
import hr.foka.rezijiser.api.common.resources.BillResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Bill.Type;

@Service
public class CommunalResourceAssembler extends BillResourceAssembler<CommunalResource> {

    public CommunalResourceAssembler(LocalDateConverter dateConverter) {
        super(dateConverter);
    }

    @Override
    protected Type getType() {
        return Type.COMMUNAL;
    }

    @Override
    protected CommunalResource getResource() {
        return new CommunalResource();
    }

}