package pers.caijx.thread.ta6;

/**
 * Created by caijx on 2018/9/22/022.
 */
public class Demo2 {

    private volatile int signal;

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    public static void main(String[] args) {
        Demo2 d = new Demo2();
        new Thread(() -> {
            synchronized (d) {
                System.out.println("修改状态的线程执行...");
                try {
                    Thread.sleep(5_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("状态值修改成功...");
                d.setSignal(1);
                d.notify();
            }
        }).start();

        new Thread(() -> {
            synchronized (d) {
                //等待signal为1开始执行，否则不能执行
                while (d.getSignal() != 1) {
                    try {
                        d.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //当信号为1的时候，执行代码
                System.out.println("模拟代码的执行...");
            }

        }).start();
    }
}
