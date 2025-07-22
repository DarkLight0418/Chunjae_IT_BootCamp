--mysql -u root -p
--Enter password: 1234

--drop user 'video'@'localhost';
create user 'video'@'localhost' identified by 'java';
--drop database modeling_schema;
create database modeling_schema;
show databases;

grant all privileges on modeling_schema.* to 'video'@'localhost';
flush privileges;
