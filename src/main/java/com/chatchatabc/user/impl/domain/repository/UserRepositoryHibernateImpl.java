package com.chatchatabc.user.impl.domain.repository;

import com.chatchatabc.user.domain.model.User;
import com.chatchatabc.user.domain.repository.UserRepository;
import net.datafaker.Faker;
import org.jetbrains.annotations.Nullable;

public class UserRepositoryHibernateImpl implements UserRepository {
    @Override
    public @Nullable User findById(Long id) {
        Faker faker = new Faker();
        User user = new User();
        user.setId(id);
        user.setNick(faker.name().name());
        user.setEmail(faker.internet().emailAddress());
        return user;
    }
}
