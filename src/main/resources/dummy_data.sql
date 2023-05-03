use gavoyage;
show tables;
select * from user;
select userIdx, email, userPassword from user where email="ssafy@ssafy.com";
alter table user change `password` userPassword varchar(200);