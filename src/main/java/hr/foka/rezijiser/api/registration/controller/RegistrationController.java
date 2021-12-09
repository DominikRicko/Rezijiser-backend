package hr.foka.rezijiser.api.registration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.foka.rezijiser.api.registration.resource.RegistrationResource;
import hr.foka.rezijiser.api.registration.service.RegistrationService;

@RestController
@RequestMapping(path = "/register")
public class RegistrationController {
        
    private static Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registerAccount(@RequestBody RegistrationResource resource){
        LOGGER.debug("Getting registration request.");
        return registrationService.registerUser(resource);
    }

}
