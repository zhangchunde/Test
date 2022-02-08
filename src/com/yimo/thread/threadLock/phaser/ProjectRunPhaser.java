package com.yimo.thread.threadLock.phaser;

import java.util.concurrent.Phaser;

public class ProjectRunPhaser extends Phaser {

    @Override
    protected boolean onAdvance(int i, int i1) {
        switch (i){
            case 0:
                System.out.println("项目启动阶段所有人工作结束，共" + i1 + "人参与。");
                return false;
            case 1:
                System.out.println("项目调研阶段所有人工作结束，共" + i1 + "人参与。");
                return false;
            case 2:
                System.out.println("项目设计阶段所有人工作结束，共" + i1 + "人参与。");
                return false;
            case 3:
                System.out.println("项目开发阶段所有人工作结束，共" + i1 + "人参与。");
                return false;
            case 4:
                System.out.println("项目测试阶段所有人工作结束，共" + i1 + "人参与。");
                return false;
            case 5:
                System.out.println("项目上线阶段所有人工作结束，共" + i1 + "人参与。");
                return true;
            default:
                return true;
        }
    }
}
