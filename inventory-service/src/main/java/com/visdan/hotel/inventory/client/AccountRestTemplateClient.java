package com.visdan.hotel.inventory.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import com.visdan.hotel.inventory.model.Account;
import com.visdan.hotel.inventory.model.Customer;

@Component
public class AccountRestTemplateClient {

	private static final Logger logger = LoggerFactory.getLogger(AccountRestTemplateClient.class);
	
	@Autowired
	private OAuth2RestTemplate template;
	
	public Account getAccount() {
		ResponseEntity<Customer> entity = 
				template.exchange(
						"http://apigatewayservice:5555/api/account/v1/customer",
						HttpMethod.GET,
						null,
						Customer.class);
		
		Customer customer = entity.getBody();
		Account account = customer.getAccount();
		logger.debug("Retrieved account information: " + account);
		return account;
	}
}
