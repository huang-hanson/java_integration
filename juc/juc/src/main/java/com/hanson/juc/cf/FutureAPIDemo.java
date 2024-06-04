package com.hanson.juc.cf;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author hanson
 * @date 2024/5/30 18:30
 */
public class FutureAPIDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + "\t -------come in");

            //暂停几秒钟
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "task over";
        });

        Thread t1 = new Thread(futureTask, "t1");
        t1.start();


//        System.out.println(futureTask.get());// 不见不散，非要等到结果才会离开，不管是否计算完成，容易造成程序堵塞
        System.out.println(Thread.currentThread().getName() + "\t ------忙其他任务了");
//        System.out.println(futureTask.get());

        /*
         * Exception in thread "main" java.util.concurrent.TimeoutException
         * */
//        System.out.println(futureTask.get(3, TimeUnit.SECONDS)); //只愿意等待三秒，计算未完成直接抛出异常

        while (true) {
            if (futureTask.isDone()){
                System.out.println(futureTask.get());
                break;
            }else {
                // 暂停毫秒
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("正在处理中，不要再催了，越催越慢，再催熄火");
            }
        }
    }

    /*
    * 1 get容易导致阻塞，一般建议放在程序后面，一旦调用不见不散，非要等到结果才会离开，不管你是否让算完成，容易程序堵塞。
    * 2 假如我不愿意等待很长时间，我希望过时不候，可以自动离开
    * */
}
