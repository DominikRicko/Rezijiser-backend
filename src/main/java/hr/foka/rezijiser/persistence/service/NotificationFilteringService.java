package hr.foka.rezijiser.persistence.service;

import java.util.Collection;

import com.querydsl.core.types.dsl.BooleanExpression;

import hr.foka.rezijiser.api.notification.resource.NotificationRequestFilter;

public interface NotificationFilteringService {

    BooleanExpression processFilters(BooleanExpression startingFilter, Collection<NotificationRequestFilter> filters);
    
}
