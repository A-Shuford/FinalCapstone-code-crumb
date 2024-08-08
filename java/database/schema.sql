BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, cakeStyle, cakeSize, cakeFlavor, cakeFilling, cakeFrosting, cart_item_status, cake, cart_item;
DROP TABLE IF EXISTS users, cake_style, cake_size, cake_flavor, cake_filling, cake_frosting, cart_item_status, cake, cart_item;

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
	status_name varchar(20), 
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
	pickup_date Date NOT NULL, 
	pickup_time TIME NOT NULL, 
	
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
VALUES('Chocolate Ganache Cake', 1, 2, 3, NULL, 2, 'Standard', true,'', 2, 35.00,'/src/assets/Cake_Images/Chocolate_cake_ganache_2.jpg'),
('Vanilla Buttercream Cake', 1, 2, 2, NULL, 1, 'Standard', true,'', 3, 5.00,'/src/assets/Cake_Images/Vanilla_Buttercream.jpg'),
('Funfetti cake', 1, 1, 6, NULL, 1, 'Standard', true,'', 1, 31.40,'/src/assets/Cake_Images/Birthday_Confetti_Cake.jpg'),
('Red Velvet Cake', 3, 2, 3, NULL, 1, 'Standard', true,'', 2, 20.50,'/src/assets/Cake_Images/Red_Velvet_Cake.webp'),
('Pound Cake', 2, 3, 1, NULL, 1, 'Standard', true, '', 2, 45.00, '/src/assets/Cake_Images/noimage.jpg'),
('Normal Cake', 2, 3, 1, 1, NULL, 'Standard', true, '', 2, 45.00, '/src/assets/Cake_Images/noimage.jpg');; 

COMMIT TRANSACTION;
