package hr.foka.rezijiser.persistence.service;

import java.time.LocalDate;
import java.util.Collection;

import com.querydsl.core.types.dsl.BooleanExpression;

import hr.foka.rezijiser.api.common.resources.ResourceRequestFilter;
import hr.foka.rezijiser.persistence.domain.Bill;

public interface BillFilteringService {
 
    BooleanExpression filterById(Integer id);

    BooleanExpression filterByCostBetween(String gt, String lt);

    BooleanExpression filterByPaydayBetween(LocalDate gt, LocalDate lt);

    BooleanExpression filterByIsPaid();

    BooleanExpression filterByDatePaidBetween(LocalDate gt, LocalDate lt);

    BooleanExpression filterBySpentBetween(String gt, String lt);

    BooleanExpression filterByBillType(Bill.Type type);

    BooleanExpression processFilters(BooleanExpression startingFilter, Collection<ResourceRequestFilter> filters);

}