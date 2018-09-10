package pers.caijx.thread.t8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by caijx on 2018/9/9/009.
 */
public class Sequence {

    //使用AtomicInteger原子类进行操作
    private AtomicInteger value = new AtomicInteger(0);

    private int[] s = {2,1,5,6};

    //原子更新数组
    private AtomicIntegerArray a = new AtomicIntegerArray(s);

    //原子更新抽象类型
    AtomicReference<User> user = new AtomicReference<>();

    //原子更新字段
    AtomicIntegerFieldUpdater<User> age = AtomicIntegerFieldUpdater.newUpdater(User.class,"age");

    /**
     * @return
     */
    public int getNext() {
        User user = new User();
        System.out.println(age.getAndIncrement(user));
        System.out.println(age.getAndIncrement(user));
        System.out.println(age.getAndIncrement(user));
        a.getAndIncrement(2);
        a.getAndAdd(2,10);
//        return value.getAndIncrement();
        return value.getAndAdd(10);
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + sequence.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                }
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    System.out.println(Thread.currentThread().getName() + " " + sequence.getNext());
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    System.out.println(Thread.currentThread().getName() + " " + sequence.getNext());
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
    }
}
