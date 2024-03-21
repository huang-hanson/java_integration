package com.hanson.test;

/**
 * @author hanson
 * @date 2024/3/21 23:34
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SuiteTest1.class,
        SuiteTest2.class,
})
public class JunitTest {
    // This class remains empty, it is used only as a holder for the above annotations
}

