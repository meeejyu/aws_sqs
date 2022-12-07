package com.sqs.aws;

import java.util.Map;

import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.sqs.dto.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsSqsListener {

	// 유저 정보 읽어오기
	// @SqsListener(value = "${cloud.aws.sqs.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
	public void userListen(@Payload UserDto info, @Headers Map<String, String> headers, Acknowledgment ack) {
		log.info("-----------------Sqs가 설정해둔 폴링시간마다 알아서 정보를 가져옴--------------------");
		log.info("------info는 내가 넣은 메세지를 가져온다. {}", info);
		log.info("-----메세지의 전체적인 정보를 가져옴---", headers);
        // 메세지 삭제
		// ack.acknowledge();
	}

	/*
	 * 폴링을 진행중일때는 메세지가 숨김 상태가 된다.
	 * 그래서 MainService.getUserMessage와 동시에 사용할 수 없다.
	 */
}
