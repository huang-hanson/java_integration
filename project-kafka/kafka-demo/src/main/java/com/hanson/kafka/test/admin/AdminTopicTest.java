package com.hanson.kafka.test.admin;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hanson
 * @date 2024/6/27 23:14
 */
public class AdminTopicTest {

    public static void main(String[] args) {

        Map<String, Object> confMap = new HashMap<>();
        confMap.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // TODO 管理员对象
        final Admin admin = Admin.create(confMap);

        // TODO 构建主题需要传递三个参数
        //  第一个参数是主题的名称:字母、数字、点、下划线、中横线
        //  第二个参数是主题分区的数量：int
        //  第三个参数是主题分区副本的（因子）数量：short
        String topicName = "test1";
        int partitionCount = 1;
        short replicationFactor = 1;
        NewTopic topic1 = new NewTopic(topicName, partitionCount, replicationFactor);

        String topicName1 = "test2";
        int partitionCount1 = 2;
        short replicationFactor1 = 2;
        NewTopic topic2 = new NewTopic(topicName1, partitionCount1, replicationFactor1);


        String topicName2 = "test4";
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, Arrays.asList(3, 1));
        map.put(1, Arrays.asList(2, 3));
        map.put(2, Arrays.asList(1, 2));

        NewTopic topic3 = new NewTopic(topicName2, map);


        // TODO 创建主题
        final CreateTopicsResult result = admin.createTopics(
                Arrays.asList(topic3)
        );

        // in-sync-replicas:同步副本列表（ISR）


        // TODO 关闭资源
        admin.close();
    }
}
