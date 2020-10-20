package com.test;

/**
 * @author Ming
 * @date 2020/10/3 - 15:33
 * @describe
 */
public class User {

    private Integer id;
    private Integer age;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(){
    }

    public User(Integer id, Integer age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public static int comparator(User user1, User user2){
        return user1.getAge() - user2.getAge();
    }

    public int compareByName(User user1, User user2) {
        return user1.getName().compareTo(user2.getName());
    }
}
