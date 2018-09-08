package pers.caijx.thread.t2;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by caijx on 2018/9/9/009.
 */
public class Demo6 {

    public static void main(String[] args) {
        // 带有10个线程的线程池
//        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 够用的情况下回收，不够用的情况下创建
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        threadPool.shutdown();
    }
}
