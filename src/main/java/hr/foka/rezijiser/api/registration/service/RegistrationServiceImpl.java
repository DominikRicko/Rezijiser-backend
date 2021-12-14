package hr.foka.rezijiser.api.registration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.registration.resource.RegistrationResource;
import hr.foka.rezijiser.persistence.domain.User;
import hr.foka.rezijiser.persistence.repository.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<?> registerUser(RegistrationResource userInfo) {

        LOGGER.debug("Attempting to register new user with info: ", userInfo);

        if(checkIfEmailExists(userInfo.getEmail())){
            LOGGER.info("Email {} is already in use.", userInfo.getEmail());
            return new ResponseEntity<>("Email is already in use.", HttpStatus.CONFLICT);
        }

        User user = createUser(userInfo);
        user = userRepository.save(user);

        LOGGER.debug("User successfully registered.");
        return new ResponseEntity<>("Registration successful", HttpStatus.OK);
    }

    private boolean checkIfEmailExists(String email){
        return userRepository.existsByEmail(email);
    }

    private User createUser(RegistrationResource resource){
        User user = new User();
        user.setEmail(resource.getEmail());
        user.setName(resource.getName());
        user.setSurname(resource.getSurname());
        user.setPassword(passwordEncoder.encode(resource.getRawPassword()));

        user.setEnabled(true);

        return user;
    }    
    
}
