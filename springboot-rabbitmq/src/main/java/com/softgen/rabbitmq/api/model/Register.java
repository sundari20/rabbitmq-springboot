package com.softgen.rabbitmq.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Register {
	
	private String id;
	private String name;
	private Address address;
	
	

}
