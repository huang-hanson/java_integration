package com.hanson.juc.atomics;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;

/**
 * @author hanson
 * @date 2024/6/13 18:58
 */
public class LongAdderAPIDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();

        longAdder.increment();
        longAdder.increment();
        longAdder.increment();

        System.out.println(longAdder.sum());

        // 方式一：用lambda表达式
//        LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 0);
        // 方式二：用匿名内部类
        LongAccumulator longAccumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left + right;
            }
        },0);

        longAccumulator.accumulate(1);//1
        longAccumulator.accumulate(3);//4

        System.out.println(longAccumulator.get());

    }
}
