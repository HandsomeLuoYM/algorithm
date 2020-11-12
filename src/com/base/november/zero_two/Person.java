package com.base.november.zero_two;

import com.base.october.three_zero.PersonInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Ming
 * @date 2020/10/30 - 11:14
 * @describe
 */
public class Person implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private Integer score;
    private static final long serialVersionUID = 1111013L;

    public Person(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person() {
        System.out.println("调用了空参构造器！！！");
    }

}

