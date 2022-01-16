package hr.foka.rezijiser.api.common.resources;

import org.springframework.stereotype.Service;

@Service
public class MessageResourceAssembler {
    
    public MessageResource assembleMessage(String message, MessageResource.Type type){
        MessageResource resource = new MessageResource();

        resource.setMessage(message);
        resource.setType(type);

        return resource;
    }

}
