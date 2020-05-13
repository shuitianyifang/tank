package factory.example;

public class Test {

    public static void main(String[] args) {
        MoveAble m = new CarFactory().create();
        m.go();
    }

}
