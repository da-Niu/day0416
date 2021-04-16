package com.controller;

import com.alibaba.fastjson.JSON;
import com.entity.Email;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;

/**
 * @Author:谁
 * @Date:2021/4/13 9:06
 */
@Controller
@RequestMapping("/TestController")
public class TestController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;

    @RequestMapping("/sendEmail")
    @ResponseBody
    public String send(@RequestBody Email email){

        try {
            String str = JSON.toJSONString(email);
            TextMessage textMessage = new ActiveMQTextMessage();
            textMessage.setText(str);
            jmsMessagingTemplate.convertAndSend(queue,textMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return "发送成功";
    }
}
