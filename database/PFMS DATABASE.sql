 DROP TABLE UserInfo;
 DROP TABLE Category;
 DROP TABLE GroceryInventory;
 DROP TABLE GroceryItem;

CREATE TABLE UserInfo (
  UserId int NOT NULL AUTO_INCREMENT UNIQUE,
  Username varchar(20) NOT NULL UNIQUE,
  UserPwd varchar(20) NOT NULL,
  Email varchar(50) NOT NULL UNIQUE,
  Budget float(2) DEFAULT 0,
  PRIMARY KEY (UserId)
);

CREATE TABLE Category (
  CategoryId int NOT NULL AUTO_INCREMENT UNIQUE,
  CategoryName varchar(20) NOT NULL UNIQUE,
  TotalSpent float(2) DEFAULT 0,
  PRIMARY KEY (CategoryId)
);

CREATE TABLE GroceryInventory (
  GroceryInventoryId int NOT NULL AUTO_INCREMENT UNIQUE,
  UserId int NOT NULL,
  ShopDate DATE NOT NULL,
  TotalSpent float(2) DEFAULT 0,
  PRIMARY KEY (GroceryInventoryId),
  FOREIGN KEY (UserId) REFERENCES UserInfo(UserId)
);

CREATE TABLE GroceryItem (
  GroceryItemId int NOT NULL AUTO_INCREMENT UNIQUE,
  GroceryInventoryId int,
  Category varchar(20),
  FoodName varchar(50) NOT NULL,
  Price float(2) NOT NULL,
  ExpiryDate date NOT NULL,
  ShopDate date NOT NULL,
  Qty int NOT NULL,
  QtyConsumed int DEFAULT 0,
  Expired bool DEFAULT false,
  Img varchar(255),
  PRIMARY KEY (GroceryItemId),
  FOREIGN KEY (GroceryInventoryId) REFERENCES GroceryInventory(GroceryInventoryId),
  FOREIGN KEY (Category) REFERENCES Category(CategoryName)
);
-- ---------------------------- SAMPLE DATA ----------------------------------------
INSERT INTO UserInfo (Username, UserPwd, Email, Budget)
VALUES 
	('john111', 'abcd1234', 'john@yahoo.com', 500),
	('mary888', 'efgh4567', 'mary@gmail.com', 250);
 	
INSERT INTO Category (CategoryName)
VALUES
('Dessert'),
('Drinks');

INSERT INTO GroceryInventory (UserId, ShopDate)
VALUES
	(1, '2021-12-31'),
	(2, '2021-11-09');
	
INSERT INTO GroceryItem (GroceryInventoryId, Category, FoodName, Price, ExpiryDate, ShopDate, Qty, Img)
VALUES
	(1, 'Dessert', 'Ice Cream', 10.99, '2021-12-31', '2021-10-01, 1', 10, '../img/icecream.jpg'),
 	(2, 'Drinks', 'Iced Tea', 5.99, '2021-12-31', '2021-10-01, 5', 5,'../img/icedtea.jpg'),
    (2, 'Drinks', 'Coca-Cola', 4.99, '2021-12-31', '2021-10-01, 5', 15,'../img/cocacola.jpg');