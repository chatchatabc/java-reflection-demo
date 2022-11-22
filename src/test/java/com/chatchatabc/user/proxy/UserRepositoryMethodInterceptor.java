package com.chatchatabc.user.proxy;

import com.chatchatabc.user.domain.model.User;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


public class UserRepositoryMethodInterceptor implements MethodInterceptor {
    @Nullable
    private Object realObj;

    public UserRepositoryMethodInterceptor() {

    }

    public UserRepositoryMethodInterceptor(@Nullable Object realObject) {
        super();
        this.realObj = realObject;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // default method
        if (method.isDefault()) {
            // invoke default method on real object
        }
        // handler method
        if (method.getName().equals("findById")) {
            final User user = new User();
            user.setId((Long) args[0]);
            user.setNick("CGLIB");
            user.setEmail("kuulei_gillxxtq@antivirus.ezv");
            return user;
        }
        return null;
    }
}
