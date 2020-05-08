package strategy;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        // 1. int 排序
        // int [] a = {9,2,3,5,7,1,4};
        // Sorter sorter = new Sorter();
        // sorter.sort(a);
        // System.out.println(Arrays.toString(a));


        // 2. Cat 排序（需要在Cat类中定义好排序的方法）
        // Cat [] a = {new Cat(3,3),new Cat(5,5), new Cat(1,1)};
        // Sorter sorter = new Sorter();
        // sorter.sort(a);
        // System.out.println(Arrays.toString(a));


        // 3. Comparable<T> 任意类型的排序
        // Dog [] a = {new Dog(3),new Dog(5), new Dog(1)};
        // Sorter sorter = new Sorter();
        // sorter.sort(a);
        // System.out.println(Arrays.toString(a));


        // 4. Comparator<T> 任意类型的排序，要求传入排序规则类
        Dog [] a = {new Dog(3),new Dog(5), new Dog(1)};
        Sorter<Dog> sorter = new Sorter();
        // 传入排序规则类，可以有很多个这种类（它们的规则都不同，所以，就很灵活）
        sorter.sort(a, new DogComparator());
        System.out.println(Arrays.toString(a));

    }

}
