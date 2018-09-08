package pers.caijx.thread.t2;

/**
 * 匿名内部类重写run方法以及实现Runnable接口，两种方法都采用去实现创建线程
 * Created by caijx on 2018/9/8/008.
 */
public class Demo3 {

    public static void main(String[] args) {

        //重新run方法
//        new Thread(){
//            @Override
//            public void run() {
//                System.out.println("thread start...");
//            }
//        }.start();

        //实现Runnable接口
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                System.out.println("thread start...");
//            }
//        }).start();

        //两种方式都采用的情况，由于子类重写父类，输出sub
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("runnable");
            }
        }){
            @Override
            public void run() {
                System.out.println("sub");
            }
        }.start();
    }
}
