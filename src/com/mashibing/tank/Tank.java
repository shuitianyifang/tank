package com.mashibing.tank;

import java.awt.*;

/**
 * 定义坦克类，确定坦克的基本属性，如果需要新的，直接new这个类即可
 */
public class Tank {

    // 初始位置
    int x = 200, y = 200;
    // 设置默认方向为向下
    Dir dir = Dir.DOWN;
    // 设置坦克默认速度
    private static final int SPEED = 10;

    public Tank() {
    }

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    // Dir 的 get、set 方法用于设置坦克的方向方法 setMainTankDir()
    public Dir getDir() {
        return dir;
    }
    public void setDir(Dir dir) {
        this.dir = dir;
    }

    /**
     * 这个方法让坦克自己画自己
     */
    public void paint(Graphics g){
        // 这里画出一个黑方块
        g.fillRect(x,y,50,50);

        // 根据坦克的方向进行坦克的移动
        // 这里定义每次“画图”的长度 （每次为10）
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }
}
