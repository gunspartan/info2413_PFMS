-- ------------------- CREATE USER ---------------------
CREATE USER 'info2413'@'localhost' IDENTIFIED BY 'PFMS_ADMIN';
GRANT ALL PRIVILEGES ON info2413.* TO 'info2413'@'localhost';
FLUSH PRIVILEGES;