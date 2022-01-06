package hr.foka.rezijiser.api.trash.resource;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.converters.LocalDateConverter;
import hr.foka.rezijiser.api.common.resources.BillResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Bill.Type;

@Service
public class TrashResourceAssembler extends BillResourceAssembler<TrashResource> {

    public TrashResourceAssembler(LocalDateConverter dateConverter) {
        super(dateConverter);
    }

    @Override
    protected Type getType() {
        return Type.TRASH;
    }

    @Override
    protected TrashResource getResource() {
        return new TrashResource();
    }

}