BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, cakeStyle, cakeSize, cakeFlavor, cakeFilling, cakeFrosting, cake, cart;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	yourName varchar(200) NOT NULL,
	email varchar(50) NOT NULL,
	phoneNumber varchar(12) NOT NULL,  
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE cakeStyle (
	cakeStyleId SERIAL,
	styleName varchar(50) NOT NULL UNIQUE,
	CONSTRAINT pk_cakeStyle PRIMARY KEY(cakeStyleId)
);

CREATE TABLE cakeSize (
	cakeSizeId SERIAL,
	sizeName varchar(50) NOT NULL UNIQUE,
	CONSTRAINT pk_cakeSizeId PRIMARY KEY(cakeSizeId)
);

CREATE TABLE cakeFlavor (
	cakeFlavorId SERIAL,
	flavorName varchar(50) NOT NULL UNIQUE,
	CONSTRAINT pk_cakeFlavorId PRIMARY KEY(cakeFlavorId)
);

CREATE TABLE cakeFilling (
	cakeFillingId SERIAL,
	fillingName varchar(50) NOT NULL UNIQUE,
	CONSTRAINT pk_cakeFillingId PRIMARY KEY(cakeFillingId)
);

CREATE TABLE cakeFrosting (
	cakeFrostingId SERIAL,
	frostingName varchar(50) NOT NULL UNIQUE,
	CONSTRAINT pk_cakeFrostingId PRIMARY KEY(cakeFrostingId)
);

CREATE TABLE cake (
	cakeId SERIAL,
	cakeName varchar(50) NOT NULL,
	cakeStyle int,
	cakeSize int,
	cakeFlavor int,
	cakeFilling int,
	cakeFrosting int,
	cakeType varchar(50),
	hasWriting boolean, 
	customText varchar(100),
	amountAvailable int,
	price numeric,
	
	CONSTRAINT pk_cakeId PRIMARY KEY(cakeId),
	CONSTRAINT fk_cakeStyle FOREIGN KEY(cakeStyle) REFERENCES cakeStyle(cakeStyleId),
	CONSTRAINT fk_cakeSize FOREIGN KEY(cakeSize) REFERENCES cakeSize(cakeSizeId),
	CONSTRAINT fk_cakeFlavor FOREIGN KEY(cakeFlavor) REFERENCES cakeFlavor(cakeFlavorId),
	CONSTRAINT fk_cakeFilling FOREIGN KEY(cakeFilling) REFERENCES cakeFilling(cakeFillingId),
	CONSTRAINT fk_cakeFrosting FOREIGN KEY (cakeFrosting) REFERENCES cakeFrosting(cakeFrostingId)
);

CREATE TABLE cart (
	cartId SERIAL,
	userId int,
	cakeId int,
	pickupDate Date NOT NULL, 
	pickupTime TIME NOT NULL, 
	
	CONSTRAINT pk_cartId PRIMARY KEY(cartId),
	CONSTRAINT fk_userId FOREIGN KEY(userId) REFERENCES users(user_id),
	CONSTRAINT fk_cakeId FOREIGN KEY (cakeId) REFERENCES cake(cakeId)
);

COMMIT TRANSACTION;
