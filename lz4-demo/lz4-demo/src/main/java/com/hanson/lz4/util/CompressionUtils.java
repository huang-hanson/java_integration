//package com.hanson.lz4.util;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.hanson.lz4.entity.BusinessObject;
//import net.jpountz.lz4.LZ4Factory;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Date;
//import java.util.Random;
//
//import static com.hanson.lz4.util.BusinessObjectGenerator.convertToJsonString;
//import static com.hanson.lz4.util.BusinessObjectGenerator.generateRandomString;
//
///**
// * @author hanson.huang
// * @version V1.0
// * @ClassName CompressionUtils
// * @Description TODO
// * @date 2024/7/23 9:58
// **/
//public class CompressionUtils {
//
//    private static final LZ4Factory factory = LZ4Factory.fastestInstance();
//    private static final Random random = new Random();
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    public static void main(String[] args) throws Exception {
//        BusinessObject obj = generateBusinessObject();
//        String jsonString = convertToJsonString(obj);
//        int dataSize = jsonString.getBytes(StandardCharsets.UTF_8).length;
//
//        System.out.println("Generated JSON String size: " + dataSize + " bytes");
//        System.out.println("JSON String: " + jsonString);
//
//        // 测量压缩时间
//        long startTime = System.nanoTime();
//        byte[] compressedData = compress(jsonString);
//        long compressionTime = System.nanoTime() - startTime;
//
//        // 测量解压缩时间
//        startTime = System.nanoTime();
//        String decompressedData = decompress(compressedData, jsonString.length());
//        long decompressionTime = System.nanoTime() - startTime;
//
//        System.out.println("Original size: " + jsonString.getBytes(StandardCharsets.UTF_8).length + " bytes");
//        System.out.println("Compressed size: " + compressedData.length + " bytes");
//        System.out.println("Decompressed data is equal to original: " + jsonString.equals(decompressedData));
//        System.out.println("Compression time: " + compressionTime / 1_000_000.0 + " ms");
//        System.out.println("Decompression time: " + decompressionTime / 1_000_000.0 + " ms");
//    }
//
//    private static BusinessObject generateBusinessObject() {
//        BusinessObject obj = new BusinessObject();
//        obj.setId(generateRandomString(20));
//        obj.setName(generateRandomString(50));
//        obj.setAge(random.nextInt(100));
//        obj.setAddress(generateRandomString(100));
//        obj.setDescription(generateRandomString(800));
//        obj.setCreatedDate(new Date());
//
//        return obj;
//    }
//}