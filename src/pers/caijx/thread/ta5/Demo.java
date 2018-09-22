package pers.caijx.thread.ta5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by caijx on 2018/9/22/022.
 */
public class Demo {

    private Map<String,Object> map = new HashMap<>();

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    private Lock r = rwl.readLock();

    private Lock w = rwl.writeLock();

    private volatile boolean isUpdate;

    /**
     * 锁降级
     * 锁降级是指写锁降级为读锁
     在写锁没有释放的时候，获取到读锁，再释放写锁
     */
    public void readWrite() {
        r.lock(); // 为了保证isUpdate能够拿到最新的值
        if (isUpdate) {
            r.unlock();
            w.lock();
            map.put("xxx","xxx");
            r.lock();
            w.unlock();
        }
        Object obj = map.get("xxx");
        System.out.println(obj);
        r.unlock();
    }

    public Object getKey(String key) {
        r.lock();
        System.out.println(Thread.currentThread().getName() + " 读操作在执行...");
        try {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.get(key);
        } finally {
            r.unlock();
            System.out.println(Thread.currentThread().getName() + " 读操作执行完毕...");
        }
    }

    public void put(String key,Object value) {
        w.lock();
        System.out.println(Thread.currentThread().getName() + " 写操作在执行...");
        try {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
        } finally {
            w.unlock();
            System.out.println(Thread.currentThread().getName() + " 写操作执行完毕...");
        }
    }
}
