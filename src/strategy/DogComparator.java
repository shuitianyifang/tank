package strategy;

/**
 * Dog 的排序规则类， 在对 Dog 进行排序时，被要求传入
 * 泛型决定排序的对象， compare决定排序的规则
 * 这种类，可以有很多个，而它们的排序规则都不相同
 */
public class DogComparator implements Comparator<Dog> {

    @Override
    public int compare(Dog o1, Dog o2) {
        if(o1.food < o2.food){
            return -1;
        }else if (o1.food > o2.food){
            return 1;
        }else {
            return 0;
        }
    }
}
