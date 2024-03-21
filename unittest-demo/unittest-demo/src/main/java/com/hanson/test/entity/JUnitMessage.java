package com.hanson.test.entity;

/**
 * @author hanson
 * @date 2024/3/21 23:31
 */
public class JUnitMessage {
    private String message;

    public JUnitMessage(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println(message);
    }

    public String printHiMessage() {
        return "Hi!" + message;
    }
}

