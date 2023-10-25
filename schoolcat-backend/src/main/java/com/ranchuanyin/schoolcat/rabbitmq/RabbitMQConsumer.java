/*
package com.ranchuanyin.schoolcat.rabbitmq;

import com.ranchuanyin.schoolcat.domain.CatAccount;
import com.ranchuanyin.schoolcat.domain.ReceiveMessagesVo;
import com.ranchuanyin.schoolcat.domain.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "message-queue",messageConverter = "jacksonConverter")
    public void receiveMessage(ReceiveMessagesVo message) {
        System.out.println("Received: " + message);
    }

    private void processMessage(ReceiveMessagesVo message, Authentication authentication) {
        // 处理消息的业务逻辑，可以在这里添加你的代码
        User user = (User) authentication.getPrincipal();
        CatAccount catAccount = user.getCatAccount();
        Long id = catAccount.getId();
        if (message.getId().equals(id)) {
            System.out.println(message.toString());
        }else
            throw new RuntimeException("Processing failed. Message will be requeued.");
    }
}
*/
