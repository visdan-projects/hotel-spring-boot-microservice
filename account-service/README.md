# Account Service - Spring Boot

The Account Service will store user account information, such as credit card details, addresses, and additional user information. The service acts as an OAuth2 client, and for defined protected resources will check whether a user is both Authenticated and Authorized to access and view the resource. For each of the protected resources, the incoming request must have the `Authorization` header present. 

The Account Service is registered with the API Gateway, and the gateway will route all incoming requests via its `/api` route. Furthermore, the Account Service upon start up will negotiate any necessary deployment related configuration that is outside the scope of the code with the Spring Cloud Configuration Server. 

### Unauthorized User

Sample request from Postman indicating the request is unauthorized:

![UnauthorizedRequest](https://i.imgur.com/L98KHQ5.jpg)

### Authorized User

Once the user is authenticated, and the `Authorization` header provided in the request, customer and account information can be retrieved via the `account/v1/customer` route:

![AuthorizedRequest](https://i.imgur.com/avTLxMS.jpg)

