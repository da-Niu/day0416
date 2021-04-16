package com.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import javax.jms.Queue;

/**
 * @Author:谁
 * @Date:2021/4/12 21:07
 */
@Component
public class ActiveMQConfig {
    @Value("${my_queue}")
    private String myQueue;
    @Bean
    public Queue queue(){
        return new ActiveMQQueue(myQueue);
    }
}
