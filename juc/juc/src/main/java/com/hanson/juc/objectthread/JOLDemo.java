package com.hanson.juc.objectthread;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author hanson
 * @date 2024/6/18 22:13
 */
public class JOLDemo {
    public static void main(String[] args) {

        // Thread.currentThread
//        System.out.println(VM.current().details());

//        System.out.println(VM.current().objectAlignment());
//
//        Object o = new Object();
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        Customer c1 = new Customer();
        System.out.println(ClassLayout.parseInstance(c1).toPrintable());
    }
}
class Customer //只有一个对象头的实例对象，16字节(忽略压缩指针的影响)+4字节+1字节=21字节----》对其填充，24字节
{
    //1 第一种情况，只有对象头，没有其它任何实例数

    // 2 第二种情况，int + boolean
    private int id;
    private boolean flag = false;

    private double d;
}
//J0L = Java object Layout

