package com.visdan.hotel.inventory.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.visdan.hotel.inventory.model.Reservation;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, String> {
	public List<Reservation> findByUsername(@Param("username") String username);
}
