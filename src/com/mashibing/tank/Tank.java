package com.mashibing.tank;

import java.awt.*;
import java.util.Random;

/**
 * 定义坦克类，确定坦克的基本属性，如果需要新的，直接new这个类即可
 */
public class Tank {

    // 初始位置
    int x,y;
    // 设置默认方向为向下
    Dir dir = Dir.DOWN;
    // new 坦克时，将哪个窗口需要用到坦克，那个窗口传入
    TankFrame tf = null;
    // 设置坦克所属的敌、友
    Group group = Group.BAD;

    // 坦克的高度、宽度改为获得图片具体值，用于计算子弹打出位置
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    // 随机数，用来敌方坦克随机 移动 和 打子弹
    private Random random = new Random();
    // 设置坦克默认速度
    private static final int SPEED = 2;
    // 设置坦克是否移动, true=移动，false=不动
    private boolean moving = true;
    // 定义坦克是否存在属性，true=存在，false=挂了
    private boolean living = true;

    // 解决子弹和坦克碰撞时，Rectangle 会一直 new 的情况；我们这里先定义好一个 Rectangle
    Rectangle rect = new Rectangle();

    /**
     * 这里将 DefaultFireStrategy 作为成员变量传入进来，
     * 因为在 fire(FireStrategy) 时，每次都会 new，
     * 所以应该把 FireStrategy 做成单例，或者像这样当做成员变量
     */
    // FireStrategy fs = new DefaultFireStrategy();
    // FireStrategy fs = new FourDirFireStrategy();
    FireStrategy fs;

    public Tank() {
    }

    // 构造方法中添加了 TankFrame 类，使得本类持有了这个类的引用，那么就可以往 TankFrame 中添东西
    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        // 在 Tank 初始化时，同时初始化好 Rectangle。
        // 然后在move()方法中，随着坦克移动
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        // 在这里处理己方和敌人的开火方式
        if(group == Group.GOOD){
            // fs = new FourDirFireStrategy();
            // 修改为从配置文件中获取，更加的灵活
            String goodFSName = (String) PropertyMgr.get("goodFS");
            try {
                fs = (FireStrategy) Class.forName(goodFSName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            fs = new DefaultFireStrategy();
        }
    }

    // 这里的get、set方法用于设置坦克的敌、友
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }

    // x,y 的 get、set方法，用于子弹和敌方坦克碰撞的检测
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
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
        // Color c = g.getColor();
        // g.setColor(Color.YELLOW);
        // // 这里画出一个方块
        // g.fillRect(x,y,50,50);
        // g.setColor(c);

        // 如果为 false=挂了，就不画了，将坦克从List中移除掉
        if(!living){
            tf.tanks.remove(this);
        }

        // 这里在画出坦克时，使用已经加载到内存中的坦克图片
        switch (dir){
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y,null);
                break;
        }

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

        // 设定敌方坦克打子弹的频率（根据移动来打子弹），这样自己就不会自动打子弹了
        if(this.group == Group.BAD && random.nextInt(100) > 95){
            this.fire();
        }
        
        // 如果是敌方坦克，则设置随机移动以及频率
        if(this.group == Group.BAD && random.nextInt(100) > 95){
            randomDir();
        }

        // 边界检测（让坦克不会越出游戏窗口）
        boundsCheck();

        // 这里这样处理使得，Rectangle这个矩形不断的在跟着 坦克 移动
        // 且应该放在 边界检测 之后
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if(this.x < 2) x = 2;
        if(this.y < 28) y = 28;
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2)
            x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2)
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    // 随机移动方法
    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    // 坦克发射子弹的方法
    public void fire() {
        fs.fire(this);
    }

    // 坦克死亡方法（不画了）
    public void die() {
        this.living = false;
    }
}
