package com.yimo.container.collection;

public class T04_LinkedList {

    static class Person{
        String name;

        public Person(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {

        Person p = new Person("laowang");
        final Person p1 = p;
        p = null;
        System.out.println(p1.name);

    }

}
