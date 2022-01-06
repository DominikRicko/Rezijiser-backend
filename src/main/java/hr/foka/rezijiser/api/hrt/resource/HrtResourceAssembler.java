package hr.foka.rezijiser.api.hrt.resource;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.converters.LocalDateConverter;
import hr.foka.rezijiser.api.common.resources.BillResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Bill.Type;

@Service
public class HrtResourceAssembler extends BillResourceAssembler<HrtResource> {

    public HrtResourceAssembler(LocalDateConverter dateConverter) {
        super(dateConverter);
    }

    @Override
    protected Type getType() {
        return Type.HRT;
    }

    @Override
    protected HrtResource getResource() {
        return new HrtResource();
    }

}