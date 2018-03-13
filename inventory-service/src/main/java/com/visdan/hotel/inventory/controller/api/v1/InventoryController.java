package com.visdan.hotel.inventory.controller.api.v1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.visdan.hotel.inventory.model.Invoice;
import com.visdan.hotel.inventory.model.Reservation;
import com.visdan.hotel.inventory.model.Room;
import com.visdan.hotel.inventory.model.RoomBooking;
import com.visdan.hotel.inventory.service.api.v1.InventoryService;

@RestController
@RequestMapping(value = "/v1")
public class InventoryController {

	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	private InventoryService inventoryService;

	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
	public ResponseEntity<List<Room>> getRooms() throws Exception {
		List<Room> rooms = inventoryService.getRooms();
		return Optional.ofNullable(rooms).map(r -> new ResponseEntity<List<Room>>(r, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Unable to retrieve room information."));
	}

	@RequestMapping(value = "/rooms/{roomId}", method = RequestMethod.GET)
	public ResponseEntity<Room> getRoom(@PathVariable("roomId") String roomId) throws Exception {
		Room room = inventoryService.getRoom(roomId);
		return Optional.ofNullable(room).map(r -> new ResponseEntity<Room>(r, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Unable to retrieve room information."));
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/rooms/{roomId}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity updateRoom(@PathVariable("roomId") String roomId, @RequestBody Room room) throws Exception {
		return Optional.ofNullable(inventoryService.updateRoom(room))
				.map(u -> new ResponseEntity(HttpStatus.NO_CONTENT))
				.orElseThrow(() -> new Exception("Unable to update room."));
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/rooms/{roomId}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity addRoom(@PathVariable("roomId") String roomId, @RequestBody Room room) throws Exception {
		return Optional.ofNullable(inventoryService.saveRoom(room)).map(u -> new ResponseEntity(HttpStatus.NO_CONTENT))
				.orElseThrow(() -> new Exception("Unable to add and save room."));
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/rooms/{roomId}", method = RequestMethod.DELETE)
	public ResponseEntity deleteRoom(@PathVariable("roomId") String roomId) throws Exception {
		Room room = inventoryService.getRoom(roomId);
		if (room == null) {
			throw new Exception("Unable to find room to delete.");
		}

		if (room.getBookings() != null && room.getBookings().size() > 0) {
			throw new Exception("Unable to delete room because it has existing bookings.");
		}

		return Optional.ofNullable(inventoryService.deleteRoom(roomId))
				.map(u -> new ResponseEntity(HttpStatus.NO_CONTENT))
				.orElseThrow(() -> new Exception("Unable to delete room."));
	}

	@RequestMapping(value = "/reservations/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<Reservation>> getReservations(@PathVariable("username") String username)
			throws Exception {
		List<Reservation> reservations = inventoryService.getReservationsForUsername(username);
		return Optional.ofNullable(reservations).map(r -> new ResponseEntity<List<Reservation>>(r, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Unable to retrieve reservations for user."));
	}

	@RequestMapping(value = "/invoices/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<Invoice>> getInvoices(@PathVariable("username") String username) throws Exception {
		List<Invoice> invoices = inventoryService.getInvoicesForUsername(username);
		return Optional.ofNullable(invoices).map(i -> new ResponseEntity<List<Invoice>>(i, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Unable to retrieve invoices for user."));
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/checkout/{username}", method = RequestMethod.POST)
	public ResponseEntity checkout(@PathVariable("username") String username) throws Exception {
		return Optional.ofNullable(inventoryService.checkout(username))
				.map(u -> new ResponseEntity(HttpStatus.NO_CONTENT))
				.orElseThrow(() -> new Exception("Unable to check out room reservation."));
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/rooms/book/{roomId}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity bookRoom(@PathVariable("roomId") String roomId, @RequestBody RoomBooking roomBooking)
			throws Exception {
		logger.debug("Received booking request + " + roomBooking + " for room: " + roomId);
		if (roomBooking == null) {
			throw new Exception("Room booking must be provided.");
		}

		String start = roomBooking.getStart();
		String end = roomBooking.getEnd();

		if ((start == null || start.trim().length() <= 0) || (end == null || end.trim().length() <= 0)) {
			throw new Exception("Start and end booking dates must be provided.");
		}

		String datePattern = "dd/MM/yyyy";
		boolean validStart = isValidFormat(datePattern, start);
		boolean validEnd = isValidFormat(datePattern, end);

		if (!validStart || !validEnd) {
			throw new Exception("Booking dates are not in the valid format: " + datePattern);
		}

		Room room = inventoryService.getRoom(roomId);
		if (room == null) {
			throw new Exception("Unable to find room to complete booking.");
		}

		if (!isBookingValid(room, start, end)) {
			throw new Exception("Room is already booked for the provided dates.");
		}

		return Optional.ofNullable(inventoryService.bookRoom(room, roomBooking))
				.map(u -> new ResponseEntity(HttpStatus.NO_CONTENT))
				.orElseThrow(() -> new Exception("Unable to save room booking."));
	}

	protected static boolean isBookingValid(Room room, String start, String end) {
		boolean result = true;
		for (RoomBooking rb : room.getBookings()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date rbStart = sdf.parse(rb.getStart());
				Date rbEnd = sdf.parse(rb.getEnd());
				Date dStart = sdf.parse(start);
				Date dEnd = sdf.parse(end);

				result = dEnd.compareTo(dStart) >= 0 && (dStart.compareTo(rbEnd) > 0 || dEnd.compareTo(rbStart) < 0);
				if (!result) {
					return false;
				}
			} catch (ParseException ex) {
				return false;
			}
		}

		return result;
	}

	protected static boolean isValidFormat(String format, String value) {
		Date date = null;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(value);
			if (!value.equals(sdf.format(date))) {
				date = null;
			}
		} catch (ParseException ex) {
			logger.debug("Error parsing date.", ex);
		}

		return date != null;
	}
}
