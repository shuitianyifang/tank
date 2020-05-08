package singleton;

/**
 * 懒汉式 配合 synchronized 加锁
 * 虽然解决了安全问题，但是效率大大降低
 */
public class Single03 {

    private static Single03 INSTANCE;

    // 私有化构造方法，让其他人不能 new
    private Single03(){

    }

    // 方法上加锁 synchronized
    public static synchronized Single03 getInstance(){

        if(INSTANCE == null){
            // 模拟进行延时
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Single03();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        // 检验是否产生了不同的对象
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Single03.getInstance().hashCode());
            }).start();
        }
    }

}
