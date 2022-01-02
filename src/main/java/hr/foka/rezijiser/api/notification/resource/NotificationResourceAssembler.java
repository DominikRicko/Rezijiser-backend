package hr.foka.rezijiser.api.notification.resource;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.persistence.domain.Notification;

@Service
public class NotificationResourceAssembler {
    
    public NotificationResource toResource(Notification entity){

        NotificationResource resource = new NotificationResource();

        resource.setId(entity.getId());
        resource.setLevel(entity.getLevel());
        resource.setTitle(entity.getTitle());
        resource.setMessage(entity.getMessage());
        resource.setTimeCreated(entity.getTimeCreated());
        resource.setChecked(entity.isChecked());
        
        return resource;

    }

    public Collection<NotificationResource> toResources(Collection<Notification> entities){
        return entities.stream().map(this::toResource).collect(Collectors.toList());
    }

}
