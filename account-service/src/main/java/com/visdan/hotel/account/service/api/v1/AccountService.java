package com.visdan.hotel.account.service.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visdan.hotel.account.model.Account;
import com.visdan.hotel.account.model.Customer;
import com.visdan.hotel.account.repository.AccountRepository;
import com.visdan.hotel.account.repository.CustomerRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public Account getAccount(String username) {
		Account account = accountRepository.findAccountByUsername(username);
		if (account == null) {
			return null;
		}

		maskCreditCardInfo(account);
		return account;
	}

	public Customer getCustomer(String username) {
		Account account = getAccount(username);
		if (account == null) {
			return null;
		}

		Customer customer = customerRepository.findCustomerByAccountId(account.getId());
		if (customer == null) {
			return null;
		}

		maskCreditCardInfo(customer.getAccount());
		return customer;
	}

	private void maskCreditCardInfo(Account account) {
		account.getCreditCards().forEach(cc -> cc.setNumber(cc.getNumber().replaceAll("([\\d]{4})(?!$)", "****-")));
	}
}
