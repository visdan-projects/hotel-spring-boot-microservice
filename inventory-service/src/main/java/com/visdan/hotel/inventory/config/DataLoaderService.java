package com.visdan.hotel.inventory.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visdan.hotel.inventory.model.Address;
import com.visdan.hotel.inventory.model.AddressType;
import com.visdan.hotel.inventory.model.Invoice;
import com.visdan.hotel.inventory.model.InvoiceStatus;
import com.visdan.hotel.inventory.model.Reservation;
import com.visdan.hotel.inventory.model.ReservationStatus;
import com.visdan.hotel.inventory.model.Room;
import com.visdan.hotel.inventory.model.RoomBooking;
import com.visdan.hotel.inventory.model.RoomType;
import com.visdan.hotel.inventory.repository.InvoiceRepository;
import com.visdan.hotel.inventory.repository.ReservationRepository;
import com.visdan.hotel.inventory.repository.RoomRepository;
import com.visdan.hotel.inventory.utils.RoomPriceCalculator;

@Service
public class DataLoaderService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	public void load() {
		invoiceRepository.deleteAll();
		reservationRepository.deleteAll();
		roomRepository.deleteAll();
		
		Address address = createAddress();
		
		List<Room> rooms = createRooms();
		rooms = (List<Room>)roomRepository.save(rooms);
		
		Reservation reservation = createReservation("1234567890", "demo", rooms.get(0));
		reservation = reservationRepository.save(reservation);
		
		Invoice invoice = new Invoice("demo", address);
		invoice.setId("9876543210");
		invoice.getReservations().add(reservation);
		invoice.setStatus(InvoiceStatus.SENT);
		invoiceRepository.save(invoice);
	}
	
	private Address createAddress() {
		Address address = new Address();
		address.setAddressType(AddressType.BILLING);
		address.setCity("Vancouver");
		address.setCountry("Canada");
		address.setId(1L);
		address.setPostalCode("V1V1V1");
		address.setProvince("BC");
		address.setStreet1("1st Street");
		return address;
	}
	
	private List<Room> createRooms() {
		RoomBooking booking1 = new RoomBooking();
		booking1.setAccountNumber("1234567890");
		booking1.setEnd("15/06/2018");
		booking1.setStart("01/06/2018");
		booking1.setId("11111");
		booking1.setUsername("demo");
		
		Room room1 = new Room();
		List<RoomBooking> room1Bookings = new ArrayList<>();
		room1Bookings.add(booking1);
		room1.setBookings(room1Bookings);
		room1.setDescription("First room");
		room1.setId("1");
		room1.setMaxOccupancy(2);
		room1.setPrice(RoomPriceCalculator.calculatePrice(RoomType.QUEEN));
		room1.setRoomNumber("1");
		room1.setRoomType(RoomType.QUEEN);
		room1.setSmoking(false);
		
		Room room2 = new Room();
		List<RoomBooking> room2Bookings = new ArrayList<>();
		room2.setBookings(room2Bookings);
		room2.setDescription("Second room");
		room2.setId("2");
		room2.setMaxOccupancy(4);
		room2.setPrice(RoomPriceCalculator.calculatePrice(RoomType.JUNIOR_SUITE));
		room2.setRoomNumber("2");
		room2.setRoomType(RoomType.JUNIOR_SUITE);
		room2.setSmoking(false);
		
		List<Room> rooms = new ArrayList<>();
		rooms.add(room1);
		rooms.add(room2);
		
		return rooms;
	}
	
	private Reservation createReservation(String accountNumber, String username, Room room) {
		Reservation reservation = new Reservation(accountNumber, username);
		reservation.getRooms().add(room);
		reservation.setStatus(ReservationStatus.CONFIRMED);
		reservation.setId("99999");
		return reservation;
	}
}
