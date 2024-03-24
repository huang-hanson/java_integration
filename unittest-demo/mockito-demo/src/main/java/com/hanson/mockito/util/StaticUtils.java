package com.hanson.mockito.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author hanson
 * @date 2024/3/22 15:30
 */
public class StaticUtils {

    private StaticUtils() {}

    //返回指定区间的 Integer List
    public static List<Integer> range(int start, int end) {
        return IntStream.range(start, end).boxed().collect(Collectors.toList());
    }

    //返回 Hanson 字符串
    public static String name() {
        return "Hanson";
    }
}
