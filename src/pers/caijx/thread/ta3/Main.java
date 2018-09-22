package pers.caijx.thread.ta3;

/**
 * Created by caijx on 2018/9/22/022.
 */
public class Main {

    public static void main(String[] args) {
        Demo d = new Demo();
        d.put("key1","value1");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                d.put("key1","value1");
//            }
//        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                d.put("key2","value2");
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                d.put("key3","value3");
//            }
//        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.getKey("key1"));
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.getKey("key1"));
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.getKey("key1"));
            }
        }).start();
    }
}
