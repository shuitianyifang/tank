package com.mashibing.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// 继承Frame类
public class TankFrame extends Frame {

    int x = 200, y = 200;

    // 设置默认方向为向下
    Dir dir = Dir.DOWN;

    // 设置坦克默认速度
    private static final int SPEED = 10;

    // 构造方法中初始化一些属性
    public TankFrame(){
        setSize(800,600);
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
     * 重写paint方法,这个方法，在父类Container中
     * Graphics相当于一支画笔，存在于系统中，它在 frame 中“画画”
     */
    @Override
    public void paint(Graphics g){
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
                default:
                    break;
            }

            setMainTankDir();
        }

        // 这个方法用于改变坦克行进的方向
        private void setMainTankDir(){
            if(bL) dir = Dir.LEFT;
            if(bU) dir = Dir.UP;
            if(bR) dir = Dir.RIGHT;
            if(bD) dir = Dir.DOWN;
        }

    }

}
