package pers.caijx.thread.t8;

/**
 * Created by caijx on 2018/9/11/011.
 */
public class User {

    private String name;

    public volatile int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
