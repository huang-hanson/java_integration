package com.hansom.logger.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Order
 * @Description TODO
 * @date 2024/7/19 14:21
 **/
@Data
@NoArgsConstructor
public class Order {

    private List<Product> products;

    public Order(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getTotalPrice() {
        return products.stream().mapToInt(Product::getPrice).sum();
    }

}