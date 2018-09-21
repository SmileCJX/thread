package pers.caijx.thread.ta1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 不可重入锁，不推荐
 * Created by caijx on 2018/9/11/011.
 */
public class MyLock implements Lock{

    private boolean isLocked = false;

    @Override
    public synchronized void lock() {
        // while模拟自旋，if不能保证安全性
        while (isLocked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
    }

    @Override
    public synchronized void unlock() {
        isLocked = false;
        notify();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
