package com.yimo.thread.threadLock.OneQuestion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OneCollection {
    volatile private List list = new LinkedList();
    public void add(Object o){
        list.add(o);
    }

    public int getSize(){
        return list.size();
    }

}
