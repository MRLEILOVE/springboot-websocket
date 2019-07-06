package com.bittrade.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("com.bittrade.*")
@EnableScheduling
@SpringBootApplication
public class NetyWebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run( NetyWebsocketApplication.class, args );
	}
}
