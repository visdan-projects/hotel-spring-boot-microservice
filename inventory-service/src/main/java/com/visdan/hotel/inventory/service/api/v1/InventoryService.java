package com.visdan.hotel.inventory.service.api.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.visdan.hotel.inventory.client.AccountRestTemplateClient;
import com.visdan.hotel.inventory.model.Account;
import com.visdan.hotel.inventory.model.Address;
import com.visdan.hotel.inventory.model.Invoice;
import com.visdan.hotel.inventory.model.InvoiceStatus;
import com.visdan.hotel.inventory.model.Reservation;
import com.visdan.hotel.inventory.model.ReservationStatus;
import com.visdan.hotel.inventory.model.Room;
import com.visdan.hotel.inventory.model.RoomBooking;
import com.visdan.hotel.inventory.repository.InvoiceRepository;
import com.visdan.hotel.inventory.repository.ReservationRepository;
import com.visdan.hotel.inventory.repository.RoomRepository;

@Service
public class InventoryService {

	private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

	@Autowired
	private AccountRestTemplateClient accountClient;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private RoomRepository roomRepository;

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000") })
	public List<Room> getRooms() {
		List<Room> rooms = new ArrayList<>();
		roomRepository.findAll().forEach(rooms::add);
		return rooms;
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000") })
	public Room getRoom(String id) {
		return roomRepository.findOne(id);
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000") })
	public Boolean updateRoom(Room room) {
		roomRepository.save(room);
		return Boolean.TRUE;
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000") })
	public Boolean saveRoom(Room room) {
		roomRepository.save(room);
		return Boolean.TRUE;
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000") })
	public Boolean deleteRoom(String id) {
		roomRepository.delete(id);
		return Boolean.TRUE;
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000") })
	public Boolean bookRoom(Room room, RoomBooking roomBooking) throws Exception {
		Account account = accountClient.getAccount();
		if (account == null) {
			throw new Exception("Invalid user account.");
		}

		String id = Long.toString(System.currentTimeMillis());
		roomBooking.setId(id);
		roomBooking.setUsername(account.getUsername());
		roomBooking.setAccountNumber(account.getAccountNumber());
		room.getBookings().add(roomBooking);
		Room savedRoom = roomRepository.save(room);

		Reservation reservation = null;
		List<Reservation> reservations = getReservationsForUsername(account.getUsername());
		if (reservations != null && !reservations.isEmpty()) {
			Optional<Reservation> o = reservations.stream().filter(r -> r.getStatus() == ReservationStatus.PENDING)
					.findFirst();
			reservation = o.orElseGet(() -> new Reservation(account.getAccountNumber(), account.getUsername()));
		} else {
			reservation = new Reservation(account.getAccountNumber(), account.getUsername());
		}

		reservation.getRooms().add(savedRoom);
		reservationRepository.save(reservation);

		return Boolean.TRUE;
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000") })
	public Boolean checkout(String username) throws Exception {
		Reservation reservation = null;
		List<Reservation> reservations = getReservationsForUsername(username);
		if (reservations != null && !reservations.isEmpty()) {
			Optional<Reservation> o = reservations.stream().filter(r -> r.getStatus() == ReservationStatus.PENDING)
					.findFirst();
			if (!o.isPresent()) {
				throw new Exception("There are no pending reservations for this user.");
			}

			reservation = o.get();
		}

		if (reservation == null) {
			throw new Exception("There are no pending reservations for this user.");
		}

		reservation.setStatus(ReservationStatus.COMPLETED);
		Reservation savedReservation = reservationRepository.save(reservation);

		Invoice invoice = new Invoice(username, new Address());
		invoice.getReservations().add(savedReservation);
		invoice.setStatus(InvoiceStatus.SENT);
		invoiceRepository.save(invoice);

		return Boolean.TRUE;
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000") })
	public List<Invoice> getInvoicesForUsername(String username) throws Exception {
		validateUsername(username);

		List<Invoice> invoices = invoiceRepository.findByUsername(username).stream().filter(i -> i != null)
				.collect(Collectors.toList());
		
		for (Invoice inv : invoices) {
			for (Reservation res : inv.getReservations()) {
				for (Room room : res.getRooms()) {
					List<RoomBooking> bookings = room.getBookings().stream().filter(b -> b.getUsername().equals(username))
							.collect(Collectors.toList());
					room.setBookings(bookings);
				}
			}
		}
		
		return invoices;
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000") })
	public List<Reservation> getReservationsForUsername(String username) throws Exception {
		validateUsername(username);

		List<Reservation> reservations = reservationRepository.findByUsername(username).stream().filter(r -> r != null)
				.collect(Collectors.toList());
		
		for (Reservation res : reservations) {
			for (Room room : res.getRooms()) {
				List<RoomBooking> bookings = room.getBookings().stream().filter(b -> b.getUsername().equals(username))
						.collect(Collectors.toList());
				room.setBookings(bookings);
			}
		}

		return reservations;
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000") })
	protected boolean validateAccountNumber(String accountNumber) throws Exception {
		Account account = accountClient.getAccount();
		if (account != null && account.getAccountNumber().equals(accountNumber)) {
			logger.debug("Customer account matches reservation.");
			return true;
		}

		throw new Exception("Invalid account number.");
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000") })
	protected boolean validateUsername(String username) throws Exception {
		Account account = accountClient.getAccount();
		if (account != null && account.getUsername().equals(username)) {
			logger.debug("Customer username matches reservation.");
			return true;
		}

		throw new Exception("Invalid username.");
	}
}
