package com.sqs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.sqs.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MainService {
    
    @Value("${cloud.aws.sqs.queue.name}")
    private String userQueueName;

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public MainService(AmazonSQS amazonSQS) {
        this.queueMessagingTemplate = new QueueMessagingTemplate((AmazonSQSAsync) amazonSQS);
    }

    // 유저 정보 가져오기
    public void getUserMessage() {

        // 여러 정보가 있어서 선입선출로 인하여 제일 먼저 넣었던 메세지를 꺼내오고, 그 메세지를 삭제한다.
        UserDto userDto = queueMessagingTemplate.receiveAndConvert(userQueueName, UserDto.class);
        System.out.println("---------메세지 확인------");
        System.out.println("SQS로부터 받은 메시지 : " + userDto.toString());

    }
    
    public void sendMessage(UserDto userMatchDto) {
        log.info("SQS에 메세지 저장");
        queueMessagingTemplate.convertAndSend(userQueueName, userMatchDto);
    }

}
