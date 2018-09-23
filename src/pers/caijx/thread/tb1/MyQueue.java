package pers.caijx.thread.tb1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by caijx on 2018/9/23/023.
 */
public class MyQueue<E> {

    private Object[] obj;

    private int addIndex;

    private int removeIndex;

    private int queueSize;

    private Lock lock = new ReentrantLock();

    Condition addCondition = lock.newCondition();
    Condition removeCondition = lock.newCondition();

    public MyQueue(int count) {
        obj = new Object[count];
    }

    public void add(E e) {
        lock.lock();
        while (queueSize == obj.length) {
            try {
                addCondition.await();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        obj[addIndex] = e;
        System.out.println("添加的元素为：" + obj[addIndex]);
        if (++addIndex == obj.length) {
            addIndex = 0;
        }
        queueSize++;
        removeCondition.signal();
        lock.unlock();
    }

    public void remove() {
        lock.lock();
        while (queueSize == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " 队列为空，不进行移除。");
                removeCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("移除的元素为：" + obj[removeIndex]);
        obj[removeIndex] = null;
        if (++removeIndex == obj.length) {
            removeIndex = 0;
        }
        queueSize--;
        addCondition.signal();
        lock.unlock();
    }

}
