package pers.caijx.thread.ta7;

import java.util.concurrent.TimeUnit;

/**
 * Created by caijx on 2018/9/23/023.
 */
public class TakeTarget implements Runnable{

    private Tmall tmall;

    public TakeTarget(Tmall tmall) {
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while (true) {
            tmall.take();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
