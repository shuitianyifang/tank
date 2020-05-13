package factory.example;

/**
 * 这里我们创建一个简单工厂类
 * 缺点：可扩展性不好，因为当要生产新的时，我们又要写新的
 * 所以这一种我们不用
 */
public class SimpleVehicleFactory {

    // 生产 Car
    public Car createCar(){
        return new Car();
    }

    // 生产 Broom
    public Broom createBroom(){
        return new Broom();
    }

}
