package com.mashibing.tank;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        // 从配置文件中取得坦克数量
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        // 初始化敌方坦克的数量、位置、方向、所属阵营
        for (int i = 0; i < initTankCount; i++) {
            tf.tanks.add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD, tf));
        }

        // 游戏背景音效
        // new Thread(()->new Audio("audio/war1.wav").loop()).start();

        /**
         * 这个循环语句的作用：每隔 50 毫秒，就调用一个 paint() 方法
         * 完成方块 自动移动 的作用（用于坦克的移动）
         */
        while (true){
            Thread.sleep(25);

            // repaint() 方法会自动调用 paint() 方法，完成画图
            // 会首先调用 update()，再调用 paint()
            tf.repaint();
        }
    }

}
