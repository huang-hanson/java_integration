package com.hansom.logger.test;

import com.hansom.logger.entity.Order;
import com.hansom.logger.entity.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName SreamExample
 * @Description TODO
 * @date 2024/7/19 14:22
 **/
public class StreamExample {
    public static void main(String[] args) {
        // 创建一些商品
        Product product1 = new Product("Apple", 1050);
        Product product2 = new Product("Banana", 1000);
        Product product3 = new Product("Orange", 2000);
        Product product4 = new Product("Mango", 500);
        Product product5 = new Product("Pineapple", 300);

        // 创建一些订单
        Order order1 = new Order(Arrays.asList(product1, product2, product3));
        Order order2 = new Order(Arrays.asList(product4, product5));
        Order order3 = new Order(Arrays.asList(product1, product3, product5));
        Order order4 = new Order(Arrays.asList(product2, product4, product5));

        List<Order> orders = Arrays.asList(order1, order2, order3, order4);

        // 流式处理
        Map<String, Long> productCount = orders.stream()
                // 过滤出订单总金额大于1000的订单
                .filter(order -> order.getTotalPrice() > 1000)
                // 将每个订单中的商品映射为商品名称列表
                .map(Order::getProducts)
                // 将所有订单中的商品列表展平为一个商品名称列表
                .flatMap(List::stream)
                // 统计每种商品出现的次数
                .collect(Collectors.groupingBy(Product::getName, Collectors.counting()));

        // 输出每种商品及其出现次数
        productCount.forEach((name, count) -> System.out.println(name + ": " + count));
    }
}