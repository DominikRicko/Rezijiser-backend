package hr.foka.rezijiser.api.user.resource;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.persistence.domain.User;

@Service
public class UserResourceAssembler {

    public UserResource toResource(User entity) {

        UserResource resource = new UserResource();

        resource.setEmail(entity.getEmail());
        resource.setName(entity.getName());
        resource.setSurname(entity.getSurname());
        resource.setTimeCreated(entity.getTimeCreated().toString());

        return resource;

    }

}