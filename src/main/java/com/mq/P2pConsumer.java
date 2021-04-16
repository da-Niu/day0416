package com.mq;

import com.alibaba.fastjson.JSON;
import com.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 消费者
 * @Author:谁
 * @Date:2021/4/13 8:39
 */
@Component
public class P2pConsumer {
    @Autowired
    private Queue queue;
    @Value("${spring.mail.username}")
    private String faJianRen;

    @Autowired
    private JavaMailSender mailSender; //发送邮件工具类

    @JmsListener(destination = "${my_queue}")
    public void consumer(TextMessage textMessage) throws JMSException, MessagingException {
        String str = textMessage.getText();
        System.out.println("str："+str);
        Email emai = JSON.parseObject(str,Email.class);
        System.out.println("emai："+emai);
        MimeMessage mimeMessage = mailSender.createMimeMessage(); //创建数据包
        MimeMessageHelper simpleMailMessage = new MimeMessageHelper(mimeMessage,true,"UTF-8");
        simpleMailMessage.setSubject(emai.getHead());
        simpleMailMessage.setText(emai.getText());
        System.out.println("faJianRen:"+faJianRen);
        simpleMailMessage.setFrom(faJianRen);
        simpleMailMessage.setTo(emai.getUurl());
        mailSender.send(mimeMessage);
        System.out.println("发送成功!!");
    }
}
