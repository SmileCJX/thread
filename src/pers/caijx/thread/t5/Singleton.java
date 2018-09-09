package pers.caijx.thread.t5;

/**
 * Created by caijx on 2018/9/9/009.
 */
public class Singleton {

    //私有化构造方法
    public Singleton() {

    }

    //饿汉式
    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

    //多线程的环境下
    //必须有共享资源
    //对资源进行非原子性操作
}
