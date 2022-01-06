package hr.foka.rezijiser.api.telecommunication.resource;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.converters.LocalDateConverter;
import hr.foka.rezijiser.api.common.resources.BillResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Bill.Type;

@Service
public class TelecommunicationResourceAssembler extends BillResourceAssembler<TelecommunicationResource> {

    public TelecommunicationResourceAssembler(LocalDateConverter dateConverter) {
        super(dateConverter);
    }

    @Override
    protected Type getType() {
        return Type.TELECOMMUNICATION;
    }

    @Override
    protected TelecommunicationResource getResource() {
        return new TelecommunicationResource();
    }

}