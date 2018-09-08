package pers.caijx.thread.t2;

/**
 * Created by Administrator on 2018/9/8/008.
 */
public class Demo1 extends Thread {

    public Demo1(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (!interrupted()) {
            System.out.println(getName() + "线程执行了..");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Demo1 d1 = new Demo1("first-thread");
        Demo1 d2 = new Demo1("second-thread");

//        d1.setDaemon(true); // 守护线程，主线程退出后，无论是否结束均停止
//        d2.setDaemon(true);

        d1.start();
        d2.start();

        d1.interrupt();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
