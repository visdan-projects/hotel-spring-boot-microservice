package com.visdan.hotel.trace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class Application {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String [] args) {
		SpringApplication.run(Application.class, args);
	}
}
