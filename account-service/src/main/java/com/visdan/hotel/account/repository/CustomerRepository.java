package com.visdan.hotel.account.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.visdan.hotel.account.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	Customer findCustomerByAccountId(@Param("account_id") Long accountId);
}
