package com.hanson.rpc;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author hanson
 * @date 2024/3/13 16:05
 */
public class CalculatorClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8081);
            CalculatorService calculatorService = (CalculatorService) registry.lookup("CalculatorService");

            int result = calculatorService.add(10, 5);
            System.out.println("Result of addition: " + result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
