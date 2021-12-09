package hr.foka.rezijiser.api.user.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    
    public ResponseEntity<?> getUsers();

    public ResponseEntity<?> getUsers(Pageable pageable);

    public ResponseEntity<?> getUser(Integer userId);

}
