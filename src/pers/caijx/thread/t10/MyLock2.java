package pers.caijx.thread.t10;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 可重入锁
 * Created by caijx on 2018/9/11/011.
 */
public class MyLock2 implements Lock{

    private boolean isLocked = false;

    private Thread lockBy = null;

    private int lockCount = 0;

    @Override
    public synchronized void lock() {
        Thread currentThread = Thread.currentThread(); // Thread-0
        // while模拟自旋，if不能保证安全性
        while (isLocked && currentThread != lockBy) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
        lockBy = currentThread;
        lockCount++; // 1 2
    }

    @Override
    public synchronized void unlock() {
        if (lockBy == Thread.currentThread()) {
            lockCount--; // 1 0
            if (lockCount == 0) {
                isLocked = false;
                notify();
            }
        }
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
