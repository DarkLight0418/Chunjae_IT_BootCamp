CREATE DATABASE got_it_schema;
CREATE USER 'got'@'localhost' IDENTIFIED BY 'got12';
GRANT ALL PRIVILEGES ON got_it_schema.* TO 'got'@'localhost';
GRANT EVENT ON got_it_schema.* TO 'got'@'localhost';
FLUSH PRIVILEGES;