package com.visdan.hotel.inventory.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.visdan.hotel.inventory.model.Room;

public interface RoomRepository extends PagingAndSortingRepository<Room, String> {
	
}
