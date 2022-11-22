package com.chatchatabc.user.proxy;

import com.chatchatabc.user.domain.model.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserRepositoryInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // default method
        if (method.isDefault()) {
            InvocationHandler.invokeDefault(proxy, method, args);
        }
        // handler method
        if (method.getName().equals("findById")) {
            final User user = new User();
            user.setId((Long) args[0]);
            user.setNick("JDK Proxy");
            user.setEmail("kuulei_gillxxtq@antivirus.ezv");
            return user;
        }
        return null;
    }
}
