package hr.foka.rezijiser.persistence.service;

import com.querydsl.core.types.dsl.BooleanExpression;

import org.springframework.stereotype.Service;

import hr.foka.rezijiser.persistence.domain.QUser;
import hr.foka.rezijiser.persistence.domain.User;

@Service
public class UserFilteringServiceImpl implements UserFilteringService {

    @Override
    public BooleanExpression filterForUser(User user) {
        return QUser.user.eq(user);
    }
    
}
