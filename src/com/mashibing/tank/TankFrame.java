package com.mashibing.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

// 继承Frame类
public class TankFrame extends Frame {
    // 游戏窗口的大小
    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;

    // new出我们需要的坦克
    Tank myTank = new Tank(200,400, Dir.DOWN, Group.GOOD,this);
    // new出敌方的坦克
    List<Tank> tanks = new ArrayList<>();
    // new出子弹
    // Bullet bullet = new Bullet(300,300, Dir.DOWN);
    // 将单个子弹改为数组
    List<Bullet> bullets = new ArrayList<>();

    // new出爆炸
    // Explode e = new Explode(100,100, this);
    // 因为有很多个爆炸，所以更换为List，哪爆画哪
    List<Explode> explodes = new ArrayList<>();

    // 构造方法中初始化一些属性
    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        // 在这里使用自定义的键盘事件监听
        this.addKeyListener(new MyKeyListener());

        // 事件监听，点击小叉关闭窗口
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * 使用双缓冲解决画面闪烁问题（不用太深究）
     * update() 方法会在 paint() 之前被调用
     * 大概原理：在内存中先全部画好，之后再 drawImage() 一下全部画到屏幕上
     */
    Image offScreenImage = null;
    @Override
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);

        // 大小和游戏窗口大小是一致的
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);

        paint(gOffScreen);

        g.drawImage(offScreenImage,0,0,null);
    }


    /**
     * 重写paint方法,这个方法，在父类Container中
     * Graphics相当于一支画笔，存在于系统中，它在 frame 中“画画”
     */
    @Override
    public void paint(Graphics g){
        // 观察子弹数量的测试代码
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量" + bullets.size(),10, 60);
        g.drawString("敌人的数量" + tanks.size(),10, 80);
        g.drawString("爆炸的数量" + explodes.size(),10, 100);
        g.setColor(c);

        // 将画笔直接传给目标坦克，让这个坦克自己画出自己
        // （这样就不用去拉取属性，因为拆开封装好的属性是错误的）
        myTank.paint(g);

        // 同理，画出子弹
        // bullet.paint(g); // 这里是画单个子弹

        // 这里，有多少颗子弹，就画多少遍
        // 注意不要使用 foreach 循环来画，会产生 concurrentModificationException 错误
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        // 画出敌方的坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }


        // 画出爆炸，注意，先爆炸，再下面碰撞检测
        // e.paint(g);
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        // 在这里做子弹与敌方坦克碰撞的检测
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }


        // 这样不断隐藏frame，点出frame，就会发现方块移动了
        // x += 10;
        // y += 10;
    }


    /**
     * 定义一个内部类，来处理对键盘事件的监听，继承类 KeyAdapter
     */
    class MyKeyListener extends KeyAdapter{

        // 给每个方向默认设置为false
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        // 这个方法是在一个键被按下去的时候调用
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            // 每次按下方向键时，对应的值设为 true
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }

            setMainTankDir();

            // 坦克移动的声音
            new Thread(()->new Audio("audio/tank_move.wav").play()).start();
        }

        // 这个方法是在一个键弹起的时候调用
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            // 同理，弹起时设置回 false
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;

                // 按下 ctrl 键，打出子弹
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;

                default:
                    break;
            }

            setMainTankDir();
        }

        // 这个方法用于改变坦克行进的方向
        private void setMainTankDir(){

            // 如果一个键都不按，就不动。否则，就会动
            if(!bL && !bU && !bR && !bD){
                myTank.setMoving(false);
            }else {
                myTank.setMoving(true);
            }

            if(bL) myTank.setDir(Dir.LEFT);
            if(bU) myTank.setDir(Dir.UP);
            if(bR) myTank.setDir(Dir.RIGHT);
            if(bD) myTank.setDir(Dir.DOWN);

        }

    }

}
