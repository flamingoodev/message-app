package io.rushb.messageapp.controller;

import io.rushb.allmq.MqTemplate;
import io.rushb.allmq.factory.ConnectionFactory;
import io.rushb.allmq.message.connection.Connection;
import io.rushb.allmq.message.consumer.consumer.Consumer;
import io.rushb.allmq.message.consumer.listener.MessageListener;
import io.rushb.allmq.message.message.Configuration;
import io.rushb.allmq.message.message.KeyValueMessage;
import io.rushb.allmq.message.message.MQ;
import io.rushb.allmq.message.message.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/8 9:53
 */
@RestController
@RequestMapping("message")
public class MessageController {

    private String test = "test";

    private String hello = "hello";

    /**
     * 初始化ActiveMQ连接
     * 注意：
     * initActiveMq和initKafka同时用@PostConstruct注解时，将会与第一个执行的init建立连接。
     * 若采用数据库配置，只用写一个通用的init即可，系统启动时即可加载连接。
     */
    @PostConstruct
    public void initActiveMq() {
        // 配置请参考spring ActiveMQ的配置项
        Configuration configuration = new Configuration();
        configuration.add("mq", MQ.ACTIVEMQ);
        configuration.add("username", "admin");
        configuration.add("password", "admin");
        configuration.add("brokerURL", "failover:(tcp://localhost:61616)");
        ConnectionFactory.build(configuration);
    }

    /**
     * 初始化Kafka连接
     * initActiveMq和initKafka同时用@PostConstruct注解时，将会与第一个执行的init建立连接。
     * 若采用数据库配置，只用写一个通用的init即可，系统启动时即可加载连接。
     */
    @PostConstruct
    public void initKafka() {
        // 配置请参考spring kafka的配置项
        Configuration configuration = new Configuration();
        configuration.add("mq", MQ.KAFKA);
        configuration.add("bootstrap.servers", "localhost:9092");
        configuration.add("group.id", "TEST_GROUP");
        configuration.add("enable.auto.commit", "true");
        configuration.add("auto.commit.interval.ms", "1000");
        configuration.add("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configuration.add("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configuration.add("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configuration.add("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        ConnectionFactory.build(configuration);
    }

    @GetMapping("/send/{message}")
    public String send(@PathVariable("message") String message) {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        System.out.println(connection.getClass().getName());
        MqTemplate.send(test, new Message(message));
        MqTemplate.send(hello, new KeyValueMessage(hello, message));
        return "message send success: " + message;
    }

    @Bean
    public void listenTest() {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        Consumer consumer = connection.createConsumer(test);
        // 此处可用lambda表达式改写
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                // do something...
                System.out.println("Test:");
                System.out.println(msg);
            }
        });
    }

    @Bean
    public void listenHello() {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        Consumer consumer = connection.createConsumer(hello);
        // 使用lambda表达式改写
        consumer.setMessageListener(msg -> {
            // do something...
            System.out.println("Hello:");
            System.out.println(msg);
        });
    }
}
