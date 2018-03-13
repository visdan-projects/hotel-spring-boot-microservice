package com.visdan.hotel.account.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/v1/account/**")
			.hasAnyRole("USER", "ADMIN")
			.anyRequest()
			.authenticated()
			.antMatchers("/v1/customer/**")
			.hasAnyRole("USER", "ADMIN")
			.anyRequest()
			.authenticated();
	}
}
