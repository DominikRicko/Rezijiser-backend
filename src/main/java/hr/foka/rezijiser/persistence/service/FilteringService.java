package hr.foka.rezijiser.persistence.service;

import java.time.LocalDate;
import java.util.Collection;

import com.querydsl.core.types.dsl.BooleanExpression;

import hr.foka.rezijiser.api.common.converters.LocalDateConverter;
import hr.foka.rezijiser.api.common.resources.ResourceRequestFilter;
import hr.foka.rezijiser.api.common.resources.ResourceRequestFilter.FilterOperation;
import hr.foka.rezijiser.api.common.resources.ResourceRequestFilter.TargetColumn;
import hr.foka.rezijiser.persistence.domain.QUser;
import hr.foka.rezijiser.persistence.domain.User;

public abstract class FilteringService {

    protected LocalDateConverter dateParser;

    public FilteringService(LocalDateConverter dateParser){
        this.dateParser = dateParser;
    }

    public BooleanExpression filterForUser(User user) {
        return QUser.user.eq(user);
    }

    public abstract BooleanExpression filterByCostBetween(String gt, String lt);

    public abstract BooleanExpression filterByPaydayBetween(LocalDate gt, LocalDate lt);

    public abstract BooleanExpression filterByIsPaid();

    public abstract BooleanExpression filterByDatePaidBetween(LocalDate gt, LocalDate lt);

    public BooleanExpression processFilters(BooleanExpression startingFilter, Collection<ResourceRequestFilter> filters) {

        for (ResourceRequestFilter filter : filters) {

            FilterOperation op = filter.getOp();
            TargetColumn col = filter.getCol();
            String val = filter.getVal();

            startingFilter = processFilterOperation(startingFilter, op, col, val);
        }

        return startingFilter;

    }

    private BooleanExpression processFilterOperation(BooleanExpression filter, FilterOperation op, TargetColumn col, String val) {

        switch (op) {
            case EQ: {
                filter = filter.and(processEqualsFilter(col, val));
                break;
            }

            case LT: {
                filter = filter.and(processLessThanFilter(col, val));
                break;
            }

            case GT: {
                filter = filter.and(processGreaterThanFilter(col, val));
                break;
            }

            case PAID: {

                if(Boolean.valueOf(val)) filter = filter.and(filterByIsPaid());
                else filter = filter.and(filterByIsPaid().not());

                break;
            }
        }

        return filter;
    }

    protected BooleanExpression processEqualsFilter(TargetColumn column, String value) {
        switch (column) {
            case PAYDAY:
                return filterByPaydayBetween(dateParser.convert(value), dateParser.convert(value));
            case COST: 
                return filterByCostBetween(value, value);
            case DATEPAID: 
                return filterByDatePaidBetween(dateParser.convert(value), dateParser.convert(value));
            default: 
                throw new IllegalArgumentException("Unknown column " + column);
        }
    }

    protected BooleanExpression processLessThanFilter(TargetColumn column, String value) {
        switch (column) {
            case PAYDAY:
                return filterByPaydayBetween(LocalDate.MIN, dateParser.convert(value));
            case COST: 
                return filterByCostBetween(Integer.valueOf(Integer.MIN_VALUE).toString(), value);
            case DATEPAID: 
                return filterByDatePaidBetween(LocalDate.MIN, dateParser.convert(value));
            default: 
                throw new IllegalArgumentException("Unknown column " + column);
        }
    }

    protected BooleanExpression processGreaterThanFilter(TargetColumn column, String value) {
        switch (column) {
            case PAYDAY:
                return filterByPaydayBetween(dateParser.convert(value), LocalDate.MAX);
            case COST: 
                return filterByCostBetween(value, Integer.valueOf(Integer.MAX_VALUE).toString());
            case DATEPAID: 
                return filterByDatePaidBetween(dateParser.convert(value), LocalDate.MAX);
            default: 
                throw new IllegalArgumentException("Unknown column " + column);
        }
    }

}
