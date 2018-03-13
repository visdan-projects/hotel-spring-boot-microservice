package com.visdan.hotel.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableZuulProxy
public class Application {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
    @Bean
    public Sampler defaultSampler() {
    	return new AlwaysSampler();
    }
    
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    
	public static void main(String [] args) {
		SpringApplication.run(Application.class, args);
	}
}
