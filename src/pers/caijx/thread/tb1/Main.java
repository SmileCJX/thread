package pers.caijx.thread.tb1;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by caijx on 2018/9/23/023.
 */
public class Main {

    private volatile static int i = 0;

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    myQueue.add("test" + (i++));
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    myQueue.remove();
                }
            }
        }).start();
    }
}
