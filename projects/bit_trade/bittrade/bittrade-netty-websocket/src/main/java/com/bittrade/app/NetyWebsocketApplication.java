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
		System.setProperty( "svc_name", "netty-websocket" );
		SpringApplication sa = new SpringApplication( NetyWebsocketApplication.class );
		sa.run( args );
	}
}
