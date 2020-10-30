package com.base.october.two_five;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Ming
 * @date 2020/10/25 - 17:13
 * @describeEnum
 */
public class ReflectTest {

    static class Proxy {

        public void run() {
            System.out.println("run");
        }

    }

    public static void main(String[] args) throws Exception{
        Proxy proxy = new Proxy();
        Method method = Proxy.class.getDeclaredMethod("run");
        method.invoke(proxy);
    }
}
