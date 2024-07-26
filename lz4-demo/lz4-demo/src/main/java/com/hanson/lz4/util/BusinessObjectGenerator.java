package com.hanson.lz4.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;

import com.hanson.lz4.entity.BusinessObject;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName BusinessObjectGenerator
 * @Description TODO
 * @date 2024/7/23 9:44
 **/
public class BusinessObjectGenerator {
    private static final Random random = new Random();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        BusinessObject obj = generateBusinessObject();
        String jsonString = convertToJsonString(obj);
        int dataSize = jsonString.getBytes(StandardCharsets.UTF_8).length;

        System.out.println("Generated JSON String size: " + dataSize + " bytes");
        System.out.println("JSON String: " + jsonString);
    }

    public static BusinessObject generateBusinessObject() {
        BusinessObject obj = new BusinessObject();
        obj.setId(generateRandomString(20));
        obj.setName(generateRandomString(50));
        obj.setAge(random.nextInt(100));
        obj.setAddress(generateRandomString(100));
        obj.setDescription(generateRandomString(800));
        obj.setCreatedDate(new Date());

        return obj;
    }

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return sb.toString();
    }

    public static String convertToJsonString(BusinessObject obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }
}