package com.hanson.juc.tl;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author hanson
 * @date 2024/6/18 16:40
 * <p>
 * 需求：需求变更：希望各自分灶吃饭，各凭销售本事提成，按照出单数各自统计-------比如房产中介销售都有自己的销售额指标，自己专属自己的，不和别人参和。
 */
class House {

    int saleCount = 0;

    public synchronized void saleHouse() {
        ++saleCount;
    }

//    ThreadLocal<Integer> saleVolume = new ThreadLocal<Integer>(){
//        @Override
//        protected Integer initialValue() {
//            return 0;
//        }
//    };

    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(() -> 0);

    public void saleVolumeByThreadLocal() {
        saleVolume.set(1 + saleVolume.get());
    }
}

public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        House house = new House();

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                int size = new Random().nextInt(5) + 1;
                try {
                    for (int j = 1; j <= size; j++) {
                        house.saleHouse();
                        house.saleVolumeByThreadLocal();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t" + "共卖出多少套：" + house.saleVolume.get());
                } finally {
                    house.saleVolume.remove();
                }
            }, String.valueOf(i)).start();
        }

        // 暂停毫秒
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t" + "共卖出多少套：" + house.saleCount);
    }
}
