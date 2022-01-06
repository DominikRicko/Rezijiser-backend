package hr.foka.rezijiser.api.reservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.foka.rezijiser.api.common.resources.ResourceRequest;
import hr.foka.rezijiser.api.reservation.resource.ReservationResource;
import hr.foka.rezijiser.api.reservation.service.ReservationService;
import hr.foka.rezijiser.persistence.domain.User;

@RestController
@RequestMapping(path = "/e/api/v1/reservation")
public class ReservationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getPowerBills(@AuthenticationPrincipal User user) {
        LOGGER.debug("Received GET request for power");
        return service.getResources(user);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/paged")
    public ResponseEntity<?> getPowerBills(@AuthenticationPrincipal User user, @RequestBody ResourceRequest page){
        LOGGER.debug("Received POST request to fetch paged power");
        return service.getResources(user, page);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> savePowerBill(@AuthenticationPrincipal User user, @RequestBody ReservationResource resource) {
        LOGGER.debug("Received POST request to save power");
        return service.saveResource(user, resource);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updatePowerBill(@AuthenticationPrincipal User user, @RequestBody ReservationResource resource) {
        LOGGER.debug("Received PUT request to update power");
        return service.updateResource(user, resource);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id:.+}")
    public ResponseEntity<?> deletePowerBill(@AuthenticationPrincipal User user, @PathVariable("id") Integer id) {
        LOGGER.debug("Received DELETE request to delete Power with Id {}", id);
        return service.deleteResource(user, id);
    }

}