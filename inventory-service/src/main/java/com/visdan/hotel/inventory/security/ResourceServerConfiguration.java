package com.visdan.hotel.inventory.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/v1/rooms/**")
			.permitAll()
			.anyRequest()
			.permitAll()
			.antMatchers(HttpMethod.PUT, "/v1/rooms/**")
			.hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.antMatchers(HttpMethod.POST, "/v1/rooms/**")
			.hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.antMatchers(HttpMethod.DELETE, "/v1/rooms/**")
			.hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.antMatchers("/v1/reservations/**")
			.hasAnyRole("USER", "ADMIN")
			.anyRequest()
			.authenticated()
			.antMatchers("/v1/invoices/**")
			.hasAnyRole("USER", "ADMIN")
			.anyRequest()
			.authenticated()
			.antMatchers("/v1/rooms/book/**")
			.hasAnyRole("USER", "ADMIN")
			.anyRequest()
			.authenticated()
			.antMatchers("/v1/checkout/**")
			.hasAnyRole("USER", "ADMIN")
			.anyRequest()
			.authenticated();
	}
}
