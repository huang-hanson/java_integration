package com.hansom.logger.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName SimpleExample
 * @Description TODO
 * @date 2024/7/19 17:39
 **/
public class SimpleExample {
    public static void main(String[] args) {
        // 创建一些简单的产品
        List<String> products = Arrays.asList("Apple", "Banana", "Apple", "Orange", "Banana");

        // 统计每种产品的出现次数
        Map<String, Long> productCount = products.stream()
                .collect(Collectors.groupingBy(product -> product, Collectors.counting()));

        // 输出结果
        productCount.forEach((name, count) -> System.out.println(name + ": " + count));
    }
}