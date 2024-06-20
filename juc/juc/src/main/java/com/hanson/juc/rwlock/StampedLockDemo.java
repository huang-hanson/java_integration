package com.hanson.juc.rwlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author hanson
 * @date 2024/6/20 8:53
 */
public class StampedLockDemo {
    static int number = 37;
    static StampedLock stampedLock = new StampedLock();

    public void write() {
        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + "\t" + "写线程准备修改");

        try {
            number = number + 13;
        } finally {
            stampedLock.unlockWrite(stamp);
        }

        System.out.println(Thread.currentThread().getName() + "\t" + "写线程修改完成");
    }

    // 悲观读，读没有完成时候写锁无法获取
    public void read() {
        long stamp = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + "\t" + "come in readlock code block, 4 seconds continue...");

        for (int i = 0; i < 4; i++) {
            // 暂停几秒钟
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + "\t" + "正在读取中....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            int result = number;
            System.out.println(Thread.currentThread().getName() + "\t" + "读线程读取完成,result: " + result);
            System.out.println("写线程没有修改成功，读锁时候写锁无法介入，传统的读写五斥");
        } finally {
            stampedLock.unlockRead(stamp);
        }

    }

    //乐观读，读的过程中也允许获取写锁介入
    public void tryoptimisticRead() {
        long stamp = stampedLock.tryOptimisticRead();
        int result = number;

        //故意间隔4秒钟，很乐观认为读取中没有其它线程修改过number值，具体考判断
        System.out.println("4秒前stampedLock.validate方法值(true无修改，false有修改)" + "\t" + stampedLock.validate(stamp));
        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "正在读取..." + i + "秒" +
                    "后stampedLock.validate方法值(true无修改，false有修改)" + "\t" + stampedLock.validate(stamp));
        }

        if (!stampedLock.validate(stamp)) {
            System.out.println("有人修改过------有写操作");
            stamp = stampedLock.readLock();
            try {
                System.out.println("从乐观读 升级为 悲观读");
                result = number;
                System.out.println("重新悲观读后result:" + result);
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t" + " finally value: " + result);

    }

    public static void main(String[] args) {
        StampedLockDemo stampedLockDemo = new StampedLockDemo();

        new Thread(() -> {
            stampedLockDemo.tryoptimisticRead();
        }, "readThread").start();

        // 暂停几秒钟
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t"+"----come in");
            stampedLockDemo.write();
        }, "writeThread").start();

//        4秒前stampedLock.validate方法值(true无修改，false有修改)	true
//        readThread	正在读取...0秒后stampedLock.validate方法值(true无修改，false有修改)	true
//        readThread	正在读取...1秒后stampedLock.validate方法值(true无修改，false有修改)	true
//        writeThread	----come in
//        writeThread	写线程准备修改
//        writeThread	写线程修改完成
//        readThread	正在读取...2秒后stampedLock.validate方法值(true无修改，false有修改)	false
//        readThread	正在读取...3秒后stampedLock.validate方法值(true无修改，false有修改)	false
//        有人修改过------有写操作
//        从乐观读 升级为 悲观读
//        重新悲观读后result:50

//        new Thread(() -> {
//            stampedLockDemo.read();
//        }, "readThread").start();
//
//        // 暂停几秒钟
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        new Thread(() -> {
//            stampedLockDemo.write();
//        }, "writeThread").start();
//
//        // 暂停几秒钟
//        try {
//            TimeUnit.SECONDS.sleep(4);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(Thread.currentThread().getName() + "number: " + number);

//        readThread	come in readlock code block, 4 seconds continue...
//        readThread	正在读取中....
//        readThread	正在读取中....
//        readThread	正在读取中....
//        readThread	正在读取中....
//        readThread	读线程读取完成,result: 37
//        写线程没有修改成功，读锁时候写锁无法介入，传统的读写五斥
//        writeThread	写线程准备修改
//        writeThread	写线程修改完成
//        mainnumber: 50
    }
}
