package com.mashibing.tank;

import java.io.IOException;
import java.util.Properties;

// 这个类专门在程序启动时，load 配置文件
public class PropertyMgr {

    static Properties props = new Properties();

    // 将 config 配置文件加载到内存中
    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 这个方法使得以后我们要获得 config 配置文件中的某个值时，只需 PropertyMgr.get(key) 即可
    public static Object get(String key){
        if(props == null){
            return null;
        }
        return props.get(key);
    }

    public static void main(String[] args) {
        System.out.println(PropertyMgr.get("initTankCount"));
    }

}
