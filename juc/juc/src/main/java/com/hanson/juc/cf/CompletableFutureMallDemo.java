package com.hanson.juc.cf;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author hanson
 * @date 2024/6/4 10:49
 * <p>
 * 这里面需要注意一下Stream流方法的使用
 * 这种异步查询的方法大大节省了时间消耗，可以融入简历项目中，和面试官有所探讨
 * <p>
 * 案例说明:电商比价需求，模拟如下情况
 * <p>
 * 1需求:
 * 1.1 同一款产品，同时搜索出同款产品在各大电商平台的售价;
 * 1.2 同一款产品，同时搜索出本产品在同一个电商平台下，各个入驻卖家售价是多少
 * <p>
 * 2输出:出来结果希望是同款产品的在不同地方的价格清单列表，返回一个ist<string>
 * 《mysql》 in jd price is 88.05
 * 《mysql》in dangdang price is 86.11
 * 《mysql》in taobao price is 90.43
 * <p>
 * 3技术要求
 * 3.1 函数式编程
 * 3.2 链式编程
 * 3.3 Stream流式计算
 */
public class CompletableFutureMallDemo {

    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobao"),
            new NetMall("pdd"),
            new NetMall("tmall")
    );


    /**
     * step by step 一家一家搜索
     * List<NetMall> ----------> map -------------> List<String>
     *
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPrice(List<NetMall> list, String productName) {
        // 《mysql》 in jd price is 88.05
        return list.
                stream().
                map(netMall ->
                        String.format(productName + " in %s price is %.2f",
                                netMall.getNetMallName(),
                                netMall.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    /**
     * List<NetMall> ----------> List<CompletableFuture<String>> -------------> List<String>
     *
     * @param list
     * @param productName
     * @return
     */
    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
        return list.stream().map(netMall ->
                CompletableFuture.supplyAsync(() -> String.format(productName + " in %s price is %.2f",
                        netMall.getNetMallName(),
                        netMall.calcPrice(productName))))
                .collect(Collectors.toList())
                .stream()
                .map(s -> s.join())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> list1 = getPrice(list, "mysql");
        for (String element:list1){
            System.out.println(element);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("----costTime: " + (endTime - startTime) + " 毫秒");

        System.out.println("-----------------------------------------------------------------------");

        long startTime2 = System.currentTimeMillis();
        List<String> list2 = getPriceByCompletableFuture(list, "mysql");
        for (String element:list2){
            System.out.println(element);
        }

        long endTime2 = System.currentTimeMillis();

        System.out.println("----costTime: " + (endTime2 - startTime2) + " 毫秒");

    }

}

// 模拟电商
class NetMall {

    @Getter
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}
