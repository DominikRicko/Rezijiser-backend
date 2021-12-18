package hr.foka.rezijiser.api.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.foka.rezijiser.api.login.resource.LoginResource;
import hr.foka.rezijiser.api.login.service.LoginService;

@RestController
@RequestMapping(path = "/login")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private final LoginService service;

    public LoginController(LoginService service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody @Validated LoginResource request) {
        LOGGER.debug("Received GET request for logging in.");
        return service.authenticate(request);
    }

}