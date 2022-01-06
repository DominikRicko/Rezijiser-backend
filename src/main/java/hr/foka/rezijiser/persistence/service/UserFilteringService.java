package hr.foka.rezijiser.persistence.service;

import com.querydsl.core.types.dsl.BooleanExpression;

import hr.foka.rezijiser.persistence.domain.User;

public interface UserFilteringService {
    
    BooleanExpression filterForUser(User user);

}
