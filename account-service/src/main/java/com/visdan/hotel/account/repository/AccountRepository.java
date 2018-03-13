package com.visdan.hotel.account.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.visdan.hotel.account.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
	Account findAccountByUsername(@Param("username") String username);
}
