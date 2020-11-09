package com.base.november.zero_serven.destroysingle;

import com.base.november.zero_serven.EnumSingle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Ming
 * @date 2020/11/7 - 17:28
 * @describe
 */
public enum EnumDestory {
    INSTANCE;
}
class Test{
    public static void main(String[] args) throws Exception {
        EnumDestory instance1 = EnumDestory.INSTANCE;
        Constructor<EnumDestory> declaredConstructor = EnumDestory.class.getDeclaredConstructor(String.class,int.class);
        declaredConstructor.setAccessible(true);
        EnumDestory instance2 = declaredConstructor.newInstance();
        System.out.println(instance1 == instance2);

    }
}
