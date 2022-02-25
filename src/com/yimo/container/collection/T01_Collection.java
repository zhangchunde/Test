package com.yimo.container.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class T01_Collection {

    static class Person{
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


    public static void main(String[] args) {
        test02();
    }

    public static void test02(){
        Collection<Person> list = new ArrayList<Person>();

        Person p1 = new Person("laowang", 45);
        Person p2 = new Person("laoli", 45);
        Person p3 = new Person("laozhang", 45);
        Person p4 = new Person("laozhao", 45);
        Person p5 = new Person("laoqian", 45);

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);

        System.out.println("list:" + getList(list));

        list.remove(p3);

        System.out.println("list:" + getList(list));
        list.remove(4);

        System.out.println("list:" + getList(list));

        Collection<Person> list1 = new ArrayList<Person>();
        list1.add(p3);
        list1.add(p5);

        list.addAll(list1);
        System.out.println("list:" + getList(list));
        System.out.println(list.containsAll(list1));
        list.removeAll(list1);
        System.out.println("list:" + getList(list));
        System.out.println(list.containsAll(list1));

    }

    public static String getList(Collection<Person> list){

        String content = "";

//        Iterator<Person> iterator = list.iterator();
//                while (iterator.hasNext()){
//            content += "[" + iterator.next().toString() + "]";
//        }

        for (Person p: list) {
            content += p.toString();
        }

        return content;
    }

    public static void test01(){
        Collection<String> list = new ArrayList<String>();

        String s = "l3";

        list.add("l1");
        list.add("l2");
        list.add("l3");
        list.add("l4");
        list.add("l3");

        System.out.println("list:" + list);

        list.remove(s);

        System.out.println("list:" + list);
        list.remove(s);

        System.out.println("list:" + list);
    }
}
