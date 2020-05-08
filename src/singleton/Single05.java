package singleton;

/**
 * 静态内部类方法（完美的方法之一）
 * JVM 保证单例
 * 加载外部类时不会加载内部类，这样可以实现 懒加载
 */
public class Single05 {

    // 私有化构造方法，让其他人不能 new
    private Single05(){

    }

    // 在静态内部类中 实例化外部类 INSTANCE
    private static class Single05Holder{
        private final static Single05 INSTANCE = new Single05();
    }

    // 调用getInstance()时，才会加载静态内部类，从而从中获得 INSTANCE
    public static Single05 getInstance(){
        return Single05Holder.INSTANCE;
    }

    public static void main(String[] args) {
        // 检验是否产生了不同的对象
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Single05.getInstance().hashCode());
            }).start();
        }
    }

}
