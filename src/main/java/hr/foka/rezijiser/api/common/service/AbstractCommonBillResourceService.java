package hr.foka.rezijiser.api.common.service;

import com.querydsl.core.types.dsl.BooleanExpression;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import hr.foka.rezijiser.api.common.exceptions.EntityNotFoundException;
import hr.foka.rezijiser.api.common.exceptions.MissingDataException;
import hr.foka.rezijiser.api.common.resources.BillResourceAssembler;
import hr.foka.rezijiser.api.common.resources.CommonResource;
import hr.foka.rezijiser.api.common.resources.MessageResourceAssembler;
import hr.foka.rezijiser.api.common.resources.ResourceRequest;
import hr.foka.rezijiser.api.common.resources.MessageResource.Type;
import hr.foka.rezijiser.persistence.domain.Bill;
import hr.foka.rezijiser.persistence.domain.User;
import hr.foka.rezijiser.persistence.repository.BillRepository;
import hr.foka.rezijiser.persistence.service.BillFilteringService;
import hr.foka.rezijiser.persistence.service.UserFilteringService;

public abstract class AbstractCommonBillResourceService<T extends CommonResource> implements CommonResourceService<T> {

    protected final BillResourceAssembler<T> assembler;
    protected final BillRepository repository;
    protected final BillFilteringService billFilteringService;
    protected final UserFilteringService userFilteringService;
    protected final MessageResourceAssembler messageAssembler;

    protected AbstractCommonBillResourceService(
            BillResourceAssembler<T> assembler,
            BillRepository repository,
            BillFilteringService filteringService,
            UserFilteringService userFilteringService,
            MessageResourceAssembler messageResourceAssembler) {
        this.assembler = assembler;
        this.repository = repository;
        this.billFilteringService = filteringService;
        this.userFilteringService = userFilteringService;
        this.messageAssembler = messageResourceAssembler;
    }

    @Override
    public ResponseEntity<?> getResources(User user) {
        return new ResponseEntity<>(assembler.toResources(repository.findAll(getBillFilter(user))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getResources(User user, ResourceRequest gridResource) {
        Pageable pageable;
        if (gridResource.getSortDirection() != null) {
            pageable = PageRequest.of(gridResource.getPageNumber(), gridResource.getPageSize(),
                    gridResource.getSortDirection(), gridResource.getSortBy().getColumnName());
        } else {
            pageable = PageRequest.of(gridResource.getPageNumber(), gridResource.getPageSize());
        }

        BooleanExpression filter = billFilteringService.processFilters(getBillFilter(user), gridResource.getFilters());

        Page<Bill> page = repository.findAll(filter, pageable);
        Page<T> resource = page.map(it -> assembler.toResource(it));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveResource(User user, T resource) {
        Bill entity = assembler.toEntity(user, resource);
        return new ResponseEntity<>(assembler.toResource(repository.save(entity)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateResource(User user, T resource) {
        Bill entity = assembler.toEntity(user, resource);

        if (entity.getId() == null)
            throw new MissingDataException("id");
        else if (!repository.exists(getBillFilterWithId(user, entity.getId())))
            throw new EntityNotFoundException(resource.getId());
        else
            return new ResponseEntity<>(assembler.toResource(repository.save(entity)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> deleteResource(User user, Integer id) {
        if (id == null)
            throw new MissingDataException("id");
        else if (!repository.exists(getBillFilterWithId(user, id)))
            throw new EntityNotFoundException(id);
        else {
            repository.deleteById(id);
            return new ResponseEntity<>(messageAssembler.assembleMessage("Deleted", Type.INFO), HttpStatus.OK);
        }

    }

    private BooleanExpression getBillFilter(User user) {
        return userFilteringService.filterForUser(user).and(billFilteringService.filterByBillType(getType()));
    }

    private BooleanExpression getBillFilterWithId(User user, Integer id) {
        return getBillFilter(user).and(billFilteringService.filterById(id));
    }

    protected abstract Bill.Type getType();

}
