package com.chatchatabc.user.proxy;

import com.chatchatabc.user.domain.model.User;
import javassist.util.proxy.MethodHandler;

import java.lang.reflect.Method;

public class UserRepositoryJavassitHandler implements MethodHandler {

    @Override
    public Object invoke(Object self, Method method, Method proceed, Object[] args) throws Throwable {
        if (method.getName().equals("findById")) {
            final User user = new User();
            user.setId((Long) args[0]);
            user.setNick("Javassist");
            user.setEmail("kuulei_gillxxtq@antivirus.ezv");
            return user;
        }
        return null; 
    }
}
