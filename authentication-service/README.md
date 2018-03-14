# Authentication Service - Spring Boot

The Authentication Service acts both as a Resource and Authorization server, with the ability to authenticate and authorize existing users. Other services will communicate with the Authentication Service in order to obtain valid token information. The Authentication Service acts as a gateway for both protected and unprotected resources. 

The Authentication Service at its core uses Spring Cloud OAuth2. At the moment the primary way to authenticate a user is through a Password grant. The generated bearer token can be used, and will be propagated, in the request headers to protected resources. An endpoint is provided by the Authentication Service in order to view the user details for an authenticated user. 

### User Authentication

For basic authentication, the application client user name and secret are as follows:

```
client = visdanhotel
secret = secretkey
```

Within the form parameters, additional form data is necessary in order to authenticate a particular user with the application. In order to get started, the below user and password can be used to authenticate with the application and generate a bearer token:

```
username = demo
password = demoPassw0rd
```

Within the form request, the data passed in will also need to include a `grant_type` and `scope`. 

Using Postman, sending a `POST` request to the Authentication Service, in order to authentication the `demo` user would look as follows:

![ClientSecret](https://i.imgur.com/2PGaAj5.jpg)

![Authentication](https://i.imgur.com/kjcjAvx.jpg)

### User Details

To view a particular user details, and the set of assigned roles assigned to the user, a `GET` request needs to be issued at the Authentication Server `/auth/user` resource, along with the `Authorization` header containing the previously generated bearer token. Within Postman, such a request would look as follows:

![UserDetails](https://i.imgur.com/RSVLx0f.jpg)
