package com.visdan.hotel.authentication.repository;

import org.springframework.data.repository.CrudRepository;

import com.visdan.hotel.authentication.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	public User findByUsername(String username);
}
