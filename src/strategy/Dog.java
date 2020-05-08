package strategy;

public class Dog implements Comparable<Dog> {

    int food;

    public Dog() {
    }

    public Dog(int food) {
        this.food = food;
    }

    // 比较大小的方法
    @Override
    public int compareTo(Dog d) {

        if(this.food < d.food){
            return -1;
        }else if(this.food > d.food){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Dog{" +
                "food=" + food +
                '}';
    }
}
