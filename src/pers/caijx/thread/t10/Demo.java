package pers.caijx.thread.t10;

/**
 * 可重入锁
 * Created by caijx on 2018/9/11/011.
 */
public class Demo {

    MyLock2 lock = new MyLock2();

    public void a() {
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    public void b() {
        lock.lock();
        System.out.println("b");
        c();
        lock.unlock();
    }

    public void c() {
        lock.lock();
        System.out.println("c");
        lock.unlock();
    }

    public static void main(String[] args) {
        Demo d = new Demo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.a();
            }
        }).start();
    }
}
