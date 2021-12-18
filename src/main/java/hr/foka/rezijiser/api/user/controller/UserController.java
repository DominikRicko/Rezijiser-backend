package hr.foka.rezijiser.api.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.foka.rezijiser.api.user.resource.UserResourceAssembler;
import hr.foka.rezijiser.persistence.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


@RestController
@RequestMapping(path = "/e/api/v1/user")
public class UserController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserResourceAssembler userAssembler;

    public UserController(UserResourceAssembler userAssembler){
        this.userAssembler = userAssembler;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getMethodName(@AuthenticationPrincipal User user) {
        LOGGER.info("Received GET request for User.");
        return new ResponseEntity<>(userAssembler.toResource(user), HttpStatus.OK);
    }

}
