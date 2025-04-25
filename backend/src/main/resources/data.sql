-- Insert roles
INSERT INTO role (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO role (id, name) VALUES (2, 'ROLE_ADMIN');

-- Insert a default user
INSERT INTO user (id, email, password) VALUES (1, 'user@example.com', 'password');

-- You can add more initial data as needed
