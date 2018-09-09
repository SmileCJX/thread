package pers.caijx.thread.t4;

/**
 * Created by caijx on 2018/9/9/009.
 */
public class Target implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " ....");
        }
    }
}
