package com.mashibing.tank;

import java.awt.*;

/**
 * 定义坦克类，确定坦克的基本属性，如果需要新的，直接new这个类即可
 */
public class Tank {

    // 初始位置
    private int x,y;
    // 设置默认方向为向下
    private Dir dir = Dir.DOWN;
    // new 坦克时，将哪个窗口需要用到坦克，那个窗口传入
    private TankFrame tf = null;

    // 设置坦克默认速度
    private static final int SPEED = 5;
    // 设置坦克是否移动, true=移动，false=不动
    private boolean moving = false;

    public Tank() {
    }

    // 构造方法中添加了 TankFrame 类，使得本类持有了这个类的引用，那么就可以往 TankFrame 中添东西
    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }


    // Dir 的 get、set 方法用于设置坦克的方向方法 setMainTankDir()
    public Dir getDir() {
        return dir;
    }
    public void setDir(Dir dir) {
        this.dir = dir;
    }


    public boolean isMoving() {
        return moving;
    }
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    /**
     * 这个方法让坦克自己画自己
     */
    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        // 这里画出一个方块
        g.fillRect(x,y,50,50);
        g.setColor(c);

        move();
    }

    private void move(){

        if(!moving) return;

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

    public void fire() {
        tf.bullets.add( new Bullet(this.x, this.y, this.dir, this.tf) );
    }

}
