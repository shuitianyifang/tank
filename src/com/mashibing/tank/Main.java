package com.mashibing.tank;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        // 初始化敌方坦克
        for (int i = 0; i < 5; i++) {
            tf.tanks.add(new Tank(50 + i*80, 200, Dir.DOWN, tf));
        }

        /**
         * 这个循环语句的作用：每隔 50 毫秒，就调用一个 paint() 方法
         * 完成方块 自动移动 的作用（用于坦克的移动）
         */
        while (true){
            Thread.sleep(50);

            // repaint() 方法会自动调用 paint() 方法，完成画图
            // 会首先调用 update()，再调用 paint()
            tf.repaint();
        }
    }

}
