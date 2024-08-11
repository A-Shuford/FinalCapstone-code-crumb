BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, cakeStyle, cakeSize, cakeFlavor, cakeFilling, cakeFrosting, cart_item_status, cake, cart_item;
DROP TABLE IF EXISTS users, cake_style, cake_size, cake_flavor, cake_filling, cake_frosting, cart_item_status, cake, cart_item, cart_item_status;

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
	amount_available int,
	price decimal(8,2),
	image_name varchar(200),
	
	CONSTRAINT pk_cake_id PRIMARY KEY(cake_id),
	CONSTRAINT fk_cake_style FOREIGN KEY(cake_style) REFERENCES cake_style(cake_style_id),
	CONSTRAINT fk_cake_size FOREIGN KEY(cake_size) REFERENCES cake_size(cake_size_id),
	CONSTRAINT fk_cake_flavor FOREIGN KEY(cake_flavor) REFERENCES cake_flavor(cake_flavor_id),
	CONSTRAINT fk_cake_filling FOREIGN KEY(cake_filling) REFERENCES cake_filling(cake_filling_id),
	CONSTRAINT fk_cake_frosting FOREIGN KEY (cake_frosting) REFERENCES cake_frosting(cake_frosting_id)
);

CREATE TABLE cart_item (
	cart_item_id SERIAL,
	user_id int,
	cake_id int,
	status_id int,
	pickup_date Date, 
	pickup_time TIME, 
	--pickupdate and picktime talk to team
	CONSTRAINT pk_cart_item_id PRIMARY KEY(cart_item_id),
	CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users(user_id),
	CONSTRAINT fk_cake_id FOREIGN KEY (cake_id) REFERENCES cake(cake_id),
	CONSTRAINT fk_status_id FOREIGN KEY (status_id) REFERENCES cart_item_status(cart_item_status_id)
);
----INSERTING FROSTING TYPES---------
INSERT INTO cake_frosting(frosting_name) VALUES ('Vanilla');
INSERT INTO cake_frosting(frosting_name) VALUES ('Chocolate Ganache');
INSERT INTO cake_frosting(frosting_name) VALUES ('Coffee');
INSERT INTO cake_frosting(frosting_name) VALUES ('Strawberry');
INSERT INTO cake_frosting(frosting_name) VALUES ('Mint');
INSERT INTO cake_frosting(frosting_name) VALUES ('Cream cheese');

------INSERTING FILLING TYPE----------
INSERT INTO cake_filling(filling_name) VALUES ('Apple Jelly');
INSERT INTO cake_filling(filling_name) VALUES ('Raspberry Jelly');
INSERT INTO cake_filling(filling_name) VALUES ('Strawberry Jelly');
INSERT INTO cake_filling(filling_name) VALUES ('Vanilla Custard');
INSERT INTO cake_filling(filling_name) VALUES ('Chocolae Custard');
INSERT INTO cake_filling(filling_name) VALUES ('Cookie Dough');
INSERT INTO cake_filling(filling_name) VALUES ('Caramel');
INSERT INTO cake_filling(filling_name) VALUES ('Peanut Butter');

-------INSERTING FLAVOR---------
INSERT INTO cake_flavor(flavor_name) VALUES('Butterscotch');
INSERT INTO cake_flavor(flavor_name) VALUES('Vanilla');
INSERT INTO cake_flavor(flavor_name) VALUES('Chocolate');
INSERT INTO cake_flavor(flavor_name) VALUES('Lemon');
INSERT INTO cake_flavor(flavor_name) VALUES('Cookie & Cream');
INSERT INTO cake_flavor(flavor_name) VALUES('Funfetti');
INSERT INTO cake_flavor(flavor_name) VALUES('Pumpkin');
INSERT INTO cake_flavor(flavor_name) VALUES('Key Lime');

--------INSTERING SIZE---------
INSERT INTO cake_size(size_name) VALUES('Small');
INSERT INTO cake_size(size_name) VALUES('Medium');
INSERT INTO cake_size(size_name) VALUES('Large');

------INSERTING Style---------
INSERT INTO cake_style(style_name) VALUES('Layered');
INSERT INTO cake_style(style_name) VALUES('Sheet');
INSERT INTO cake_style(style_name) VALUES('Cupcakes');


--
INSERT INTO cake(cake_Name, cake_style, cake_size, cake_flavor, cake_filling, cake_frosting, cake_type, has_writing, custom_text, amount_available, price, image_name) 
VALUES('Chocolate Ganache Cake', 1, 2, 3, NULL, 2, 'Standard', true,'', 2, 45.99,'/src/assets/Cake_Images/standard_chocolate_ganache_cake.png'),
('Vanilla Buttercream Cake', 1, 2, 2, NULL, 1, 'Standard', true,'', 3, 25.00,'/src/assets/Cake_Images/standard_vanilla_cake.png'),
('Funfetti cake', 1, 1, 6, NULL, 1, 'Standard', true,'', 1, 31.40,'/src/assets/Cake_Images/standard_funfetti_cake.jpg'),
('Red Velvet Cake', 3, 2, 3, NULL, 1, 'Standard', true,'', 2, 33.00,'/src/assets/Cake_Images/standard_red_velvet_cake.png'),
('Pound Cake', 2, 3, 1, NULL, 1, 'Standard', true, '', 2, 45.00, '/src/assets/Cake_Images/standard_pound_cake.png'),
('Butterscotch Cake', 2, 3, 1, 7, 1, 'Standard', true, '', 2, 45.00, '/src/assets/Cake_Images/standard_butterscotch_cake.jpg');

INSERT INTO cart_item_status(status_name)
VALUES ('Pending'),
('Rejected by bakery'),
('Cancelled by customer'),
('Ready for pick-up'),
('Order Completed');

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
