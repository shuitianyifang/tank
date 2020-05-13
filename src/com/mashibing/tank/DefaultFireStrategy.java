package com.mashibing.tank;

// 默认开火方式
public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        // 计算子弹打出的起始位置，设置为坦克的中心
        int bX = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        new Bullet(bX, bY, t.dir, t.group, t.tf);

        // 如果是自己的坦克，打子弹出声音
        if(t.group == Group.GOOD){
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
    }

}