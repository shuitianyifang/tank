package com.mashibing.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 这个类的作用：在游戏一开始时，把所有要用到的图片资源 load 到内存中去
 */
public class ResourceMgr {

    public static BufferedImage tankL, tankU, tankR, tankD;
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;

    // ResourceMgr 类被load到内存中时，执行static
    static {
        try {
            /**
             * 获取相对路径
             * 1.ResourceMgr.class.getClassLoader() 获取到将ResourceMgr类load到内存的加载器
             * 2.让这个加载器获取到文件
             */
            tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));

            bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
