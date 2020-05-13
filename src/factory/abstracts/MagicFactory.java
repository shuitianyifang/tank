package factory.abstracts;

/**
 * 魔法工厂
 * 实现了 抽象工厂类 AbstractFactory
 */
public class MagicFactory extends AbstractFactory {
    @Override
    Food createFood() {
        return new Mushroom();
    }

    @Override
    Vehicle createVehicle() {
        return new Broom();
    }

    @Override
    Weapon createWeapon() {
        return new MagicStick();
    }
}
