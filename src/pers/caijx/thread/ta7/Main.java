package pers.caijx.thread.ta7;

/**
 * Created by caijx on 2018/9/23/023.
 */
public class Main {

    public static void main(String[] args) {
        Tmall tmall = new Tmall();
        PushTarget p =  new PushTarget(tmall);
        TakeTarget t = new TakeTarget(tmall);

        //供小于求
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();

        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
    }
}
