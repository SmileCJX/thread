package pers.caijx.thread.t2;

import java.util.Arrays;
import java.util.List;

/**
 * 使用JDK8提供的lambda进行并行计算
 * Created by caijx on 2018/9/9/009.
 */
public class Demo7 {

    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(10,20,30,40);
        int res = new Demo7().add(values);
        System.out.println("计算的结果为：" + res);
    }

    public int add(List<Integer> values) {
        return values.parallelStream().mapToInt(a -> a).sum();
    }
}
