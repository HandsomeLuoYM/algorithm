package com.base.october.three_zero;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Ming
 * @date 2020/10/30 - 11:14
 * @describe
 */
public class Person implements Serializable {
    transient private Integer id;
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

    public void setAge(int age) {
        this.age = age;
    }
    private void writeObject(ObjectOutputStream out) throws IOException{
        //将名字反转写入二进制流       
        out.writeObject("new StringBuffer(this.name).reverse()");
        out.writeInt(age);
    }
    private void readObject(ObjectInputStream ins) throws IOException,ClassNotFoundException{
        //将读出的字符串反转恢复回来      
        this.name = ins.readObject().toString();
        this.age = ins.readInt();
    }

    private Object readResolve() throws ObjectStreamException{
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(123456,1321321);
        return objectObjectHashMap;
    }

    private Object writeReplace() throws ObjectStreamException {
        ArrayList<Object> list = new ArrayList<>(2);
        list.add(this.name);
        list.add(this.age);
        return list;
    }

}
