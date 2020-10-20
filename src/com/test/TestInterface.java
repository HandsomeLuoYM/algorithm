package com.test;

/**
 * @author Ming
 * @date 2020/10/3 - 15:07
 * @describe
 */
@FunctionalInterface
public interface TestInterface<T> {

    Integer INTEGER = new Integer(101);

    void println(T t,T t2);



}
