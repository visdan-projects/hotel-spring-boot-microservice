INSERT INTO accounts(id, account_number, username) VALUES (1, '1234567890', 'demo');
INSERT INTO accounts(id, account_number, username) VALUES (2, '1111111111', 'admin');
INSERT INTO accounts(id, account_number, username) VALUES (3, '2222222222', 'carlos');
INSERT INTO accounts(id, account_number, username) VALUES (4, '3333333333', 'alex');

INSERT INTO customers(account_id, id, email, first_name, last_name) VALUES (1, 1, 'demo@demo.com', 'demo', 'demo');
INSERT INTO customers(account_id, id, email, first_name, last_name) VALUES (2, 2, 'admin@admin.com', 'admin', 'admin');
INSERT INTO customers(account_id, id, email, first_name, last_name) VALUES (3, 3, 'carlos@me.com', 'carlos', 'carlos');
INSERT INTO customers(account_id, id, email, first_name, last_name) VALUES (4, 4, 'alex@me.com', 'alex', 'alex');

INSERT INTO credit_cards(account_id, id, number, type) VALUES (1, 1, '5555444433332222', 'VISA');
INSERT INTO credit_cards(account_id, id, number, type) VALUES (1, 2, '1111333399990000', 'MASTERCARD');
INSERT INTO credit_cards(account_id, id, number, type) VALUES (2, 3, 'zzzzzzzzzzzzzzzz', 'VISA');
INSERT INTO credit_cards(account_id, id, number, type) VALUES (3, 4, '9999888899998888', 'VISA');
INSERT INTO credit_cards(account_id, id, number, type) VALUES (4, 5, '6666777788881111', 'MASTERCARD');

INSERT INTO addresses(account_id, id, address_type, city, country, province, street1, street2, postal_code) VALUES (1, 1, 'BILLING', 'Vancouver', 'Canada', 'BC', '1 - First St.', '', 'V1K 1K1');
INSERT INTO addresses(account_id, id, address_type, city, country, province, street1, street2, postal_code) VALUES (1, 2, 'SHIPPING', 'Burnaby', 'Canada', 'BC', '2 - Second St.', '', 'V1K 1K1');
INSERT INTO addresses(account_id, id, address_type, city, country, province, street1, street2, postal_code) VALUES (2, 3, 'BILLING', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A');
INSERT INTO addresses(account_id, id, address_type, city, country, province, street1, street2, postal_code) VALUES (3, 4, 'BILLING', 'Santiago', 'Chile', 'Patagonia', 'Numero Uno', '', '');
INSERT INTO addresses(account_id, id, address_type, city, country, province, street1, street2, postal_code) VALUES (4, 5, 'BILLING', 'Kraznivostok', 'Russia', 'Siberia', '1 - Lenin Place', '', '');