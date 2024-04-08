package com.hanson.ioc.example;

/**
 * @author hanson
 * @date 2024/4/8 19:16
 */
public class NewCarExample {
    public static void main(String[] args) {
        Car car = new Car(20);
        car.run();
    }


    /**
     * 汽车对象
     */
    static class Car {
        private FrameWork frameWork;

        public Car(int size) {
            this.frameWork = new FrameWork(size);

            System.out.println("Car init...");
        }

        public void run() {
            System.out.println("Car run...");
        }
    }


    /**
     * 车身类
     */
    static class FrameWork {
        private Bottom bottom;

        public FrameWork(int size) {
            this.bottom = new Bottom(size);

            System.out.println("Frame init ...");
        }
    }

    /**
     * 底盘类
     */
    static class Bottom {
        private Tire tire;

        public Bottom(int size) {
            this.tire = new Tire(size);

            System.out.println("Bottom init ...");
        }
    }

    /**
     * 轮胎类
     */
    static class Tire {
        // 尺寸
        private int size;

//        public Tire() {
//            this.size = 17;
//            System.out.println("轮胎的尺寸：" + size);
//        }

        public Tire(int size) {
            this.size = size;
            System.out.println("轮胎的尺寸：" + size);
        }
    }
}
