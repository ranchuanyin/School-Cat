package com.ranchuanyin.schoolcat.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Bean("directExchange")  //定义交换机Bean，可以很多个
    public Exchange exchange() {
        return ExchangeBuilder.directExchange("message-mqtt").build();
    }

    @Bean("messageQueue")     //定义消息队列
    public Queue queue() {
        return QueueBuilder
                .nonDurable("message-queue")   //非持久化类型
                .build();
    }

    @Bean("binding")
    public Binding binding(@Qualifier("directExchange") Exchange exchange,
                           @Qualifier("messageQueue") Queue queue) {
        //将我们刚刚定义的交换机和队列进行绑定
        return BindingBuilder
                .bind(queue)   //绑定队列
                .to(exchange)  //到交换机
                .with("message-push")   //使用自定义的routingKey
                .noargs();
    }

    @Bean("jacksonConverter")
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean("customContainerFactory")
    public SimpleRabbitListenerContainerFactory customContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setBatchListener(true);//开启批处理监听
        factory.setConsumerBatchEnabled(true);//开启批处理
        factory.setConcurrentConsumers(1);  //设置线程数
        factory.setMaxConcurrentConsumers(1); //最大线程数
        factory.setBatchSize(500);//每次从队列中取出的数据量，
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}
