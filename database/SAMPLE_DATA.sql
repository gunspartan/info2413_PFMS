-- ---------------------------- SAMPLE DATA ----------------------------------------
INSERT INTO UserInfo (Username, UserPwd, Email)
VALUES 
	('john111', '1234', 'john@yahoo.com'),
	('mary888', '4567', 'mary@gmail.com');
--  	
INSERT INTO Category (CategoryName)
VALUES
('Fruit'),
('Dessert'),
('Drinks');

INSERT INTO GroceryInventory (UserId, ShopDate)
VALUES
	(1, '2021-12-31'),
 	(2, '2021-11-09'),
    (1, '2021-11-11');
	
INSERT INTO GroceryItem (GroceryInventoryId, Category, FoodName, Price, ExpiryDate, Qty, Img)
VALUES
	(1, 'Dessert', 'Ice Cream', 10.99, '2021-12-31', 10, '../img/icecream.jpg'),
 	(2, 'Drinks', 'Iced Tea', 5.99, '2021-12-31', 5,'../img/icedtea.jpg'),
    (2, 'Drinks', 'Coca-Cola', 4.99, '2021-12-31', 15,'../img/cocacola.jpg'),
    (1, 'Fruit', 'Apple', 2.99, '2021-11-30', '3', '../img/apple.jpg');