# Inventory Service - Spring Boot

The Inventory Service acts as a central repository for all hotel room information, and also provides access to searching for available inventory and completing room bookings and registrations. The service acts as an OAuth2 client, and for defined protected resources will check whether a user is both Authenticated and Authorized to access and view the resource. For each of the protected resources, the incoming request must have the `Authorization` header present. The service will retrieve any relevant account information through a `REST` based client call to the Account Service, via its `OAuth2` template. 

The Inventory Service is registered with the API Gateway, and the gateway will route all incoming requests via its `/api` route. Furthermore, the Inventory Service upon start up will negotiate any necessary deployment related configuration that is outside the scope of the code with the Spring Cloud Configuration Server. 

### Unprotected Resources

Searching for room availability, or information on a particular room, can be achieved through unprotected resources `/inventory/v1/rooms` or `/inventory/v1/rooms/{roomId}`. 

![GetRooms](https://i.imgur.com/pr6ZaEP.jpg)

![GetRoom](https://i.imgur.com/8JGvpn0.png)

### Room Booking - Protected Resource

The user must be Authenticated and Authorized in order to create a new booking for an available room. This is achieved with a `PUT` request on the `/inventory/v1/rooms/book/{roomId}` resource. Within the request body, the room booking start and end dates need to be provided. Also ensure that the `Content-Type` header is set as `application/json`.

![BookRoom](https://i.imgur.com/PdRnTrF.jpg)

### User Reservations - Protected Resource

Viewing of any existing or pending reservations is performed via a `GET` request on the `/inventory/v1/reservations/{username}` resource. The user must be Authenticated and Authorized to view this information.

![ViewReservations](https://i.imgur.com/ZkofHWV.jpg)

### Booking Checkout - Protected Resource

Completing a reservation via a `POST` request through the checkout resource `/inventory/v1/checkout/{username}` is a protected resource and the user must be Authenticated and Authorized. If there are no pending reservations, an error will be displayed.

![Checkout](https://i.imgur.com/c75BELT.jpg)

### User Invoices - Protected Resource

Viewing of any existing or pending invoices is performed via a `GET` request on the `/inventory/v1/invoices/{username}` resource. The user must be Authenticated and Authorized to view this information.

![ViewInvoice](https://i.imgur.com/7wisW33.jpg)

