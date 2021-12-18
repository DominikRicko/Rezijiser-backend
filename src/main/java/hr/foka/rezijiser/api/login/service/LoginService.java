package hr.foka.rezijiser.api.login.service;

import org.springframework.http.ResponseEntity;

import hr.foka.rezijiser.api.login.resource.LoginResource;

public interface LoginService {

    ResponseEntity<?> authenticate(LoginResource resource);

}