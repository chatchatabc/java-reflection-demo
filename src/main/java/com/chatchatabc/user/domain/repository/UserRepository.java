package com.chatchatabc.user.domain.repository;

import com.chatchatabc.user.domain.model.User;
import org.jetbrains.annotations.Nullable;
import org.mvnsearch.microservices.annotator.DatabaseAccess;

@DatabaseAccess
public interface UserRepository {
    default String getAdminName() {
        return "admin";
    }

    @Nullable
    User findById(Long id);
}
