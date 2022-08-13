package com.sqs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
	
	@Value("${cloud.aws.end-point.uri}")
	private String endPoint;
	
	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;
	
	@GetMapping("send/{msg}")
	public String sendMessage(@PathVariable("msg") String message) {
		queueMessagingTemplate.send(endPoint, MessageBuilder.withPayload(message).build());
		return "Message published successfully !";
	}

	@SqsListener("MySQSQueue")
	public void loadMessageFromQueue(String message){
		System.out.println("SQS queue Message: "+message);

	}

}
