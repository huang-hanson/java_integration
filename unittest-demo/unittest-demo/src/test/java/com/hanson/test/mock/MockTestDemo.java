package com.hanson.test.mock;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.timeout;

/**
 * @author hanson
 * @date 2024/3/22 0:20
 */
public class MockTestDemo {

    @Test
    public void test0() {
        //1、创建mock对象（模拟依赖的对象）
        final List mock = Mockito.mock(List.class);

        //2、使用mock对象（mock对象会对接口或类的方法给出默认实现）
        System.out.println("mock.add result => " + mock.add("first"));  //false
        System.out.println("mock.size result => " + mock.size());       //0

        //3、打桩操作（状态测试：设置该对象指定方法被调用时的返回值）
        Mockito.when(mock.get(0)).thenReturn("second");
        Mockito.doReturn(66).when(mock).size();

        //3、使用mock对象的stub（测试打桩结果）
        System.out.println("mock.get result => " + mock.get(0));    //second
        System.out.println("mock.size result => " + mock.size());   //66

        //4、验证交互 verification（行为测试：验证方法调用情况）
        Mockito.verify(mock).get(Mockito.anyInt());
        Mockito.verify(mock, Mockito.times(2)).size();

        //5、验证返回的结果（这是JUnit的功能）
        Assert.assertEquals("second", mock.get(0));
        Assert.assertEquals(66, mock.size());
    }

    @Test
    public void test1() {
        final List mockList = Mockito.mock(List.class);
        mockList.add("mock1");
        mockList.get(0);
        mockList.size();
        mockList.clear();
        // 验证方法被使用（默认1次）
        Mockito.verify(mockList).add("mock1");
        // 验证方法被使用1次
        Mockito.verify(mockList, Mockito.times(1)).get(0);
        // 验证方法至少被使用1次
        Mockito.verify(mockList, Mockito.atLeast(1)).size();
        // 验证方法没有被使用
        Mockito.verify(mockList, Mockito.never()).contains("mock2");
        // 验证方法至多被使用5次
        Mockito.verify(mockList, Mockito.atMost(5)).clear();
        // 指定方法调用超时时间
        Mockito.verify(mockList, timeout(100)).get(0);
        // 指定时间内需要完成的次数
        Mockito.verify(mockList, timeout(200).atLeastOnce()).size();
    }


}
