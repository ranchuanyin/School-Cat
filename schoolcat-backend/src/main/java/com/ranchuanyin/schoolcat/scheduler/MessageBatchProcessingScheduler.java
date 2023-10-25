/*
package com.ranchuanyin.schoolcat.scheduler;

import com.ranchuanyin.schoolcat.domain.ReceiveMessagesVo;
import com.ranchuanyin.schoolcat.rabbitmq.RabbitMQConsumerService;
import com.ranchuanyin.schoolcat.service.ReceiveMessagesVoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MessageBatchProcessingScheduler {
    @Resource
    ReceiveMessagesVoService receiveMessagesVoService;
    @Resource
    RabbitMQConsumerService rabbitMQConsumerService;

    @Scheduled(fixedRate = 10000)
    public void processBatch() {
        List<ReceiveMessagesVo> receiveMessagesVos = rabbitMQConsumerService.consumeMessages();
        receiveMessagesVoService.saveBatch(receiveMessagesVos);
    }
}
*/
