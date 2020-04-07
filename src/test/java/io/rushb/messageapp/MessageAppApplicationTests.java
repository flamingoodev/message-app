package io.rushb.messageapp;

import io.rushb.allmq.MqTemplate;
import io.rushb.allmq.factory.ConnectionFactory;
import io.rushb.allmq.message.connection.Connection;
import io.rushb.allmq.message.consumer.consumer.Consumer;
import io.rushb.allmq.message.consumer.listener.MessageListener;
import io.rushb.allmq.message.message.Configuration;
import io.rushb.allmq.message.message.MQ;
import io.rushb.allmq.message.message.Message;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class MessageAppApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void kafkaTest() {
        Configuration configuration = new Configuration();
        configuration.add("mq", MQ.KAFKA);
        configuration.add("bootstrap.servers", "116.62.150.178:9092");
        configuration.add("group.id", "TEST_GROUP");
        configuration.add("enable.auto.commit", "true");
        configuration.add("auto.commit.interval.ms", "1000");
        configuration.add("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configuration.add("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        ConnectionFactory.build(configuration);
        MqTemplate.send("test123", new Message("这是一个mqTemplate发出的消息"));
        Message message = MqTemplate.listen("test123");
        System.out.println(message);
    }

    @Test
    void activeMqProducerTest() {
        Configuration configuration = new Configuration();
        configuration.add("mq", MQ.ACTIVEMQ);
        configuration.add("username", "admin");
        configuration.add("password", "admin");
        configuration.add("brokerURL", "failover:(tcp://localhost:61616)");
        ConnectionFactory.build(configuration);
        MqTemplate.send("test123", new Message("这是一个mqTemplate发出的消息2"));
    }

    @Test
    void activeMqConsumerTest() throws IOException {
        Configuration configuration = new Configuration();
        configuration.add("mq", MQ.ACTIVEMQ);
        configuration.add("username", "admin");
        configuration.add("password", "admin");
        configuration.add("brokerURL", "failover:(tcp://localhost:61616)");
        ConnectionFactory.build(configuration);
        Connection connection = ConnectionFactory.getInstance().getConnection();
        Consumer consumer = connection.createConsumer("test123");
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                System.out.println(msg);
            }
        });
        System.out.println(System.in.read());
    }
}
