package pers.caijx.thread.t2;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 使用定时器
 * Created by caijx on 2018/9/9/009.
 */
public class Demo5 {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 实现定时任务
                System.out.println("timetask is run");
            }
        },0,1000);
    }
}
