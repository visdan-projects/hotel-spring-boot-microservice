package com.visdan.hotel.account.controller.api.v1;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.visdan.hotel.account.model.Account;
import com.visdan.hotel.account.model.Customer;
import com.visdan.hotel.account.service.api.v1.AccountService;

@RestController
@RequestMapping(value = "/v1")
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public ResponseEntity<Account> getAccounts(Principal principalUser) throws Exception {
		logger.debug("Found Authorization header in controller: {} ", request.getHeader("Authorization"));
		logger.debug("Logged in user: {} ", principalUser.getName());
		Account account = accountService.getAccount(getUsernameFromSecurityContext(principalUser));
		return Optional.ofNullable(account)
				.map(a -> new ResponseEntity<Account>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Account does not exist."));
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomer(Principal principalUser) throws Exception {
		logger.debug("Found Authorization header in controller: {} ", request.getHeader("Authorization"));
		logger.debug("Logged in user: {} ", principalUser.getName());
		Customer customer = accountService.getCustomer(getUsernameFromSecurityContext(principalUser));
		return Optional.ofNullable(customer)
				.map(c -> new ResponseEntity<Customer>(c, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Customer does not exist."));
	}

	private String getUsernameFromSecurityContext(Principal principalUser) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			String str = principalUser.getName();
			int start = str.indexOf("username=") + 9;
			String sub = str.substring(start);
			username = sub.substring(0, sub.indexOf(","));
		}

		logger.debug("Fetching data for user: {} ", username);
		return username;
	}
}
