package com.mashibing.tank;

/**
 * 各种开火方式类所实现的接口，当要添加新的开火方式时，实现这个接口，写出开火代码
 * 然后在 Tank 类中修改即可
 * 这里就用到的 策略模式
 */
public interface FireStrategy {

    void fire(Tank t);

}
