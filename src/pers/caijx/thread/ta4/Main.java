package pers.caijx.thread.ta4;

/**
 * Created by caijx on 2018/9/21/021.
 */
public class Main {

    private MyLock2 lock = new MyLock2();

    private int value;

    public int next() {
        lock.lock();
        try {
            Thread.sleep(300);
            return  value++;
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
    }

    public void a() {
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    public void b() {
        lock.lock();
        System.out.println("b");
        lock.unlock();
    }

    public static void main(String[] args) {
        Main m = new Main();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (true) {
                    m.a();
                }
            }
        }).start();
    }
}
