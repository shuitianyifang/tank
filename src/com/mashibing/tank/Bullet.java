package com.mashibing.tank;

import java.awt.*;

// 定义子弹类
public class Bullet {

    // 初始位置
    private int x,y;
    // 子弹方向
    private Dir dir;
    // 和 Tank 类一样，去持有 TankFrame 类的引用
    private TankFrame tf = null;

    // 默认速度
    private static final int SPEED = 10;
    // 子弹的高度、宽度
    // 改为获得图片的具体值
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    // 子弹是否存在（解决子弹出游戏窗口时，自动删除）
    // true=存在状态， false=消亡状态
    private boolean live = true;


    public Bullet() {
    }

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    /**
     * 这个方法让子弹自己画出自己
     */
    public void paint(Graphics g){

        // 当子弹处于消亡状态时，将其从子弹List中移除
        if(!live){
            tf.bullets.remove(this);
        }

        // Color c = g.getColor();
        // g.setColor(Color.RED);
        // // 这里画出一个圆
        // g.fillOval(x,y,WIDTH,HEIGHT);
        // g.setColor(c);

        // 这里画出子弹时，使用已经加载到内存中的子弹图片
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y,null);
                break;
        }

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

        // 子弹出窗口后，将其设置为消亡状态
        if(x<0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT){
            live = false;
        }
    }

}
