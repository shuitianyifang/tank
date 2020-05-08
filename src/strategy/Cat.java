package strategy;

public class Cat implements Comparable<Cat> {

    int weight, height;

    public Cat() {
    }

    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    // 比较大小的方法
    public int compareTo(Cat c){

        if(this.weight < c.weight){
            return -1;
        }else if (this.weight > c.weight){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }
}
