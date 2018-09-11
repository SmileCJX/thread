package pers.caijx.thread.t10;

/**
 * 自定义锁
 * Created by caijx on 2018/9/11/011.
 */
public class Sequence {

    private int value;

    MyLock myLock = new MyLock();

    private int getNext() {
        myLock.lock();
        value++;
        myLock.unlock();
        return value;
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(sequence.getNext());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(sequence.getNext());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(sequence.getNext());
                }
            }
        }).start();
    }
}
