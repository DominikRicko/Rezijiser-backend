package hr.foka.rezijiser.persistence.service;

import java.util.Collection;

import com.querydsl.core.types.dsl.BooleanExpression;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.notification.resource.NotificationRequestFilter;
import hr.foka.rezijiser.api.notification.resource.NotificationRequestFilter.FilterOperation;
import hr.foka.rezijiser.api.notification.resource.NotificationRequestFilter.TargetColumn;
import hr.foka.rezijiser.persistence.domain.QNotification;
import hr.foka.rezijiser.persistence.domain.QUser;
import hr.foka.rezijiser.persistence.domain.User;
import hr.foka.rezijiser.persistence.domain.Notification.Level;

@Service
public class NotificationFilteringService {
    
    protected ZonedDateTimeConverter dateParser;

    public NotificationFilteringService(ZonedDateTimeConverter dateParser){
        this.dateParser = dateParser;
    }

    public BooleanExpression filterForUser(User user) {
        return QUser.user.eq(user);
    }

    public BooleanExpression processFilters(BooleanExpression startingFilter, Collection<NotificationRequestFilter> filters) {

        for (NotificationRequestFilter filter : filters) {

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

            case REGEX:{
                filter = filter.and(processRegexFilter(col, val));
                break;
            }

        }

        return filter;
    }

    protected BooleanExpression processRegexFilter(TargetColumn column, String regex) {
        switch (column) {
            case TITLE: 
                return QNotification.notification.title.matches(regex);
            case MESSAGE:
                return QNotification.notification.message.matches(regex);
            default: 
                throw new UnsupportedOperationException("Regex cannot be processed for " + column.name());
        }
    }

    protected BooleanExpression processEqualsFilter(TargetColumn column, String value) {
        switch (column) {
            case TYPE:
                return QNotification.notification.level.eq(Level.valueOf(value));
            case TITLE: 
                return QNotification.notification.title.eq(value);
            case MESSAGE:
                return QNotification.notification.message.eq(value);
            case TIME_CREATED: 
                return QNotification.notification.timeCreated.eq(dateParser.convert(value));
            case CHECKED:
                return QNotification.notification.checked.eq(Boolean.valueOf(value));
            default: 
                throw new IllegalArgumentException("Unknown column " + column);
        }
    }

    protected BooleanExpression processLessThanFilter(TargetColumn column, String value) {
        switch (column) {
            case TIME_CREATED: 
                return QNotification.notification.timeCreated.before(dateParser.convert(value));
            default: 
                throw new UnsupportedOperationException("Less than cannot be processed for " + column.name());
        }
    }

    protected BooleanExpression processGreaterThanFilter(TargetColumn column, String value) {
        switch (column) {
            case TIME_CREATED: 
            return QNotification.notification.timeCreated.after(dateParser.convert(value));
            default: 
                throw new UnsupportedOperationException("Regex cannot be processed for " + column.name());
        }
    }
}
