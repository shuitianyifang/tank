package com.mashibing.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// 继承Frame类
public class TankFrame extends Frame {

    int x = 200, y = 200;

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

        // 这样不断隐藏frame，点出frame，就会发现方块移动了
        x += 10;
        y += 10;
    }


    /**
     * 定义一个内部类，来处理对键盘事件的监听，继承类 KeyAdapter
     */
    class MyKeyListener extends KeyAdapter{

        // 这个方法是在一个键被按下去的时候调用
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("key pressed");
        }

        // 这个方法是在一个键弹起的时候调用
        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("key released");
        }
    }

}
