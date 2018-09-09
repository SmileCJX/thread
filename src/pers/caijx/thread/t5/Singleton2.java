package pers.caijx.thread.t5;

/**
 * Created by caijx on 2018/9/9/009.
 */
public class Singleton2 {

    private Singleton2() {

    }

    private static volatile Singleton2 instance;

    /**
     * 双重检查加锁制
     * @return
     */
    public static Singleton2 getInstance() {
        // 自旋 相当于 while(true)，会空转CPU
        if (null == instance) {
            synchronized (Singleton2.class) {
                if (null == instance) {
                    instance = new Singleton2();  //指令重排序，指令重排序将会导致以下三个步骤无法按照正常顺序执行
                    //1.申请一块内存空间
                    //2.在这块空间里实例化对象
                    //3.instance的引用指向这块空间地址
                }
            }
        }
        return instance;
    }
}
