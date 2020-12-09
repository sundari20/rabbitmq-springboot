package com.softgen.rabbitmq.api.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

	@Value("${rabbitmq_queue}")
	public String queue;
	@Value("${rabbitmq_topic_exchange}")
	public String topic_exchange;
	@Value("${rabbitmq_routing_key}")
	public String routing_key;
	
	@Bean
	public Queue queue() {
		return new Queue(queue);
	}
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(topic_exchange);
	}
	
	@Bean
	public Binding binding(Queue queue, TopicExchange topic_exchange) {
		return BindingBuilder.bind(queue).to(topic_exchange).with(routing_key);
		
	}
	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate= new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter());
		return rabbitTemplate;
		
		
		
	}
}
