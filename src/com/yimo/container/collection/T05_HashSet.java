package com.yimo.container.collection;

import java.util.HashSet;
import java.util.Set;

public class T05_HashSet {

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();

        System.out.println(set.add("laowang"));
        System.out.println(set.add("laoli"));
        System.out.println(set.add("laozhao"));
        System.out.println(set.add("laozhang"));
        System.out.println(set.add("laozheng"));
        System.out.println(set.add("laoqian"));
        System.out.println(set.add("laoqian"));
        System.out.println(set.add(null));

        System.out.println("set = " + set);

    }


}
