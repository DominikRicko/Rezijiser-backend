package hr.foka.rezijiser.api.common.service;

import org.springframework.http.ResponseEntity;

import hr.foka.rezijiser.api.common.resources.PageableResource;
import hr.foka.rezijiser.persistence.domain.User;

public interface CommonService<T> {

    ResponseEntity<?> getResources(User user);

    ResponseEntity<?> getResources(User user, PageableResource gridResource);

    ResponseEntity<?> saveResource(User user, T resource);

    ResponseEntity<?> updateResource(User user, T resource);

    ResponseEntity<?> deleteResource(User user, Integer id);

}