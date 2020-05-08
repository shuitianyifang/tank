package singleton;

/**
 * 懒汉式
 * 虽然达到了按需初始化的目的，但却带来线程不安全的问题
 */
public class Single02 {

    private static Single02 INSTANCE;

    // 私有化构造方法，让其他人不能 new
    private Single02(){

    }

    // 其他地方需要这个对象时，只能通过 getInstance()
    public static Single02 getInstance(){

        if(INSTANCE == null){
            // 模拟进行延时
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Single02();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        // 检验是否产生了不同的对象
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Single02.getInstance().hashCode());
            }).start();
        }
    }

}
