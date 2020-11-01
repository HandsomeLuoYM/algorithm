package com.base.october.three_zero;

import java.io.Serializable;

/**
 * @author Ming
 * @date 2020/10/30 - 11:14
 * @describe
 */
public class Person implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private PersonInfo personInfo;
    public Person(Integer id, String name, Integer age, PersonInfo personInfo) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.personInfo = personInfo;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
