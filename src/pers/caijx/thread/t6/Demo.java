package pers.caijx.thread.t6;

/**
 * 锁的重入
 * Created by caijx on 2018/9/9/009.
 */
public class Demo {

    public synchronized void a() {
        System.out.println("a");
        b();
    }

    public synchronized void b() {
        System.out.println("b");
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Demo d = new Demo();
                d.a();
            }
        }).start();
    }
}
