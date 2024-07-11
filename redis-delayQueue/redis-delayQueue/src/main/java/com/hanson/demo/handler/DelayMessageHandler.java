//package com.hanson.demo.handler;
//
//import com.hanson.demo.entity.DelayMessage;
//import com.hanson.demo.utils.DelayQueue;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.List;
//
///**
// * @author hanson.huang
// * @version V1.0
// * @ClassName DelayMessageHandler
// * @Description TODO
// * @date 2024/7/11 17:07
// **/
//@Component
//@Slf4j
//public class DelayMessageHandler {
//
//    public static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    @Autowired
//    private DelayQueue delayQueue;
//
//    /**
//     * 处理已到期的消息(轮询)
//     */
////    @Scheduled(fixedDelay = 1000)
////    public void handleExpiredMessages() {
////        String currTime = getCurrTime();
////        // 1. 扫描任务，并将需要执行的任务加入到任务队列当中
////        List<DelayMessage> messages = delayQueue.getExpiredMessages();
////        List<DelayMessage> messages_2 = delayQueue.getExpiredMessages();
////        log.info(currTime + " 待处理消息数量： " + messages.size());
////        // 2. 开始处理消息
////        if (!messages.isEmpty()) {
////            for (DelayMessage message : messages) {
////                log.info(message.getId() + "------------> 消息开始处理");
////                try {
////                    // 2.1.1: 模拟睡眠3秒，任务的处理时间（实际可能会更长）
////                    Thread.sleep(3000);
////                }catch (Exception e){
////                    e.printStackTrace();
////                }
////                log.info(message.getId() + "------------> 消息处理结束");
////                // 2.2 处理完消息，删除消息
////                delayQueue.remove(message);
////            }
////        }
////    }
//
//    /**
//     * 处理已到期的消息(轮询)
//     */
////    @Scheduled(fixedDelay = 1000)
////    public void handleExpiredMessages() {
////        String currentTime = getCurrTime();
////        //  1 : 扫描任务，并将需要执行的任务加入到任务队列中
////        List<DelayMessage> messages = delayQueue.getExpiredMessages();
////        System.out.println(currentTime + " 待处理消息数量：" + messages.size());
////        //  2 : 开始处理消息
////        if (!messages.isEmpty()) {
////            for (DelayMessage message : messages) {
////        //  2.1 : 开启线程异步处理消息:不让处理消息的时间阻塞当前线程
////                new Thread(() -> {
////                    System.out.println(currentTime + " :" + message.getId() + " --> 消息开始处理");
////                    try {
////        //  2.1.1 : 模拟睡眠3秒,任务的处理时间(实际可能会更长)
////                        Thread.sleep(3000);
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////                    System.out.println(currentTime + " :" + message.getId() + " --> 消息处理结束");
////        //  2.2 : 处理完消息,删除消息
////                    delayQueue.remove(message);
////                }).start();
////            }
////        }
////    }
//
//    /**
//     * 处理已到期的消息(轮询)
//     */
//    @Scheduled(fixedDelay = 1000)
//    public void handleExpiredMessages() {
//        String currentTime = getCurrTime();
//        //  1 : 扫描任务，并将需要执行的任务加入到任务队列中
//        List<DelayMessage> messages = delayQueue.getExpiredMessages();
//        System.out.println(currentTime + " 待处理消息数量：" + messages.size());
//        //  2 : 开始处理消息
//        if (!messages.isEmpty()) {
//            for (DelayMessage message : messages) {
//        //  2.1 : 处理消息:先删除消息,获取当前消息是否已经被其他人消费
//                Long remove = delayQueue.remove(message);
//                if (remove > 0) {
//        //  2.2 : 开启线程异步处理消息:不让处理消息的时间阻塞当前线程
//                    new Thread(() -> {
//                        System.out.println(currentTime + " :" + message.getId() + " --> 消息开始处理");
//                        try {
//        //  2.1.1 : 模拟睡眠3秒,任务的处理时间(实际可能会更长)
//                            Thread.sleep(3000);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println(currentTime + " :" + message.getId() + " --> 消息处理结束");
//                    }).start();
//                }
//            }
//        }
//    }
//
//
//
//    /**
//     * 获取到的当前时分秒
//     *
//     * @return
//     */
//    public static String getCurrTime() {
//        return dateTimeFormat.format(System.currentTimeMillis());
//    }
//
//}