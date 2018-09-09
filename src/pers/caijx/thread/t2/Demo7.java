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
        //普通流，顺序执行
        System.out.println("普通流：");
        values.stream().forEach(System.out::println);
        //并行流
        System.out.println("并行流：");
        values.parallelStream().forEach(System.out::println);
        //并行流，但按照顺序排序
        System.out.println("并行流，按照原始顺序排序");
        values.parallelStream().forEachOrdered(System.out::println);
        return values.parallelStream().mapToInt(a -> a * 2).sum();
    }
}
