package singleton;

/**
 * 饿汉式
 * 类加载到内存后，就立刻实例化一个单例，JVM保证线程安全
 * 简单实用，推荐使用
 * 唯一缺点：不管用到与否，类装载时就完成实例化
 */
public class Single01 {

    // 类加载到内存后，就立刻实例化一个 INSTANCE
    private static final Single01 INSTANCE = new Single01();

    // 私有化构造方法，让其他人不能 new
    private Single01(){

    }

    // 其他地方需要这个对象时，只能通过 getInstance()
    public static Single01 getInstance(){

        // 不管调用多少次，INSTANCE 都是那唯一的一个
        return INSTANCE;
    }

    public static void main(String[] args) {
        Single01 s1 = Single01.getInstance();
        Single01 s2 = Single01.getInstance();

        System.out.println(s1==s2); // 得出为true，说明是同一个对象
    }

}
