package pers.caijx.thread.t1;

/**
 * Created by caijx on 2018/9/8/008.
 */
public class NewThread implements Runnable{

    @Override
    public synchronized void run() {
        while (true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("自定义线程执行了...");
        }
    }

    public static void main(String[] args) {

        NewThread n = new NewThread();

        // 初始化状态
        Thread thread = new Thread(n);  // 创建线程，并指定线程任务
        thread.start();  // 启动线程
        while (true) {
            synchronized (n) {
                System.out.println("主线程执行了...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                n.notifyAll();
            }
        }
    }

}
