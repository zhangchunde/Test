package com.yimo.thread.threadLock;

import java.util.concurrent.Phaser;

public class T09_TestPhaser2 {

    static ProjectRunPhaser projectRunPhaser = new ProjectRunPhaser();
    public static void main(String[] args) {
        projectRunPhaser.bulkRegister(0);

        new Thread(new ProjectRunPhaser.Employee("PM")).start();
        do{

        }while(projectRunPhaser.getPhase() <= 0);

        new Thread(new ProjectRunPhaser.Employee("SA")).start();

        do{

        }while(projectRunPhaser.getPhase() <= 1);
        new Thread(new ProjectRunPhaser.Employee("DEV")).start();

        do{

        }while(projectRunPhaser.getPhase() <= 3);
        new Thread(new ProjectRunPhaser.Employee("TEST")).start();
    }
    static class ProjectRunPhaser extends Phaser {

        @Override
        protected boolean onAdvance(int i, int i1) {
            switch (i){
                case 0:
                    System.out.println("项目启动阶段所有人工作结束[0]，共" + i1 + "人参与。");
                    return false;
                case 1:
                    System.out.println("项目调研阶段所有人工作结束[1]，共" + this.getArrivedParties() + "人参与。");
                    return false;
                case 2:
                    System.out.println("项目设计阶段所有人工作结束[2]，共" + this.getArrivedParties() + "人参与。");
                    return false;
                case 3:
                    System.out.println("项目开发阶段所有人工作结束[3]，共" + this.getArrivedParties() + "人参与。");
                    return false;
                case 4:
                    System.out.println("项目测试阶段所有人工作结束[4]，共" + i1 + "人参与。");
                    return false;
                case 5:
                    System.out.println("项目上线阶段所有人工作结束[5]，共" + i1 + "人参与。");
                    return true;
                default:
                    return true;
            }
        }

        static class Employee implements Runnable{
            String empType;

            public Employee(String s){
                empType = s;
            }

            @Override
            public void run() {
                projectStart();
                demond();
                design();
                dev();
                test();
                online();
            }

            public void projectStart(){
                if("PM".indexOf(this.empType) >= 0){
                    projectRunPhaser.register();
                    System.out.println(this.empType + "入场，开始工作...");
                    int random = (int)(Math.random()*100);
                    try {
                        Thread.sleep(random);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(this.empType+"的项目启动工作完成，耗时" + random + "ms。等待下一工作阶段。");
                    projectRunPhaser.arriveAndAwaitAdvance();
                }
            }

            public void demond(){
                if("SA".equals(this.empType)){
                    projectRunPhaser.register();
                    System.out.println(this.empType + "入场，开始工作...");
                }

                if("PM,SA".indexOf(this.empType) >= 0){
                    int random = (int)(Math.random()*100);
                    try {
                        Thread.sleep(random);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(this.empType+"的项目调研工作完成，耗时" + random + "ms。等待下一工作阶段。");
                    projectRunPhaser.arriveAndAwaitAdvance();
                }
            }

            public void design(){
                if("DEV".equals(this.empType)){
                    projectRunPhaser.register();
                    System.out.println(this.empType + "入场，开始工作...");
                }

                if("PM,SA,DEV".indexOf(this.empType) >= 0){
                    int random = (int)(Math.random()*100);
                    try {
                        Thread.sleep(random);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(this.empType+"的项目设计工作完成，耗时" + random + "ms。等待下一工作阶段。");
                    if("SA".equals(this.empType)){
                        projectRunPhaser.arriveAndDeregister();
                        System.out.println(this.empType + "退场，结束工作。");
                    }else {
                        projectRunPhaser.arriveAndAwaitAdvance();
                    }
                }
            }


            public void dev(){
                if("PM,DEV".indexOf(this.empType) >= 0){
                    int random = (int)(Math.random()*100);
                    try {
                        Thread.sleep(random);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(this.empType+"的项目开发工作完成，耗时" + random + "ms。等待下一工作阶段。");

                    projectRunPhaser.arriveAndAwaitAdvance();

                }
            }

            public void test(){
                if("TEST".equals(this.empType)){
                    projectRunPhaser.register();
                    System.out.println(this.empType + "入场，开始工作...");
                }

                if("PM,DEV,TEST".indexOf(this.empType) >= 0){
                    int random = (int)(Math.random()*100);
                    try {
                        Thread.sleep(random);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(this.empType+"的项目测试工作完成，耗时" + random + "ms。等待下一工作阶段。");

                    projectRunPhaser.arriveAndAwaitAdvance();

                }
            }

            public void online(){
                if("PM,DEV,TEST".indexOf(this.empType) >= 0){
                    int random = (int)(Math.random()*100);
                    try {
                        Thread.sleep(random);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(this.empType+"的项目上线工作完成，耗时" + random + "ms。等待下一工作阶段。");

                    projectRunPhaser.arriveAndAwaitAdvance();

                }
            }
        }
    }
}
