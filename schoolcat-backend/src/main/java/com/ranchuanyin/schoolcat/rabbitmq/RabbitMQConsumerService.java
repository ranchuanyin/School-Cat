package com.ranchuanyin.schoolcat.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import com.ranchuanyin.schoolcat.domain.ReceiveMessagesVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RabbitMQConsumerService {


    public List<ReceiveMessagesVo> consumeMessages(Long id) {
        // 从队列中获取并消费消息
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("116.204.133.43"); // RabbitMQ 服务器地址
        factory.setPort(5672);  //注意这里写5672，是amqp协议端口
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");

        List<ReceiveMessagesVo> list = new ArrayList<>();
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            for (int i = 0; i < 500; i++) {
                GetResponse response = channel.basicGet("message-queue", false); // 第二个参数设置为 false 表示手动确认
                if (response != null) {
                    byte[] body = response.getBody();
                    ReceiveMessagesVo o = JSONObject.parseObject(body, ReceiveMessagesVo.class);
                    long deliveryTag = response.getEnvelope().getDeliveryTag();
                    if (o.getToUserId().equals(id)) {
                        list.add(o);
                        // 手动确认消息的消费
                        channel.basicAck(deliveryTag, false);
                    } else
                        channel.basicReject(deliveryTag, true);
                } else {

                    break;
                }
            }
            channel.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
