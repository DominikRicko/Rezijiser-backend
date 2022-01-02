package hr.foka.rezijiser.persistence.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.querydsl.core.types.dsl.BooleanExpression;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.common.converters.LocalDateConverter;
import hr.foka.rezijiser.api.common.resources.ResourceRequestFilter.TargetColumn;
import hr.foka.rezijiser.persistence.domain.QPower;

@Service
public class PowerFilteringService extends FilteringService {

    public PowerFilteringService(LocalDateConverter dateParser) {
        super(dateParser);
    }

    @Override
    public BooleanExpression filterByCostBetween(String gt, String lt) {
        return QPower.power.cost.between(new BigDecimal(gt), new BigDecimal(lt));
    }

    @Override
    public BooleanExpression filterByPaydayBetween(LocalDate gt, LocalDate lt) {
        return QPower.power.payday.between(gt, lt);
    }

    @Override
    public BooleanExpression filterByIsPaid() {
        return QPower.power.datePaid.isNotNull();
    }

    @Override
    public BooleanExpression filterByDatePaidBetween(LocalDate gt, LocalDate lt) {
        return QPower.power.datePaid.between(gt, lt);
    }

    public BooleanExpression filterByCounterBetween(String gt, String lt){
        return QPower.power.counter.between(new BigDecimal(gt), new BigDecimal(lt));
    }
    
    @Override
    protected BooleanExpression processEqualsFilter(TargetColumn column, String value) {
        if(column == TargetColumn.COUNTER){
            return filterByCounterBetween(value, value);
        } else {
            return super.processEqualsFilter(column, value);
        }
    }

    @Override
    protected BooleanExpression processGreaterThanFilter(TargetColumn column, String value) {
        if(column == TargetColumn.COUNTER){
            return filterByCounterBetween(value, Integer.valueOf(Integer.MAX_VALUE).toString());
        } else {
            return super.processGreaterThanFilter(column, value);
        }
        
    }

    @Override
    protected BooleanExpression processLessThanFilter(TargetColumn column, String value) {
        if(column == TargetColumn.COUNTER){
            return filterByCounterBetween(Integer.valueOf(Integer.MIN_VALUE).toString(), value);
        } else {
            return super.processLessThanFilter(column, value);
        }
        
    }

}
