//package com.hanson.demo.consumer;
//
//import lombok.extern.slf4j.Slf4j;
//import org.redisson.api.RBlockingQueue;
//
///**
// * @author hanson.huang
// * @version V1.0
// * @ClassName TaskConsumer
// * @Description TODO
// * @date 2024/7/11 18:02
// **/
//@Slf4j
//public class TaskConsumer implements Runnable{
//
//    private final RBlockingQueue<String> blockingDeque;
//
//    public TaskConsumer(RBlockingQueue<String> blockingDeque) {
//        this.blockingDeque = blockingDeque;
//    }
//
//    @Override
//    public void run() {
//        try {
//            // 阻塞等待并执行
//            while (true){
//                String task = blockingDeque.take();
//                log.info("消费任务:{}",task);
//            }
//        }catch (InterruptedException e){
//            Thread.currentThread().interrupt();
//            e.printStackTrace();
//        }
//    }
//}