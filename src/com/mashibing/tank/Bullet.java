package com.mashibing.tank;

import java.awt.*;

// 定义子弹类
public class Bullet {
    // 默认速度
    private static final int SPEED = 2;
    // 子弹的高度、宽度
    private static final int WIDTH = 20, HEIGHT = 20;
    // 初始位置
    private int x,y;
    // 子弹方向
    private Dir dir;

    public Bullet() {
    }

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    /**
     * 这个方法让子弹自己画出自己
     */
    public void paint(Graphics g){
        Color c = g.getColor();
        // 更改子弹颜色
        g.setColor(Color.RED);

        // 这里画出一个圆
        g.fillOval(x,y,WIDTH,HEIGHT);

        g.setColor(c);

        move();
    }

    private void move(){

        // 这里是子弹的移动，注意子弹没有停止状态
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
