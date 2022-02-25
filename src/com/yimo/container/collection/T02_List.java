package com.yimo.container.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class T02_List {
    private static List<Person> list = new Vector<Person>();

    static class Person{
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    static{

        Person p1 = new Person("laowang", 34);
        Person p2 = new Person("laoli", 28);
        Person p3 = new Person("laozhang", 18);
        Person p4 = new Person("laozhao", 65);
        Person p5 = new Person("laoqian", 9);

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
    }


    public static void main(String[] args) {
        test03();
    }

    public static void test03(){

        System.out.println("list:" + getList(list));
        sortList(list);
        System.out.println("list:" + getList(list));
    }

    public static void sortList(List<Person> list){
        int length = list.size();
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {

                Person p1 = (Person)list.get(j);
                Person p2 = (Person)list.get(j + 1);

                if(p1.getAge() > p2.getAge()){
                    list.set(j, p2);
                    list.set(j + 1, p1);
                }

            }

        }
    }


    public static void test02(){
//        List<Person> list = new ArrayList<Person>();
//        List<Person> list = new LinkedList<Person>();
        List<Person> list = new Vector<Person>();

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

        list.add(3,new Person("lisi",32));

        System.out.println("list:" + getList(list));

        System.out.println(list.lastIndexOf(p2));

        list.remove(4);
        System.out.println("list:" + getList(list));

        list.set(3,p2);
        System.out.println("list:" + getList(list));

        System.out.println("subList:" + getList(list.subList(2,5)));

    }

    public static String getList(List<Person> list){

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
        List<String> list = new ArrayList<String>();

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
