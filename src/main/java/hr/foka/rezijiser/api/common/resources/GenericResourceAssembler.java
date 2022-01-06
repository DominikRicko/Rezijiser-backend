package hr.foka.rezijiser.api.common.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import hr.foka.rezijiser.api.common.converters.LocalDateConverter;
import hr.foka.rezijiser.persistence.domain.User;

public abstract class GenericResourceAssembler<ENTITY, RESOURCE> {

    protected final LocalDateConverter dateConverter;

    protected GenericResourceAssembler(LocalDateConverter dateConverter) {
        this.dateConverter = dateConverter;
    }

    public abstract RESOURCE toResource(ENTITY entity);

    public abstract ENTITY toEntity(User user, RESOURCE resource);

    public Collection<RESOURCE> toResources(Collection<ENTITY> entities) {
        return entities.stream().map(this::toResource).collect(Collectors.toList());
    }

    public Collection<ENTITY> toEntities(User user, Collection<RESOURCE> resources) {
        return resources.stream().map(it -> toEntity(user, it)).collect(Collectors.toList());
    }

    public Collection<RESOURCE> toResources(Iterable<ENTITY> entities){

        List<RESOURCE> resources = new ArrayList<>();

        for(ENTITY entity : entities){
            resources.add(toResource(entity));
        }

        return resources;
    }

}