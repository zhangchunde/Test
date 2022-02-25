package com.yimo.thread.Threadpool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class T08_ForkJoinPool {
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static{
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }

        System.out.println("---" + Arrays.stream(nums).sum());
    }

    static class AddTask extends RecursiveAction{

        private int s,e;

        public AddTask(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        protected void compute() {
            long sum = 0L;
            if(e - s < MAX_NUM){
                for (int i = s; i < e; i++) {
                    sum += nums[i];
                }
                System.out.println("addTask:sum(nums[" + s + "]...nums[" + e + "]) = " + sum);
            }else{
                int middle = s + (e-s)/2;
                AddTask addTask1 = new AddTask(s, middle);
                AddTask addTask2 = new AddTask(middle, e);

                addTask1.fork();
                addTask2.fork();
            }
        }
    }

    static class AddTaskRej extends RecursiveTask<Long>{
        private int s,e;

        public AddTaskRej(int s, int e) {
            this.s = s;
            this.e = e;
        }
        @Override
        protected Long compute() {
            long sum = 0L;
            if(e - s < MAX_NUM){
                for (int i = s; i < e; i++) {
                    sum += nums[i];
                }
                System.out.println("addTaskRej:sum(nums[" + s + "]...nums[" + e + "]) = " + sum);
                return sum;
            }else{
                int middle = s + (e-s)/2;
                AddTaskRej addTask1 = new AddTaskRej(s, middle);
                AddTaskRej addTask2 = new AddTaskRej(middle, e);

                addTask1.fork();
                addTask2.fork();

                return addTask1.join() + addTask2.join();
            }
        }
    }

    public static void main(String[] args) {

        ForkJoinPool fjp = new ForkJoinPool();
        AddTask addTask = new AddTask(0,nums.length);
        fjp.execute(addTask);

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ForkJoinPool fjp1 = new ForkJoinPool();
        AddTaskRej addTaskRej = new AddTaskRej(0,nums.length);
        fjp.execute(addTaskRej);
        long result = addTaskRej.join();
        System.out.println(result);


    }


}
