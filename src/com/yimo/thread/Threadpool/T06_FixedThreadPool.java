package com.yimo.thread.Threadpool;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class T06_FixedThreadPool {

    static class ComputePrime implements Callable{

        private int s,e;

        public ComputePrime(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> r = getPrime(s,e);
            return r;
        }
    }

    static List<Integer> getPrime(int s, int e){

        List<Integer> result = new ArrayList<Integer>();

        for (int i = s; i <= e; i++) {
            if(isPrime(i)) result.add(i);
        }

        return result;
    }

    static boolean isPrime(int num){
        for (int i = 2; i <= num/2; i++) {
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start, end;
        start = System.currentTimeMillis();
        List<Integer> r = getPrime(1,250000);
        end = System.currentTimeMillis();
        System.out.println("end - start : " + (end - start));
        System.out.println(r.size());


        ExecutorService fixThreadPool = Executors.newFixedThreadPool(5);

        ComputePrime c1 = new ComputePrime(1,50000);
        ComputePrime c2 = new ComputePrime(50001,100000);
        ComputePrime c3 = new ComputePrime(100001,150000);
        ComputePrime c4 = new ComputePrime(150001,200000);
        ComputePrime c5 = new ComputePrime(200001,250000);

        Future<List<Integer>> f1 = fixThreadPool.submit(c1);
        Future<List<Integer>> f2 = fixThreadPool.submit(c2);
        Future<List<Integer>> f3 = fixThreadPool.submit(c3);
        Future<List<Integer>> f4 = fixThreadPool.submit(c4);
        Future<List<Integer>> f5 = fixThreadPool.submit(c5);

        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        f5.get();
        end = System.currentTimeMillis();
        System.out.println(end - start);

        fixThreadPool.shutdown();

    }

}
