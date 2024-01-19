#!/bin/bash

# Install MySQL server
sudo apt-get install mysql-common

# Start MySQL service
sudo service mysql start

# Log in to MySQL as root and execute SQL scripts
sudo mysql -u root <<'END_SQL'
-- Create a user
CREATE USER IF NOT EXISTS 'BloodBank'@'localhost' IDENTIFIED BY 'BloodBank';
GRANT ALL PRIVILEGES ON *.* TO 'BloodBank'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

-- Create the database
CREATE DATABASE IF NOT EXISTS BloodBank;
USE BloodBank;

-- Create the staff table
CREATE TABLE IF NOT EXISTS staff (
    staff_id INT PRIMARY KEY,
    staff_name VARCHAR(255) NOT NULL,
    position VARCHAR(255),
    contact_number VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME
);

-- Insert an initial admin into the staff table
INSERT INTO staff (staff_id, staff_name, position, contact_number, email, password, enabled, created_at, updated_at)
VALUES (3,'Admin','Manager','0000000000','admin@gmail.com','$2a$12$Q8b8lPkDhetMmDS0DShOI.IL6v.GtJztrUlqamxTagMH5c5Kg37Z2', true, NOW(), null);
END_SQL

# Restart MySQL service to apply changes
sudo service mysql restart

# Run database migrations or initialize the schema
# Adjust this based on your project structure and build tools
mvn clean install

# Start your application
mvn spring-boot:run