package com.example.auctionapplication.domain.auction.rabbitmq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.core.ParameterizedTypeReference;

public class RabbitMQTemplate implements AmqpTemplate {
    @Override
    public void send(Message message) throws AmqpException {

    }

    @Override
    public void send(String s, Message message) throws AmqpException {

    }

    @Override
    public void send(String s, String s1, Message message) throws AmqpException {

    }

    @Override
    public void convertAndSend(Object o) throws AmqpException {

    }

    @Override
    public void convertAndSend(String s, Object o) throws AmqpException {

    }

    @Override
    public void convertAndSend(String s, String s1, Object o) throws AmqpException {

    }

    @Override
    public void convertAndSend(Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {

    }

    @Override
    public void convertAndSend(String s, Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {

    }

    @Override
    public void convertAndSend(String s, String s1, Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {

    }

    @Override
    public Message receive() throws AmqpException {
        return null;
    }

    @Override
    public Message receive(String s) throws AmqpException {
        return null;
    }

    @Override
    public Message receive(long l) throws AmqpException {
        return null;
    }

    @Override
    public Message receive(String s, long l) throws AmqpException {
        return null;
    }

    @Override
    public Object receiveAndConvert() throws AmqpException {
        return null;
    }

    @Override
    public Object receiveAndConvert(String s) throws AmqpException {
        return null;
    }

    @Override
    public Object receiveAndConvert(long l) throws AmqpException {
        return null;
    }

    @Override
    public Object receiveAndConvert(String s, long l) throws AmqpException {
        return null;
    }

    @Override
    public <T> T receiveAndConvert(ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return null;
    }

    @Override
    public <T> T receiveAndConvert(String s, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return null;
    }

    @Override
    public <T> T receiveAndConvert(long l, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return null;
    }

    @Override
    public <T> T receiveAndConvert(String s, long l, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return null;
    }

    @Override
    public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback) throws AmqpException {
        return false;
    }

    @Override
    public <R, S> boolean receiveAndReply(String s, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback) throws AmqpException {
        return false;
    }

    @Override
    public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, String s, String s1) throws AmqpException {
        return false;
    }

    @Override
    public <R, S> boolean receiveAndReply(String s, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, String s1, String s2) throws AmqpException {
        return false;
    }

    @Override
    public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, ReplyToAddressCallback<S> replyToAddressCallback) throws AmqpException {
        return false;
    }

    @Override
    public <R, S> boolean receiveAndReply(String s, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, ReplyToAddressCallback<S> replyToAddressCallback) throws AmqpException {
        return false;
    }

    @Override
    public Message sendAndReceive(Message message) throws AmqpException {
        return null;
    }

    @Override
    public Message sendAndReceive(String s, Message message) throws AmqpException {
        return null;
    }

    @Override
    public Message sendAndReceive(String s, String s1, Message message) throws AmqpException {
        return null;
    }

    @Override
    public Object convertSendAndReceive(Object o) throws AmqpException {
        return null;
    }

    @Override
    public Object convertSendAndReceive(String s, Object o) throws AmqpException {
        return null;
    }

    @Override
    public Object convertSendAndReceive(String s, String s1, Object o) throws AmqpException {
        return null;
    }

    @Override
    public Object convertSendAndReceive(Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        return null;
    }

    @Override
    public Object convertSendAndReceive(String s, Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        return null;
    }

    @Override
    public Object convertSendAndReceive(String s, String s1, Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        return null;
    }

    @Override
    public <T> T convertSendAndReceiveAsType(Object o, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return null;
    }

    @Override
    public <T> T convertSendAndReceiveAsType(String s, Object o, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return null;
    }

    @Override
    public <T> T convertSendAndReceiveAsType(String s, String s1, Object o, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return null;
    }

    @Override
    public <T> T convertSendAndReceiveAsType(Object o, MessagePostProcessor messagePostProcessor, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return null;
    }

    @Override
    public <T> T convertSendAndReceiveAsType(String s, Object o, MessagePostProcessor messagePostProcessor, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return null;
    }

    @Override
    public <T> T convertSendAndReceiveAsType(String s, String s1, Object o, MessagePostProcessor messagePostProcessor, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return null;
    }
}
