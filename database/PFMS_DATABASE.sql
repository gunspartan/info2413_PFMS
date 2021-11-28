CREATE DATABASE info2413;

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
  FOREIGN KEY (UserId) REFERENCES UserInfo(UserId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE GroceryItem (
  GroceryItemId int NOT NULL AUTO_INCREMENT UNIQUE,
  GroceryInventoryId int,
  Category varchar(20),
  FoodName varchar(50) NOT NULL,
  Price float(2) NOT NULL,
  ExpiryDate date NOT NULL,
  Qty int NOT NULL,
  QtyConsumed int DEFAULT 0,
  Expired bool DEFAULT false,
  Img varchar(255),
  PRIMARY KEY (GroceryItemId),
  FOREIGN KEY (GroceryInventoryId) REFERENCES GroceryInventory(GroceryInventoryId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (Category) REFERENCES Category(CategoryName) ON UPDATE CASCADE ON DELETE RESTRICT
);

USE info2413;