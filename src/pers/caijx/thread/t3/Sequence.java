package pers.caijx.thread.t3;

/**
 * 从java字节码的角度看线程安全性问题
 * 用于分析字节码文件：javap - verbose xxx.class
 * Created by caijx on 2018/9/9/009.
 */
public class Sequence {

    private static int value;

    /**
     * synchronized 放在普通方法上，内置锁就是当前类的实例
     * @return
     */
    public synchronized int getNext() {
        return value++;
    }

    /**
     * 修饰代码块
     * @return
     */
    public int method() {
        synchronized (this) {
            if (value > 0) {
                return value;
            } else {
                return -1;
            }
        }
    }

    /**
     * 修饰静态方法，内置锁是当前的Class字节码对象
     * @return
     */
    public static synchronized int getPrevious() {
        return value--;
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence();
//        while (true) {
//            System.out.println(sequence.getNext());
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + sequence.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + sequence.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " " + sequence.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
