package com.hansom.rabbitMQ.config;

import com.rabbitmq.client.Channel;
import lombok.Getter;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName RabbitMQConfig
 * @Description TODO
 * @date 2024/7/22 12:12
 **/
@Configuration
@EnableRabbit
public class RabbitMQConfig extends BaseRabbitConfig {

    public static final String TEST_QUEUE = "test-queue";
    public static final String TEST_ROUTING_KEY = "test.routing.key";

    @Configuration
    @Getter
    public static class WyRabbitConfigProperties {
        @Value("${wy.rabbitmq.host}")
        private String host;

        @Value("${wy.rabbitmq.username}")
        private String username;

        @Value("${wy.rabbitmq.password}")
        private String password;

        @Value("${wy.rabbitmq.port}")
        private int port;

        @Value("${wy.rabbitmq.virtualHost}")
        private String virtualHost;

        @Value("${wy.rabbitmq.publisherConfirms:false}")
        private Boolean publisherConfirms;

        @Value("${wy.rabbitmq.consumerProcess:3}")
        private int consumerProcess;

        @Value("${wy.rabbitmq.consumerProcessMax:10}")
        private int consumerProcessMax;

        @Value("${wy.rabbitmq.prefetchCount:10}")
        private int prefetchCount;

        @Value("${wy.rabbitmq.listenerAcknowledgeMode:}")
        private String listenerAcknowledgeMode;

        @Value("${wy.rabbitmq.channelMax:10000}")
        private int channelMax;
    }

    /**
     * 创建一个自定义的 RabbitMQ 连接工厂，主要用于提供与 RabbitMQ 服务器的连接。
     * 这个连接工厂配置是基于WyRabbitConfigProperties的属性进行的，确保了配置的灵活性和可扩展性。
     * 使用@Bean注解将这个方法的返回值注册为一个Spring Bean，命名为"wyConnectionFactory"。
     * 使用@Primary注解指定这个连接工厂作为默认选项，当存在多个候选连接工厂时。
     *
     * @param configProperties 一个自定义的配置类，包含了连接到RabbitMQ服务器所需的所有属性。
     * @return 返回一个配置好的CachingConnectionFactory实例，它可以被其他RabbitMQ相关的Bean使用。
     */
    @Bean("wyConnectionFactory")
    @Primary
    public ConnectionFactory wyConnectionFactory(@Autowired WyRabbitConfigProperties configProperties) {
        // 创建CachingConnectionFactory实例，它是一个连接工厂的缓存，可以提高性能。
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();

        // 根据配置属性设置连接工厂的各项参数，以建立到RabbitMQ服务器的连接.
        connectionFactory.setHost(configProperties.getHost());
        connectionFactory.setUsername(configProperties.getUsername());
        connectionFactory.setPassword(configProperties.getPassword());
        connectionFactory.setPort(configProperties.getPort());
        connectionFactory.setVirtualHost(configProperties.getVirtualHost());
        connectionFactory.setPublisherConfirms(configProperties.getPublisherConfirms());

        // 进一步配置底层的RabbitConnectionFactory，设置最大通道数。
        // 这个设置可以影响到连接的性能和资源使用。
        connectionFactory.getRabbitConnectionFactory().setRequestedChannelMax(configProperties.getChannelMax());
        // 返回配置好的连接工厂。
        return connectionFactory;
    }

    /**
     * 配置RabbitMQ监听器容器工厂。
     * 该方法用于创建并配置一个SimpleRabbitListenerContainerFactory，它被用于Spring Boot应用程序中处理RabbitMQ消息监听。
     *
     * @param configurer        用于配置Rabbit监听器容器工厂的对象。
     * @param connectionFactory 提供RabbitMQ连接的ConnectionFactory。
     * @param configProperties  WyRabbitConfigProperties的实例，包含RabbitMQ的配置属性。
     * @return 配置后的SimpleRabbitListenerContainerFactory实例。
     * @Bean 注解表明该方法返回的对象应该被注册为Spring Bean，名称为"wyRabbitListener"。
     */
    @Bean(name = "wyRabbitListener")
    public SimpleRabbitListenerContainerFactory wyRabbitListener(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("wyConnectionFactory") ConnectionFactory connectionFactory,
            @Autowired WyRabbitConfigProperties configProperties
    ) {
        // 创建一个新的SimpleRabbitListenerContainerFactory实例
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        // 设置ConnectionFactory
        factory.setConnectionFactory(connectionFactory);
        // 根据配置属性设置预取消息数量
        factory.setPrefetchCount(configProperties.getPrefetchCount());
        // 根据配置属性设置初始并发消费者数量
        factory.setConcurrentConsumers(configProperties.getConsumerProcess());
        // 根据配置属性设置最大并发消费者数量
        factory.setMaxConcurrentConsumers(configProperties.getConsumerProcessMax());

        // 如果配置属性中的监听器确认模式为"manual"，则设置手动确认模式
        if (configProperties.getListenerAcknowledgeMode().equals("manual")) {
            factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);//设置确认模式手工确认
        }
        // 使用configurer进一步配置factory
        configurer.configure(factory, connectionFactory);
        // 返回配置后的factory
        return factory;
    }

    /**
     * 创建并配置一个名为"wyRabbitTemplate"的RabbitTemplate bean。
     * 这个方法旨在提供一个主用的RabbitTemplate实例，用于与RabbitMQ进行通信。
     * 通过指定ConnectionFactory为"wyConnectionFactory"，确保这个模板使用的是特定的连接工厂。
     * 配置了消息确认回调，以处理消息发送后的确认逻辑。
     *
     * @param connectionFactory 用于创建RabbitTemplate的ConnectionFactory。这个ConnectionFactory应该是特定于"wyConnectionFactory"的。
     * @return 配置好的RabbitTemplate实例，可用于发送消息到RabbitMQ。
     */
    @Bean(name = "wyRabbitTemplate")
    @Primary
    public RabbitTemplate wyRabbitTemplate(
            @Qualifier("wyConnectionFactory") ConnectionFactory connectionFactory
    ) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);

        // 开启消息必须被服务器接收的标志，确保消息不会无声地丢失
        //失败通知
        template.setMandatory(true);
        // 配置确认回调，用于处理消息发送后服务器的确认响应。
        //发送方确认
        template.setConfirmCallback(confirmCallback());

        return template;
    }

    /**
     * 定义一个Bean方法，用于在RabbitMQ中声明一个主题交换机。
     * 此方法通过Spring框架的@Bean注解进行声明，使得Spring容器能够管理这个方法生成的实例。
     *
     * @param connectionFactory 用于创建RabbitMQ连接的ConnectionFactory对象。
     *                          使用@Qualifier注解指定具体的ConnectionFactory bean，确保正确地获取到Wy工厂创建的连接。
     * @return 总是返回true，表示方法执行成功。这里可以考虑返回更丰富的信息，比如是否成功声明了交换机。
     * @throws Exception 如果创建连接或声明交换机过程中发生错误，将打印堆栈跟踪信息。
     */
    @Bean
    public Boolean wyDeclareTopicExchange(
            @Qualifier("wyConnectionFactory") ConnectionFactory connectionFactory
    ) {
        try {
            // 创建一个连接并打开一个通道，然后在通道上声明一个主题交换机。
            // 这里的EXCHANGE是预定义的主题交换机名称，ExchangeTypes.TOPIC指定交换机的类型为主题类型。
            connectionFactory.createConnection().createChannel(false).exchangeDeclare(EXCHANGE, ExchangeTypes.TOPIC, true);
        } catch (Exception e) {
            // 如果在声明交换机过程中遇到任何异常，打印异常堆栈跟踪信息。
            e.printStackTrace();
        }
        // 方法始终返回true，表示交换机声明操作已执行。
        // 在实际应用中，可能需要根据操作的实际结果返回更具体的布尔值或抛出异常。
        return true;
    }

    /**
     * 定义一个Bean方法，用于测试RabbitMQ的队列绑定。
     * 此方法主要用于在应用启动时，确保指定的队列已经被声明和绑定到正确的交换机上。
     * 使用了Spring的@Bean注解，表明这个方法会返回一个对象，该对象被Spring容器管理。
     *
     * @param connectionFactory 用于创建RabbitMQ连接的ConnectionFactory对象。
     *                          通过@Qualifier注解指定了特定的ConnectionFactory bean。
     * @return 总是返回true，表示队列的声明和绑定操作成功或者已经被执行过。
     */
    @Bean
    public Boolean testQueue(
            @Qualifier("wyConnectionFactory") ConnectionFactory connectionFactory
    ) {
        try {
            Channel channel = connectionFactory.createConnection().createChannel(false);
            channel.queueDeclare(TEST_QUEUE, true, false, false, null);
            channel.queueBind(TEST_QUEUE, EXCHANGE, TEST_ROUTING_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}