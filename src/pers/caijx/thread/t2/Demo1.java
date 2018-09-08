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
        System.out.println(getName() + "线程执行了..");
    }

    public static void main(String[] args) {
        Demo1 d1 = new Demo1("first-thread");
        Demo1 d2 = new Demo1("second-thread");
        d1.start();
        d2.start();
    }
}
