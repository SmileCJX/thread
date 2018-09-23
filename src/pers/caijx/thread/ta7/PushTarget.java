package pers.caijx.thread.ta7;

import java.util.concurrent.TimeUnit;

/**
 * Created by caijx on 2018/9/23/023.
 */
public class PushTarget implements Runnable{

    private Tmall tmall;

    public PushTarget(Tmall tmall) {
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while (true) {
            tmall.push();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
