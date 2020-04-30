package com.mashibing.tank;

import java.awt.*;
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

}
