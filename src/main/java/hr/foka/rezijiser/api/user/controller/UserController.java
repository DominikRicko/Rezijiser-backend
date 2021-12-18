package hr.foka.rezijiser.api.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.foka.rezijiser.api.user.service.UserService;
import hr.foka.rezijiser.persistence.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


@RestController
@RequestMapping(path = "/e/api/v1/users")
public class UserController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getMethodName() {
        LOGGER.info("Received Get request for Users.");
        return userService.getUsers();
    }
    
    @RequestMapping(path="/current", method = RequestMethod.GET)
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal User user){
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
