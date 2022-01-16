package hr.foka.rezijiser.api.registration.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.exceptions.MissingDataException;
import hr.foka.rezijiser.api.common.resources.MessageResourceAssembler;
import hr.foka.rezijiser.api.common.resources.MessageResource.Type;
import hr.foka.rezijiser.api.registration.resource.RegistrationResource;
import hr.foka.rezijiser.persistence.domain.User;
import hr.foka.rezijiser.persistence.repository.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private MessageResourceAssembler messageAssembler;

    public RegistrationServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            MessageResourceAssembler messageAssembler) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.messageAssembler = messageAssembler;
    }

    @Override
    public ResponseEntity<?> registerUser(RegistrationResource userInfo) {

        LOGGER.debug("Attempting to register new user with info: ", userInfo);

        checkMissingData(userInfo);

        if (checkIfEmailExists(userInfo.getEmail())) {
            LOGGER.info("Email {} is already in use.", userInfo.getEmail());
            return new ResponseEntity<>(messageAssembler.assembleMessage("Email se već koristi", Type.ERROR), HttpStatus.CONFLICT);
        }

        User user = createUser(userInfo);
        user = userRepository.save(user);

        LOGGER.debug("User successfully registered.");
        return new ResponseEntity<>(messageAssembler.assembleMessage("Registracija uspješna.", Type.INFO), HttpStatus.OK);
    }

    private void checkMissingData(RegistrationResource resource){
        ArrayList<String> missingData = new ArrayList<>();

        if(resource.getEmail() == null || resource.getEmail().isEmpty()){
            missingData.add("email");
        }

        if(resource.getName() == null || resource.getName().isEmpty()){
            missingData.add("name");
        }

        if(resource.getSurname() == null || resource.getSurname().isEmpty()){
            missingData.add("surname");
        }

        if(resource.getRawPassword() == null || resource.getRawPassword().isEmpty()){
            missingData.add("password");
        }

        if(missingData.size() > 0)
            throw new MissingDataException(missingData.toArray(new String[0]));
    }

    private boolean checkIfEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    private User createUser(RegistrationResource resource) {
        User user = new User();
        user.setEmail(resource.getEmail());
        user.setName(resource.getName());
        user.setSurname(resource.getSurname());
        user.setPassword(passwordEncoder.encode(resource.getRawPassword()));

        user.setEnabled(true);

        return user;
    }

}