package com.hanson.mq.fanout;

import com.hanson.mq.util.ConnectionUtil;
import com.rabbitmq.client.*;

/**
 * @author hanson
 * @date 2024/6/21 13:15
 */
public class Producer {
    public static void main(String[] args) throws Exception {

        // 1、获取连接
        Connection connection = ConnectionUtil.getConnection();

        // 2、创建频道
        Channel channel = connection.createChannel();

        // 参数1. exchange：交换机名称
        // 参数2. type：交换机类型
        //     DIRECT("direct")：定向
        //     FANOUT("fanout")：扇形（广播），发送消息到每一个与之绑定队列。
        //     TOPIC("topic")：通配符的方式
        //     HEADERS("headers")：参数匹配
        // 参数3. durable：是否持久化
        // 参数4. autoDelete：自动删除
        // 参数5. internal：内部使用。一般false
        // 参数6. arguments：其它参数
        String exchangeName = "test_fanout";

        // 3、创建交换机
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT,true,false,false,null);

        // 4、创建队列
        String queue1Name = "test_fanout_queue1";
        String queue2Name = "test_fanout_queue2";

        channel.queueDeclare(queue1Name,true,false,false,null);
        channel.queueDeclare(queue2Name,true,false,false,null);

        // 5、绑定队列和交换机
        // 参数1. queue：队列名称
        // 参数2. exchange：交换机名称
        // 参数3. routingKey：路由键，绑定规则
        //     如果交换机的类型为fanout，routingKey设置为""
        channel.queueBind(queue1Name,exchangeName,"");
        channel.queueBind(queue2Name,exchangeName,"");

        String body = "日志信息：张三调用了findAll方法...日志级别：info...";

        // 6、发送消息
        channel.basicPublish(exchangeName,"",null,body.getBytes());

        // 7、释放资源
        channel.close();
        connection.close();

    }
}
