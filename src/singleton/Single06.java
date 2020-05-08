package singleton;

/**
 * 枚举单例（最完美的写法）
 * 不仅可以解决线程同步，还可以防止反序列化
 * 原因在于，枚举没有构造方法，没有办法将其实例化
 */
public enum  Single06 {

    INSTANCE;

    public static void main(String[] args) {
        // 检验是否产生了不同的对象
        for (int i = 0; i < 100; i++) {
            new Thread(()->{

                // 直接使用 Single06.INSTANCE
                System.out.println(Single06.INSTANCE.hashCode());
            }).start();
        }
    }

}
