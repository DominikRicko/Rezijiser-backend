package hr.foka.rezijiser.api.user.service;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import hr.foka.rezijiser.api.user.resource.UserResource;
import hr.foka.rezijiser.api.user.resource.UserResourceAssembler;
import hr.foka.rezijiser.domain.User;
import hr.foka.rezijiser.repository.UserRepository;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    private UserResourceAssembler resourceAssembler;

    public UserServiceImpl(UserRepository userRepository, UserResourceAssembler resourceAssembler) {
        this.userRepository = userRepository;
        this.resourceAssembler = resourceAssembler;
    }

    @Override
    public ResponseEntity<?> getUsers() {
        LOGGER.debug("Getting all users.");

        ArrayList<UserResource> users = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            users.add(resourceAssembler.toResource(user));
        }

        LOGGER.debug("Fetched {} users.", users.size());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUsers(Pageable pageable) {
        LOGGER.debug("Getting all users with pageable {}", pageable);

        Page<User> users = userRepository.findAll(pageable);
        Page<UserResource> resources = new PageImpl<UserResource>(users.stream().map(resourceAssembler::toResource).toList());

        LOGGER.debug("Fetched {} users.", users.getSize());
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUser(Integer userId) {
        LOGGER.debug("Getting user with id {}", userId);

        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return new ResponseEntity<>(resourceAssembler.toResource(user.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with given id is not found.", HttpStatus.NOT_FOUND);
        }

    }

}
