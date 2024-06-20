package com.hanson.juc.objectthread;

/**
 * @author hanson
 * @date 2024/6/18 20:30
 */
public class ObjectHeadDemo {
    public static void main(String[] args) {

        Object o = new Object();// ? new一个对象，占内存多少？16个字节（mark word占8个字节，类型指针占8个字节）

        System.out.println(o.hashCode());// 这个hashcode记录在什么地方

        synchronized (o){// 锁住对象，占用内存

        }

        System.gc();//手动收集垃圾。。。。，15次可以从新生代到养老区

        Customer c1 = new Customer();
    }
}

//class Customer{
//    int id;
//    String customerName;
//}
