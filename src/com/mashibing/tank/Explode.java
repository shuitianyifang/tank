package com.mashibing.tank;

import java.awt.*;

// 定义子弹类
public class Explode {

    private int x, y;
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private boolean living = true;
    TankFrame tf = null;

    // 画爆炸的第几张图片
    private int step = 0;

    public Explode() {
    }

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        // 爆炸声音
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }


    // 一张一张按顺序的画出爆炸的16张图片
    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);

        if(step >= ResourceMgr.explodes.length){

            // 爆炸完成后，当前爆炸位置不再爆炸
            tf.explodes.remove(this);
        }
    }


}
