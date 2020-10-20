package com.base.october.one_serven;

/**
 * @author Ming
 * @date 2020/10/17 - 17:38
 * @describe
 */
public class CopyDemo01 {
    class Person{
        //两个属性值：分别代表值传递和引用传递
        private Age age;
        private String name;
        public Person(Age age,String name) {
            this.age=age;
            this.name=name;
        }
        //拷贝构造方法
        public Person(Person p) {
            this.name=p.name;
            this.age=p.age;
        }
        public void setName(String name) {
            this.name=name;
        }
        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    class Age{
        private int age;
        public Age(int age) {
            this.age=age;
        }
        public void setAge(int age) {
            this.age=age;
        }

        @Override
        public String toString() {
            return "Age{" +
                    "age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {
        CopyDemo01 copy = new CopyDemo01();
        Age age = copy.new Age(20);
        Person p1=copy.new Person(age,"AAAAA"); //源数据
        Person p2=copy.new Person(p1); //拷贝数据
        System.out.println("p1 = "+p1);
        System.out.println("p2 = "+p2);
        //修改p1的各属性值，观察p2的各属性值是否跟随变化
        p1.setName("BBBBB");
        age.setAge(99);
        System.out.println("修改后的p1 = "+p1);
        System.out.println("修改后的p2 = "+p2);
    }

}
