package com.chatchatabc.user;

import com.chatchatabc.user.domain.model.User;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.AnnotationUtils;
import org.mvnsearch.microservices.annotator.DatabaseAccess;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ReflectionTest {
    @Test
    public void testVanillaReflection() throws Exception {
        Class<?> clazz = Class.forName("com.chatchatabc.user.domain.repository.UserRepository");
        // class annotations
        final DatabaseAccess annotation = clazz.getAnnotation(DatabaseAccess.class);
        assertThat(annotation).isNotNull();
        // methods
        final Method method = clazz.getMethod("findById", Long.class);
        assertThat(method).isNotNull();
        // method's params
        for (Parameter parameter : method.getParameters()) {
            System.out.println(parameter.getName());
            // param's annotations
            // parameter.getAnnotations();
        }
        // method's return type
        final Class<?> returnType = method.getReturnType();
        assertThat(returnType).isEqualTo(User.class);
        // method's annotations
        final Nullable nullableAnnotation = method.getAnnotation(Nullable.class);
        assertThat(nullableAnnotation).isNull();
    }

    @Test
    public void testClassUtils() throws Exception {
        final Class<?> clazz = ClassUtils.forName("com.chatchatabc.user.domain.repository.UserRepository", this.getClass().getClassLoader());
        final boolean isMethodExist = ClassUtils.hasMethod(clazz, "findById", Long.class);
        assertThat(isMethodExist).isTrue();
        final Optional<DatabaseAccess> annotation = AnnotationUtils.findAnnotation(clazz, DatabaseAccess.class);
        assertThat(annotation).isPresent();
    }
}
