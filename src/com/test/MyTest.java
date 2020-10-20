package com.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author Ming
 * @date 2020/10/3 - 12:00
 * @describe
 */
public class MyTest {
    final static Integer test = 10;

    transient static int N = 1;
    public static void main(String[] args) {

        System.out.println(new String("fnsdhdsnajkhk").hashCode());
        System.out.println(new String("fnsdhdsnajkhk").hashCode());

        User user = new User();
        User user1 = new User(1,22,"小王");
        User user2 = new User(2,20,"小李");
        User user3 = new User(3,18,"小黄");
        List<User> list = Arrays.asList(user1, user2, user3);
        list.sort(User::comparator);
        list.forEach(System.out::println);

        list.sort(user::compareByName);

        TestInterface2 testInterface2 = User::new;
        User creat = testInterface2.creat();

        TestInterface testInterface = (a1,a2) -> System.out.println(a1 +"------"+ a2);
//        Runnable aNew = User::new;

    }

}
