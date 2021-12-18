package hr.foka.rezijiser.api.registration.service;

import org.springframework.http.ResponseEntity;
import hr.foka.rezijiser.api.registration.resource.RegistrationResource;

public interface RegistrationService {

    public ResponseEntity<?> registerUser(RegistrationResource userInfo);

}