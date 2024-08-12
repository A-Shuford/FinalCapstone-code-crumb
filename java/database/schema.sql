BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, cake_style, cake_size, cake_flavor, cake_filling, 
cake_frosting, cart_item_status, cake, cart_item, cart_item_status, 
cart_item_cake, cart_item_cake, cake_price;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	yourName varchar(200) NOT NULL,
	email varchar(50) NOT NULL,
	phone_number varchar(12) NOT NULL,  
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE cake_style (
	cake_style_id SERIAL,
	style_name varchar(50) NOT NULL UNIQUE,
	CONSTRAINT pk_cake_style PRIMARY KEY(cake_style_id)
);

CREATE TABLE cake_size (
	cake_size_id SERIAL,
	size_name varchar(50) NOT NULL UNIQUE,
	CONSTRAINT pk_cake_size_id PRIMARY KEY(cake_size_id)
);

CREATE TABLE cake_flavor (
	cake_flavor_id SERIAL,
	flavor_name varchar(50) NOT NULL UNIQUE,
	CONSTRAINT pk_cake_flavor_id PRIMARY KEY(cake_flavor_id)
);

CREATE TABLE cake_filling (
	cake_filling_id SERIAL,
	filling_name varchar(50) NOT NULL UNIQUE,
	CONSTRAINT pk_cake_filling_id PRIMARY KEY(cake_filling_id)
);

CREATE TABLE cake_frosting (
	cake_frosting_id SERIAL,
	frosting_name varchar(50) NOT NULL UNIQUE,
	CONSTRAINT pk_cake_frosting_id PRIMARY KEY(cake_frosting_id)
);

CREATE TABLE cart_item_status ( 
	cart_item_status_id SERIAL, 
	status_name varchar(50), 
	CONSTRAINT pk_cart_item_status_id PRIMARY KEY(cart_item_status_id)
);

CREATE TABLE cake_price (
    cake_price_id SERIAL PRIMARY KEY,
    cake_style_id int NOT NULL REFERENCES cake_style(cake_style_id),
    cake_size_id int NOT NULL REFERENCES cake_size(cake_size_id),
	has_writing boolean NOT NULL,
	base_price decimal(8,2) NOT NULL,
    writing_fee decimal(8,2) DEFAULT 0.00,
    CONSTRAINT chk_cupcakes_no_writing CHECK (cake_style_id != 3 OR writing_fee = 0.00)
);

CREATE TABLE cake (
	cake_id SERIAL,
	cake_name varchar(50) NOT NULL,
	cake_style int NOT NULL,
	cake_size int NOT NULL,
	cake_flavor int NOT NULL,
	cake_filling int,
	cake_frosting int,
	cake_type varchar(50),
	has_writing boolean, 
	custom_text varchar(100),
	cake_price int NOT NULL,
	amount_available int,
	image_name varchar(200),
	
	CONSTRAINT pk_cake_id PRIMARY KEY(cake_id),
	CONSTRAINT fk_cake_style FOREIGN KEY(cake_style) REFERENCES cake_style(cake_style_id),
	CONSTRAINT fk_cake_size FOREIGN KEY(cake_size) REFERENCES cake_size(cake_size_id),
	CONSTRAINT fk_cake_flavor FOREIGN KEY(cake_flavor) REFERENCES cake_flavor(cake_flavor_id),
	CONSTRAINT fk_cake_filling FOREIGN KEY(cake_filling) REFERENCES cake_filling(cake_filling_id),
	CONSTRAINT fk_cake_frosting FOREIGN KEY (cake_frosting) REFERENCES cake_frosting(cake_frosting_id),
	CONSTRAINT fk_cake_price FOREIGN KEY(cake_price) REFERENCES cake_price(cake_price_id)
);

CREATE TABLE cart_item (
	cart_item_id SERIAL,
	user_id int,
	status_id int,
	pickup_date Date, 
	pickup_time TIME, 
	CONSTRAINT pk_cart_item_id PRIMARY KEY(cart_item_id),
	CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users(user_id),
	CONSTRAINT fk_status_id FOREIGN KEY (status_id) REFERENCES cart_item_status(cart_item_status_id)
);

CREATE TABLE cart_item_cake (
    cart_item_cake_id SERIAL PRIMARY KEY,
    cart_item_id int NOT NULL,
    cake_id int NOT NULL,
    quantity int NOT NULL DEFAULT 1,
    CONSTRAINT fk_cart_item FOREIGN KEY(cart_item_id) REFERENCES cart_item(cart_item_id),
    CONSTRAINT fk_cake FOREIGN KEY(cake_id) REFERENCES cake(cake_id)
);

-- INSERTING DATA AS PER YOUR EXAMPLES --

-- Prices for Layered Cakes
INSERT INTO cake_price (cake_style_id, cake_size_id, has_writing, base_price, writing_fee) VALUES 
(1, 1, false, 39.00, 0.00),  -- Layered, Small 6'
(1, 2, false, 46.00, 0.00),  -- Layered, Medium 8'
(1, 3, false, 67.00, 0.00),  -- Layered, Large 12'
(1, 1, true, 39.00, 5.00),  -- Layered, Small 6'
(1, 2, true, 46.00, 5.00),  -- Layered, Medium 8'
(1, 3, true, 67.00, 5.00);  -- Layered, Large 12'

-- Prices for Sheet Cakes
INSERT INTO cake_price (cake_style_id, cake_size_id, has_writing, base_price, writing_fee) VALUES 
(2, 1, false, 39.00, 0.00),  -- Sheet, Small 1/4 
(2, 2, false, 57.00, 0.00),  -- Sheet, Medium 1/2
(2, 3, false, 107.00, 0.00), -- Sheet, Large Whole Sheet
(2, 1, true, 39.00, 5.00),  -- Sheet, Small 1/4 
(2, 2, true, 57.00, 5.00),  -- Sheet, Medium 1/2
(2, 3, true, 107.00, 5.00); -- Sheet, Large Whole Sheet

-- Prices for Cupcakes (No writing fee)
INSERT INTO cake_price (cake_style_id, cake_size_id, has_writing, base_price, writing_fee) VALUES 
(3, 1, false, 18.00, 0.00),  -- Cupcakes, Small (6 cupcakes)
(3, 2, false, 36.00, 0.00),  -- Cupcakes, Medium (12 cupcakes)
(3, 3, false, 72.00, 0.00); -- Cupcakes, Large (24 cupcakes)

-- INSERTING CAKES --
INSERT INTO cake(cake_Name, cake_style, cake_size, cake_flavor, cake_filling, cake_frosting, cake_type, has_writing, custom_text, amount_available, cake_price, image_name) 
VALUES('Chocolate Ganache Cake', 1, 2, 3, NULL, 2, 'Standard', true,'', 2, 2,'/src/assets/Cake_Images/standard_chocolate_ganache_cake.png'),
('Chocolate Ganache Cake', 1, 2, 3, NULL, 2, 'Standard', false,'', 2, 2,'/src/assets/Cake_Images/standard_chocolate_ganache_cake.png'),
('Vanilla Buttercream Cake', 1, 2, 2, NULL, 1, 'Standard', true,'', 3, 2,'/src/assets/Cake_Images/standard_vanilla_cake.png'),
('Funfetti cake', 1, 1, 6, NULL, 1, 'Standard', true,'', 1, 1,'/src/assets/Cake_Images/standard_funfetti_cake.jpg'),
('Red Velvet Cake', 3, 2, 3, NULL, 1, 'Standard', true,'', 2, 8,'/src/assets/Cake_Images/standard_red_velvet_cake.png'),
('Pound Cake', 2, 3, 1, NULL, 1, 'Standard', true, '', 2, 6,'/src/assets/Cake_Images/standard_pound_cake.png'),
('Butterscotch Cake', 2, 3, 1, 7, 1, 'Standard', true, '', 2, 6, '/src/assets/Cake_Images/standard_butterscotch_cake.jpg');

-- INSERTING STATUSES --
INSERT INTO cart_item_status(status_name)
VALUES ('Pending'),
('Rejected by bakery'),
('Cancelled by customer'),
('Ready for pick-up'),
('Order Completed');

-- INSERTING USERS --
INSERT INTO users (username, yourname, email, phone_number, password_hash, role)
VALUES ('user', 'John Smith', 'user@example.com', '123-456-7890', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
('admin', 'Admin Smith', 'admin@example.com', '098-765-4321', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN' );

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO final_capstone_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_owner;

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser;

COMMIT TRANSACTION;
