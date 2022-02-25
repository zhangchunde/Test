package com.yimo.container.collection;

import java.util.ArrayList;
import java.util.List;

public class T03_ArrayList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < 10; i++) {
            list.add("e-" + i);
        }

        for (int i = 0; i < 5; i++) {
            list.add("e-" + (10 + i));
        }

        list.add("e-100");
        list.add("e-200");
    }

}
