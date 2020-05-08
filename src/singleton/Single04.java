package singleton;

/**
 * 懒汉式 配合 synchronized 加锁
 * 使用 双重判断加锁写法，增加效率
 */
public class Single04 {

    // volatile 防止语句重排
    private static volatile Single04 INSTANCE;

    // 私有化构造方法，让其他人不能 new
    private Single04(){

    }

    // 代码块加锁 synchronized
    // 注意，一定要再判断一次，否则依然会产生多线程问题
    public static Single04 getInstance(){

        if(INSTANCE == null){
            synchronized (Single04.class){
                // 再判断一次
                if(INSTANCE == null){
                    // 模拟进行延时
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Single04();
                }
            }

        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        // 检验是否产生了不同的对象
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Single04.getInstance().hashCode());
            }).start();
        }
    }

}
