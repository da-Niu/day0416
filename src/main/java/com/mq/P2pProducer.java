package com.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Queue;

/**
 * 生产者
 * @Author:谁
 * @Date:2021/4/12 21:18
 */
@Component
public class P2pProducer {
    @Autowired
    private Queue queue;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    public void send() throws JMSException {

        jmsMessagingTemplate.convertAndSend(queue,"textMessage123456");
        System.out.println("1");
    }
}
