package com.hansom.logger.test;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Calculator
 * @Description TODO
 * @date 2024/7/18 17:38
 **/
@Slf4j
public class Calculator {

    public int add(int a, int b) {
        log.trace("Entering add method with parameters: a = {}, b = {}", a, b);
        int result = a + b;
        log.trace("Result of add method: {}", result);
        return result;
    }

    public int subtract(int a, int b) {
        log.trace("Entering subtract method with parameters: a = {}, b = {}", a, b);
        int result = a - b;
        log.trace("Result of subtract method: {}", result);
        return result;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.add(10, 5);
        calculator.subtract(10, 5);
    }
}