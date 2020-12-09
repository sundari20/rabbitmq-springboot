package com.softgen.rabbitmq.api.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.softgen.rabbitmq.api.model.RegisterStatus;

@Component
public class Consumer {
	
	
	@RabbitListener(queues = "${rabbitmq_queue}")
	public void consumeMessageFromQueue(RegisterStatus registerStatus) {
		System.out.println("Received message from Queue"+ registerStatus);
		
	}

}
