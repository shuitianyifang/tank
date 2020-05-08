package strategy;

/**
 * 我们发现，虽然实现 Comparable<> 接口，可以比较任意的类型。
 * 但是，比较的方法 compareTo() 还是不灵活。（因为，想换一种规则比较时，需要重新写方法）
 * 所以，这里我们要使用 Comparator<T> 接口
 * 这里，就是一种 策略模式
 */
public interface Comparator<T> {

    /**
     * o1 < o2时，返回 -1；  o1 > o2时，返回 1；  o1 = o2时，返回 0  （从小到大）
     * 反之，（从大到小）
     */
    int compare(T o1, T o2);

}
