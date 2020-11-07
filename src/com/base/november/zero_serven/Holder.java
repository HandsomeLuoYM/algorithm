package com.base.november.zero_serven;

/**
 * @author Ming
 * @date 2020/11/7 - 16:11
 * @describe
 */
public class Holder {

    private Holder(){}

    public static Holder getInstance(){
        return InnerClass.HOLDER;
    }

    public static class InnerClass{
        private static final Holder HOLDER = new Holder();
    }
}
