package com.hanson.test;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author hanson
 * @date 2024/3/21 19:03
 */
public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String json = "{\"id\":4,\"name\":\"阿狸\",\"classname\":\"初三一班\",\"age\":16,\"sex\":\"女\"}";
        HttpEntity<String> httpEntity = new HttpEntity<>(json, httpHeaders);
        String url = "http://localhost:8080/student/add";
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, httpEntity, Map.class);
        System.out.println(responseEntity.getBody());
    }
}

