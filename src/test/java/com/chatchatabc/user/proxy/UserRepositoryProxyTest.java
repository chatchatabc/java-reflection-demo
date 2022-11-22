package com.chatchatabc.user.proxy;

import com.chatchatabc.user.domain.model.User;
import com.chatchatabc.user.domain.repository.UserRepository;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryProxyTest {
    @Test
    public void testJdkProxy() {
        InvocationHandler invocationHandler = new UserRepositoryInvocationHandler();
        UserRepository userRepository = (UserRepository) Proxy.newProxyInstance(
                UserRepositoryProxyTest.class.getClassLoader(),
                new Class[]{UserRepository.class},
                invocationHandler);
        final User user = userRepository.findById(1L);
        assertThat(user).isNotNull();
        System.out.println(user.getNick());
    }

    @Test
    public void testByteBuddyProxy() throws Exception {
        InvocationHandler invocationHandler = new UserRepositoryInvocationHandler();
        Class<?> clazz = UserRepository.class;
        Class<?> dynamicType = new ByteBuddy(ClassFileVersion.JAVA_V8)
                .subclass(clazz)
                .name(clazz.getSimpleName() + "Stub")
                .method(ElementMatchers.not(ElementMatchers.isDefaultMethod()))
                .intercept(MethodDelegation.to(invocationHandler))
                .make()
                .load(clazz.getClassLoader())
                .getLoaded();
        final UserRepository userRepository = (UserRepository) dynamicType.newInstance();
        System.out.println(userRepository.findById(1L).getNick());
    }


}
