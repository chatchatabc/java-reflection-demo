package com.chatchatabc.user.proxy;

import com.chatchatabc.user.domain.model.User;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserRepositoryInvocationHandler implements InvocationHandler {
    @RuntimeType
    @Override
    public Object invoke(@This Object proxy, @Origin Method method, @AllArguments Object[] args) throws Throwable {
        // default method
        if (method.isDefault()) {
            InvocationHandler.invokeDefault(proxy, method, args);
        }
        // handler method
        if (method.getName().equals("findById")) {
            final User user = new User();
            user.setId((Long) args[0]);
            user.setNick("mocked");
            user.setEmail("kuulei_gillxxtq@antivirus.ezv");
            return user;
        }
        return null;
    }
}
