package hr.foka.rezijiser.persistence.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

import com.querydsl.core.types.dsl.BooleanExpression;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.converters.LocalDateConverter;
import hr.foka.rezijiser.api.common.resources.ResourceRequestFilter;
import hr.foka.rezijiser.api.common.resources.ResourceRequestFilter.FilterOperation;
import hr.foka.rezijiser.api.common.resources.ResourceRequestFilter.TargetColumn;
import hr.foka.rezijiser.persistence.domain.Bill.Type;
import hr.foka.rezijiser.persistence.domain.QBill;

@Service
public final class BillFilteringServiceImpl implements BillFilteringService {

    private LocalDateConverter dateParser;

    public BillFilteringServiceImpl(LocalDateConverter dateParser){
        this.dateParser = dateParser;
    }

    @Override
    public BooleanExpression filterByCostBetween(String gt, String lt) {
        return QBill.bill.cost.between(new BigDecimal(gt), new BigDecimal(lt));
    }

    @Override
    public BooleanExpression filterByPaydayBetween(LocalDate gt, LocalDate lt) {
        return QBill.bill.payday.between(gt, lt);
    }

    @Override
    public BooleanExpression filterByIsPaid() {
        return QBill.bill.datePaid.isNotNull();
    }

    @Override
    public BooleanExpression filterByDatePaidBetween(LocalDate gt, LocalDate lt) {
        return QBill.bill.datePaid.between(gt, lt);
    }

    @Override
    public BooleanExpression filterBySpentBetween(String gt, String lt){
        return QBill.bill.spent.between(new BigDecimal(gt), new BigDecimal(lt));
    }

    @Override
    public BooleanExpression filterByBillType(Type type) {
        return QBill.bill.type.eq(type);
    }

    @Override
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

    private BooleanExpression processEqualsFilter(TargetColumn column, String value) {
        switch (column) {
            case PAYDAY:
                return filterByPaydayBetween(dateParser.convert(value), dateParser.convert(value));
            case COST: 
                return filterByCostBetween(value, value);
            case DATEPAID: 
                return filterByDatePaidBetween(dateParser.convert(value), dateParser.convert(value));
            case SPENT:
                return filterBySpentBetween(value, value);
            default: 
                throw new IllegalArgumentException("Unknown column " + column);
        }
    }

    private BooleanExpression processLessThanFilter(TargetColumn column, String value) {
        switch (column) {
            case PAYDAY:
                return filterByPaydayBetween(LocalDate.MIN, dateParser.convert(value));
            case COST: 
                return filterByCostBetween(Integer.valueOf(Integer.MIN_VALUE).toString(), value);
            case DATEPAID: 
                return filterByDatePaidBetween(LocalDate.MIN, dateParser.convert(value));
            case SPENT:
                return filterBySpentBetween(Integer.valueOf(Integer.MIN_VALUE).toString(), value);
            default: 
                throw new IllegalArgumentException("Unknown column " + column);
        }
    }

    private BooleanExpression processGreaterThanFilter(TargetColumn column, String value) {
        switch (column) {
            case PAYDAY:
                return filterByPaydayBetween(dateParser.convert(value), LocalDate.MAX);
            case COST: 
                return filterByCostBetween(value, Integer.valueOf(Integer.MAX_VALUE).toString());
            case DATEPAID: 
                return filterByDatePaidBetween(dateParser.convert(value), LocalDate.MAX);
            case SPENT:
                return filterBySpentBetween(value, Integer.valueOf(Integer.MAX_VALUE).toString());
            default: 
                throw new IllegalArgumentException("Unknown column " + column);
        }
    }

}
