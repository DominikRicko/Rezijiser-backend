package hr.foka.rezijiser.api.power.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.resources.ResourcePage;
import hr.foka.rezijiser.api.power.resources.PowerResource;
import hr.foka.rezijiser.api.power.resources.PowerResourceAssembler;
import hr.foka.rezijiser.persistence.domain.Power;
import hr.foka.rezijiser.persistence.domain.User;
import hr.foka.rezijiser.persistence.repository.PowerRepository;

@Service
public class PowerServiceImpl implements PowerService {

    private final PowerResourceAssembler assembler;
    private final PowerRepository repository;

    public PowerServiceImpl(PowerResourceAssembler assembler, PowerRepository repository) {
        this.assembler = assembler;
        this.repository = repository;
    }

    @Override
    public ResponseEntity<?> getResources(User user) {
        return new ResponseEntity<>(assembler.toResources(repository.findByUser(user)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getResources(User user, ResourcePage gridResource) {
        Pageable pageable = PageRequest.of(gridResource.getPageNumber(), gridResource.getPageSize(),
                gridResource.getSortDirection(), gridResource.getSortBy());
        Page<Power> page = repository.findAllByUser(user, pageable);
        Page<PowerResource> resource = page.map(it -> assembler.toResource(it));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveResource(User user, PowerResource resource) {
        Power power = assembler.toEntity(user, resource);
        return new ResponseEntity<>(assembler.toResource(repository.save(power)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateResource(User user, PowerResource resource) {
        Power power = assembler.toEntity(user, resource);

        if (power.getId() == null)
            return new ResponseEntity<>("Missing id, cannot update.", HttpStatus.BAD_REQUEST);
        else if (!repository.existsById(power.getId()))
            return new ResponseEntity<>("Entity with id " + power.getId() + " does not exist in database.",
                    HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(assembler.toResource(repository.save(power)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> deleteResource(User user, Integer id) {
        if (id == null)
            return new ResponseEntity<>("Missing id, cannot delete.", HttpStatus.BAD_REQUEST);
        else if (!repository.existsById(id))
            return new ResponseEntity<>("Entity with id " + id + " does not exist in database.", HttpStatus.NOT_FOUND);
        else {
            repository.deleteById(id);
            return new ResponseEntity<>("Deleted.", HttpStatus.OK);
        }

    }

}