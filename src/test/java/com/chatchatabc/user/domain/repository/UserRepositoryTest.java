package com.chatchatabc.user.domain.repository;

import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

public class UserRepositoryTest {
    @Test
    public void testServiceLoader() {
        ServiceLoader<UserRepository> userRepositoryServiceLoader = ServiceLoader.load(UserRepository.class);
        for (UserRepository userRepository : userRepositoryServiceLoader) {
            System.out.println(userRepository.getClass().getCanonicalName());
            System.out.println("User: " + userRepository.findById(1L).getNick());
        }
    }
}
