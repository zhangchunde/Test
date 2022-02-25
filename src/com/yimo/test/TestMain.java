package com.yimo.test;

public class TestMain {

    static int hashIncrement = 0x61c88647;

    public static void main(String[] args){
//        hashCode(2);
//        hashCode(4);
//        hashCode(8);
//        hashCode(16);
//        hashCode(32);
//        hashCode(64);

//        computeHash(16);

//        computeHashLong(16);

        testInt();

    }

    public static void hashCode(int i){
        int increment = computeHash(i);
        for (int j = 0; j < i; j++) {

            System.out.print(((j + 1) * increment) & (i - 1));
            System.out.print(" ");

        }
        System.out.println();
    }

    public static int computeHash(int t){
        int result = (int)((Math.sqrt(5) - 1) * (1L<<(t-1)));
        System.out.println(result);
        return result;
    }

    public static void computeHashLong(int t){
//        int result = (int)((Math.sqrt(5) - 1) * (1L<<31));
        double d = (Math.sqrt(5) - 1) / 2;
        System.out.println((int)(d * (1L << 32)));
    }

    public static void testInt(){
        int i = 1 << 33;
        print(-1);
        print(i);
        print(i/2);
    }

    public static void print(int num){
        for(int i=31;i>=0;i--){
            System.out.print((num & 1 << i) == 0 ? "0":"1");
        }
        System.out.println();
    }

}
