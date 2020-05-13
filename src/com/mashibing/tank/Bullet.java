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
    // 设置子弹所属的敌、友
    private Group group = Group.BAD;

    // 默认速度
    private static final int SPEED = 6;
    // 子弹的高度、宽度
    // 改为获得图片的具体值，用于计算子弹打出位置
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    // 子弹是否存在（解决子弹出游戏窗口时，自动删除）
    // true=存在状态， false=消亡状态
    private boolean living = true;

    // 解决子弹和坦克碰撞时，Rectangle 会一直 new 的情况；我们这里先定义好一个 Rectangle
    Rectangle rect = new Rectangle();


    // 这里的get、set方法用于设置子弹的敌、友
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet() {
    }

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        // 在 Bullet 初始化时，同时初始化好 Rectangle。
        // 然后在move()方法中，随着子弹移动
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        tf.bullets.add(this);

    }

    /**
     * 这个方法让子弹自己画出自己
     */
    public void paint(Graphics g){

        // 当子弹处于消亡状态时，将其从子弹List中移除
        if(!living){
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

        // 这里这样处理使得，Rectangle这个矩形不断的在跟着 子弹 移动
        rect.x = this.x;
        rect.y = this.y;

        // 子弹出窗口后，将其设置为消亡状态
        if(x<0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT){
            living = false;
        }
    }

    // 碰撞的逻辑
    public void collideWith(Tank tank) {
        // 如果子弹和坦克所属阵营相同，则不做碰撞检测
        if(this.group == tank.getGroup()){
            return;
        }

        // 分别得出 子弹和坦克 的矩形 TODO：用一个 Rectangle 来记录子弹的位置
        // Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        // Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);

        // 判断这两个矩形是否相交，相交即为碰撞
        if(rect.intersects(tank.rect)){
            tank.die();
            this.die();

            // 计算爆炸的位置
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;

            // 碰撞时增加爆炸效果，设置为坦克的中心
            tf.explodes.add(new Explode(eX, eY, tf));
        }
    }

    // 子弹死亡方法
    private void die() {
        this.living = false;
    }
}
