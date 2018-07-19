package com.example.domain.auction.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Component
public class Sender {


    @Autowired
    private Queue queue;

    @Autowired
    private Connection connection;

//    private String exchange = "routeToQue";


    public void produceMsg(JSONObject msg) throws IOException, TimeoutException {

    Channel channel = connection.createChannel();
    channel.basicPublish("", queue.getName(),null, msg.toString().getBytes());
    channel.close();
    }

    private Channel createChannel() throws IOException {
        return connection.createChannel();
    }
}
