package com.softgen.rabbitmq.api.producer;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softgen.rabbitmq.api.model.Register;
import com.softgen.rabbitmq.api.model.RegisterStatus;

@RestController
@RequestMapping("/register")
public class Producer {
	@Value("${rabbitmq_queue}")
	public String queue;
	@Value("${rabbitmq_topic_exchange}")
	public String topic_exchange;
	@Value("${rabbitmq_routing_key}")
	public String routing_key;
	@Autowired
	private RabbitTemplate template;
	@PostMapping("/{collegeName}")
	public String formRegistration(@RequestBody Register register, @PathVariable String collegeName) {
	
		//payment service -- may connect with other microserices before responding the user
		
		register.setId(UUID.randomUUID().toString());
		RegisterStatus  registerStatus = new RegisterStatus(register,"PROCESS", "Registered successfully"+collegeName);
		
		template.convertAndSend(topic_exchange, routing_key, registerStatus);
		
		return "Registration success!!!!!!!";
	}

}
