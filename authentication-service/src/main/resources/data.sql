INSERT INTO users(id, username, password, enabled) VALUES (1, 'demo', '$2a$10$tBsilg/TlO7fm6BHbcYejuXF5CP2ZvrrXkv3SbnD4OjYxcd0DaGla', true);
INSERT INTO users(id, username, password, enabled) VALUES (2, 'admin', '$2a$10$ANoH6Wm3H6s5Jso4v8FOS.NaPSNeWlT0TKOA06Ri6KMVYTboFyQTq', true);
INSERT INTO users(id, username, password, enabled) VALUES (3, 'carlos', '$2a$10$0UP2u2uCole4ek3ILgxCB.CPixmgMApeumN2d2YMIhvOjavEP7IO2', true);
INSERT INTO users(id, username, password, enabled) VALUES (4, 'alex', '$2a$10$cr7lMdmuBOyvcU/M7I6tHuYPYbgI0ciZduBsAQtfbUseY205ImrGK', true);

INSERT INTO roles(user_id, role, id) VALUES (1, 'ROLE_USER', 1);
INSERT INTO roles(user_id, role, id) VALUES (2, 'ROLE_USER', 2);
INSERT INTO roles(user_id, role, id) VALUES (2, 'ROLE_ADMIN', 3);
INSERT INTO roles(user_id, role, id) VALUES (3, 'ROLE_USER', 4);
INSERT INTO roles(user_id, role, id) VALUES (4, 'ROLE_USER', 5);
