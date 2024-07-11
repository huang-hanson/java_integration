//package com.hanson.demo.producer;
//
//import lombok.extern.slf4j.Slf4j;
//import org.redisson.api.RDelayedQueue;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author hanson.huang
// * @version V1.0
// * @ClassName TaskProducer
// * @Description TODO
// * @date 2024/7/11 17:56
// **/
//@Slf4j
//public class TaskProducer implements Runnable {
//
//    private final RDelayedQueue<String> delayedQueue;
//
//    public TaskProducer(RDelayedQueue<String> delayedQueue) {
//        this.delayedQueue = delayedQueue;
//    }
//
//    @Override
//    public void run() {
//        try {
//            for (int i = 0; i < 5; i++) {
//                String task = "task-" + i;
//                delayedQueue.offer(task, 5 * i, TimeUnit.SECONDS);
//                log.info("任务{}已放入队列,将在{}秒后执行", task, i * 5);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}