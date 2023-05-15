use gavoyage;
insert into User (email, nickname, `password`, phoneNumber, createdAt)
values     ('ssafy@ssafy.com', '김싸피', '1234', '010-0000-0000',  now()),
        ('admin@ssafy.com', '관리자', '1234', '010-1234-5678', now());

select * from User;