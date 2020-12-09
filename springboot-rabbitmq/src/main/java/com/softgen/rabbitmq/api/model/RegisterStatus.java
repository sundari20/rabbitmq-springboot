package com.softgen.rabbitmq.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterStatus {
	private Register register;
	private String status; //progress, completed  
	private String message;
	
	

}
