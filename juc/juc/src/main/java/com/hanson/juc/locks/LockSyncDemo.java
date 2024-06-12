package com.hanson.juc.locks;

class Book{

}
/**
 * @author hanson
 * @date 2024/6/5 14:44
 */
public class LockSyncDemo
{

    Object object = new Object();
    Book book = new Book();

    public void m1()
    {
        synchronized (object)
        {
            System.out.println("-----hello synchronized code block");
        }
    }


    public synchronized void m2()
    {
        System.out.println("--------------hello synchronized m2");
    }

    public static synchronized void m3()
    {
        System.out.println("--------------hello synchronized m3");
    }

    public static void main(String[] args)
    {

    }
}
