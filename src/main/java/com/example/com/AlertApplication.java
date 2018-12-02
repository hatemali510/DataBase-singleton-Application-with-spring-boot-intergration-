package com.etistalat.com;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.etisalat.*"})

public class AlertApplication {
	
	public static void main(String[] args) throws Exception{
		SpringApplication.run(AlertApplication.class, args);
	}
	
	

}
