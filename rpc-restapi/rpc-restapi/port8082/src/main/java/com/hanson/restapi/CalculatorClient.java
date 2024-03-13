package com.hanson.restapi;

import org.springframework.web.client.RestTemplate;

/**
 * @author hanson
 * @date 2024/3/13 16:16
 */
public class CalculatorClient {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8081/add?a=10&b=5";
        int result = restTemplate.getForObject(url, Integer.class);

        System.out.println("Result of addition: " + result);
    }
}
