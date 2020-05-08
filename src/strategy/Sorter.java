package strategy;

public class Sorter<T> {

    // 1.只能对 int 类型进行排序
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;

            for (int j = i+1; j < arr.length; j++) {
                minPos = arr[j] < arr[minPos] ? j : minPos;
            }

            swap(arr,i,minPos);
        }
    }
    static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    // 2.只能对 Cat 类对象进行排序
    public static void sort(Cat[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;

            for (int j = i+1; j < arr.length; j++) {
                minPos = arr[j].compareTo(arr[minPos]) == -1  ? j : minPos;
            }

            swap(arr,i,minPos);
        }
    }
    static void swap(Cat[] arr, int i, int j){
        Cat temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 3. 对任意类型都可以进行排序的方法 重点 Comparable
     *    任何要使用该类进行排序的类，都请实现 Comparable<T> 接口
     *    并且重写 compareTo() 方法，自己定制排序规则
     */
    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;

            for (int j = i+1; j < arr.length; j++) {
                minPos = arr[j].compareTo(arr[minPos]) == -1  ? j : minPos;
            }

            swap(arr,i,minPos);
        }
    }
    static void swap(Comparable[] arr, int i, int j){
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 4. 对任意类型进行排序，要求传入 排序规则类（该类实现了 Comparator 接口）
     *    这种方式，就是策略模式
     */
    public void sort(T [] arr, Comparator<T> comparator) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;

            for (int j = i+1; j < arr.length; j++) {
                minPos = comparator.compare(arr[j], arr[minPos]) == -1 ? j : minPos;
            }

            swap(arr,i,minPos);
        }
    }
     void swap(T [] arr, int i, int j){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }





}
