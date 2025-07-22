--mysql -u root -p
--Enter password: 1234

--drop user 'video'@'localhost';
create user 'health'@'localhost' identified by 'java';
--drop database modeling_schema;
create database modeling_schema;
show databases;

grant all privileges on modeling_schema.* to 'health'@'localhost';
flush privileges;
