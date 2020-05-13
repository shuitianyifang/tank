package factory.example;

/**
 * 2. 这种方式用来定制生产过程
 *    Car 工厂，其他的 工厂类类似
 */
public class CarFactory {

    public Car create(){
        System.out.println("a car created");
        return new Car();
    }

}
