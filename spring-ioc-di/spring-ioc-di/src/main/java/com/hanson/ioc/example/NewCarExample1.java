package com.hanson.ioc.example;

/**
 * @author hanson
 * @date 2024/4/8 19:16
 */
public class NewCarExample1 {
    public static void main(String[] args) {
        Tire tire = new Tire(20);
        Bottom bottom = new Bottom(tire);
        FrameWork frameWork = new FrameWork(bottom);
        Car car = new Car(frameWork);
        car.run();
    }


    /**
     * 汽车对象
     */
    static class Car {
        private FrameWork frameWork;

        public Car(FrameWork frameWork) {
            this.frameWork = frameWork;

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

        public FrameWork(Bottom bottom) {
            this.bottom = bottom;

            System.out.println("Frame init ...");
        }
    }

    /**
     * 底盘类
     */
    static class Bottom {
        private Tire tire;

        public Bottom(Tire tire) {
            this.tire = tire;

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
