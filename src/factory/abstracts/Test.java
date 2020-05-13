package factory.abstracts;

/**
 * 不同的各族
 * 我们发现，当要换族时，需要重写一大堆的东西，如下：现代人、魔法世界人
 * 但是当使用抽象工厂类时，只需更换工厂，就能更换为另一族
 */
public class Test {

    public static void main(String[] args) {
        // // 现代人
        // Car c = new Car();
        // c.go();
        // AK47 w = new AK47();
        // w.shoot();
        // Bread b = new Bread();
        // b.printName();

        // // 魔法世界人
        // Broom br = new Broom();
        // br.go();
        // MagicStick ma = new MagicStick();
        // ma.shoot();
        // Mushroom mu = new Mushroom();
        // mu.printName();


        // 这里我哦们只需 使用不同的工厂，即可变成那一族的
        // AbstractFactory f = new ModernFactory();
        AbstractFactory f = new MagicFactory();

        // 这以下的代码都不需要动
        Vehicle vehicle = f.createVehicle();
        vehicle.go();
        Weapon weapon = f.createWeapon();
        weapon.shoot();
        Food food = f.createFood();
        food.printName();
    }

}
