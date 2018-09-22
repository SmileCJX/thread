package pers.caijx.thread.ta6;

import java.util.concurrent.TimeUnit;

/**
 * wait和notify必须放在同步代码块或者同步方法
 * Created by caijx on 2018/9/22/022.
 */
public class Demo3 {

    private volatile int signal;

    public synchronized int getSignal() {
        System.out.println(Thread.currentThread().getName() + " 方法执行了...");
        if (signal != 1) {
            try {
                wait(); // 走到wait的时候，synchronized这把锁已经被释放掉了
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 方法执行完毕...");
        return signal;
    }

    public synchronized void setSignal() {
        this.signal = 1;
//        notify(); // notify方法会随机叫醒一个处于wait状态的线程，拿到锁
        notifyAll(); // notifyAll叫醒所有的处于wati线程，争夺到时间片的线程只有一个
        System.out.println("叫醒线程叫醒之后休眠开始...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //等到方法结束后才释放锁
    }

    public static void main(String[] args) {
        Demo3 d = new Demo3();
        Target1 t1 = new Target1(d);
        Target2 t2 = new Target2(d);
        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t1).start();
    }

}
