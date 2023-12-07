#!/bin/bash

# Install MySQL server
sudo apt-get install mysql*

# Start MySQL service
sudo service mysql start

# Log in to MySQL as root and create a user and database
sudo mysql <<EOF
-- Check if the user already exists
SELECT user FROM mysql.user WHERE user='BloodBank' LIMIT 1;
-- If the user doesn't exist, create the user and database
CREATE USER IF NOT EXISTS 'BloodBank'@'localhost' IDENTIFIED BY 'BloodBank';
GRANT ALL PRIVILEGES ON *.* TO 'BloodBank'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;
CREATE DATABASE IF NOT EXISTS BloodBank;
EOF

# Restart MySQL service to apply changes
sudo service mysql restart
