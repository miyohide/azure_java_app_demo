# create databases
CREATE DATABASE IF NOT EXISTS `app_dev`;
CREATE DATABASE IF NOT EXISTS `app_test`;

GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
