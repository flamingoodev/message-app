package io.rushb.messageapp.service;

import common.entity.ConnectionEntity;
import common.service.Connector;
import io.rushb.messageapp.dao.ConnectionDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:flamingodev@outlook.com">FLAMINGO</a>
 * @since 2020/4/3 17:05
 */
public class ConnectionService implements Connector {

    @Autowired
    private ConnectionDao connectionDao;

    @Override
    public ConnectionEntity getByCode(@NotNull String code) {
        ConnectionEntity connectionEntity = new ConnectionEntity();
        connectionEntity.setConnectionId(1L);
        connectionEntity.setConnectionCode("MQ");
        connectionEntity.setConnectionName("消息队列连接");
        connectionEntity.setProtocol("http");
        connectionEntity.setDomain("127.0.0.1");
        connectionEntity.setDomain("61616");
        connectionEntity.setArguments1("ActiveMQ");
        connectionEntity.setArguments2("127.0.0.1");
        return connectionEntity;
    }
}
