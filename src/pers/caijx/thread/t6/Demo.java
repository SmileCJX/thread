package pers.caijx.thread.t6;

/**
 * 锁的重入
 * Created by caijx on 2018/9/9/009.
 */
public class Demo {

    public synchronized void a() {
        System.out.println("a");
//        b();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void b() {
        System.out.println("b");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Demo d = new Demo();  //使用相同对象，锁重入
//        Demo d1 = new Demo();
//        Demo d2 = new Demo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.a();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.b();
            }
        }).start();
    }
}
