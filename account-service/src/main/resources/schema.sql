DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS credit_cards;
DROP TABLE IF EXISTS addresses;

CREATE TABLE accounts
(
	id BIGINT NOT NULL PRIMARY KEY,
	account_number VARCHAR(255) NOT NULL,
	username VARCHAR(255) NOT NULL
);

CREATE TABLE customers 
(
	account_id BIGINT REFERENCES accounts(id),
	id BIGINT NOT NULL PRIMARY KEY,
	email VARCHAR(255) NOT NULL,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL
);

CREATE TABLE credit_cards
(
	account_id BIGINT REFERENCES accounts(id),
	id BIGINT NOT NULL PRIMARY KEY,
	number VARCHAR(255) NOT NULL,
	type VARCHAR(255) NOT NULL
);

CREATE TABLE addresses
(
	account_id BIGINT REFERENCES accounts(id),
	id BIGINT NOT NULL PRIMARY KEY,
	address_type VARCHAR(255) NOT NULL,
	city VARCHAR(255) NOT NULL,
	country VARCHAR(255) NOT NULL,
	province VARCHAR(255) NOT NULL,
	street1 VARCHAR(255) NOT NULL,
	street2 VARCHAR(255),
	postal_code VARCHAR(255)
);