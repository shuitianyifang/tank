package factory.abstracts;

/**
 * 为了解决“族”问题，我们采用抽象工厂类
 * 然后让各种 食物、武器、出行方法 类 各自继承 Food、Weapon、Vehicle 抽象类
 * 然后让 现代工厂类、魔法工厂类 继承这个类
 */
public abstract class AbstractFactory {

    // 抽象的产品
    abstract Food createFood();
    abstract Vehicle createVehicle();
    abstract Weapon createWeapon();

}
