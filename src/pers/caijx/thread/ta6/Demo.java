package pers.caijx.thread.ta6;

/**
 * 不推荐做法，线程先后执行的一种方式
 * Created by caijx on 2018/9/22/022.
 */
public class Demo {

    private volatile int signal;

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    public static void main(String[] args) {
        Demo d = new Demo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("修改状态的线程执行...");
                try {
                    Thread.sleep(5_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("状态值修改成功...");
                d.setSignal(1);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //等待signal为1开始执行，否则不能执行
                while (d.getSignal() != 1) {
                    try {
                        Thread.sleep(1000);
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
