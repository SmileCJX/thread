package pers.caijx.thread.t7;

/**
 * 关于valatile的例子
 * Created by caijx on 2018/9/9/009.
 */
public class Demo2 {

    private volatile boolean run = false;

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public static void main(String[] args) {
        Demo2 d = new Demo2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("执行了第" + (i + 1) + "次");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                d.setRun(true);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!d.isRun()) {
                    //不执行
                }
                System.out.println("线程2执行了...");
            }
        }).start();
    }
}
