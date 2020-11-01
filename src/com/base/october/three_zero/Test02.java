package com.base.october.three_zero;

import java.io.*;
import java.util.Queue;

/**
 * @author Ming
 * @date 2020/10/30 - 12:11
 * @describe
 */
public class Test02 {

    public static void main(String[] args) throws Exception {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("D:\\idea_maven\\algorithm\\test.txt"));
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("D:\\idea_maven\\algorithm\\test.txt"));
        PersonInfo piObject = new PersonInfo(110);
        Person pObject1 = new Person(1, "AAA", 1008611,piObject);
        outputStream.writeObject(pObject1);
        outputStream.writeObject(pObject1);
        outputStream.writeObject(piObject);

        Person person1 = (Person) inputStream.readObject();//依次读出两次 Person 对象
        Person person2 = (Person) inputStream.readObject();
        PersonInfo personInfo = (PersonInfo)inputStream.readObject();//读出 personInfo 对象

        System.out.println(person1==person2); //读出的两次对象是相等的
        System.out.println(pObject1==person2); //读出的对象和原对象是不相等的
        System.out.println(person1.getPersonInfo()==personInfo); //读出来的 Person 对象中的 PersonInfo 引用和读出来的 PersonInfo 一致
        System.out.println(person1.getPersonInfo()==person2.getPersonInfo()); //读出来的 Person 对象中的 PersonInfo 引用都是同一个


//            person.setAge(110);
//            outputStream.writeObject(person);
//
//            Person p1 = (Person) inputStream.readObject();
//            Person p2 = (Person) inputStream.readObject();
//            System.out.println(p1==p2);
//            System.out.println(p1);
//            System.out.println(person);


    }
}
