package hr.foka.rezijiser.api.user.resource;

import hr.foka.rezijiser.domain.User;

public class UserResourceAssembler {
    
    public UserResource toResource(User entity){

        UserResource resource = new UserResource();

        resource.setEmail(entity.getEmail());
        resource.setName(entity.getName());
        resource.setSurname(entity.getSurname());
        resource.setTimeCreated(entity.getTimeCreated().toString());

        return resource;

    }

}
