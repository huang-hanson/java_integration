package com.hanson.test;

/**
 * @author hanson
 * @date 2024/3/21 23:30
 */
import static org.junit.Assert.assertEquals;

import com.hanson.test.entity.JUnitMessage;
import org.junit.Test;

public class SuiteTest1 {

    public String message = "Saurabh";

    JUnitMessage junitMessage = new JUnitMessage(message);

    @Test
    public void testJUnitMessage() {
        System.out.println("Junit Message is printing ");
        junitMessage.printMessage();
    }

    @Test
    public void testJUnitHiMessage() {
        message = "Hi!" + message;
        System.out.println("Junit Hi Message is printing ");
        assertEquals(message, junitMessage.printHiMessage());
        System.out.println("Suite Test 2 is successful " + message);
    }
}
