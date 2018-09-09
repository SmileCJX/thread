package pers.caijx.thread.t7;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * 保证可见性的前提
 * 多个线程拿到的是同一把锁，否则是保证不了的
 * Created by caijx on 2018/9/9/009.
 */
public class Demo {

    public int a = 1;

    public synchronized int getA() {
        return a;
    }

    public synchronized void setA(int a) {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.a = a;
    }

    public static void main(String[] args) {
        Demo demo = new Demo();

        //不是一个很好的例子，可能get先执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.a = 10;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(demo.a);
            }
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("最终的结果为：" + demo.getA());
    }
}
