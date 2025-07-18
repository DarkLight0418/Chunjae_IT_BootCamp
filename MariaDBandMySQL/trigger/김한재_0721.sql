-- 상당히 숙달되려면 복습 많이 해야 할 것 같습니다... 쉽지 않네요 ㅜㅜ)

drop table if exists TR_MEMBER;
drop table if exists TR_LOGIN;
drop table if exists TR_MYHOME;

-- 회원

create table TR_MEMBER (
	ID varchar(10) primary key,
	PWD varchar(10) not null,
	NAME varchar(20) not null,
	EMAIL varchar(20) unique,
	RDATE date
);

-- 로그인

create table TR_LOGIN (
	ID varchar(10),
	PWD varchar(10),
	primary key (ID),
	foreign key (ID) references TR_MEMBER(ID) on delete cascade
);

-- 홈페이지

create table TR_MYHOME (
	SEQ int primary key auto_increment,
	ID varchar(10) not null,
	HNAME varchar(20) not null,
	TOTCOUNT int default 0,
	HMSG varchar(15),
	CDATE date,
	foreign key (ID) references TR_MEMBER(ID) on delete cascade
);

show tables;

delimiter $$
  drop trigger if exists TRI_MEMBER;

  create trigger TRI_MEMBER
  after insert on TR_LOGIN
  for each row
  begin
    insert into TR_MYHOME(ID, HNAME) 
    values (new.ID, new.ID);
  end$$

delimiter ;

insert into TR_MEMBER(ID, PWD, NAME, EMAIL, RDATE)
values('kim', '0000', '김철수', 'kim@ggg.com', now());
insert into TR_LOGIN values('kim', '0000');
select * from TR_LOGIN;
select SEQ, ID, HNAME from TR_MYHOME;

delimiter $$
  drop trigger if exists TRI_PWD;

  create trigger TRI_PWD
  after update on TR_MEMBER
  for each row
  begin
    update TR_LOGIN set PWD=new.PWD
    where ID=new.ID;
  end$$

delimiter ;

update TR_MEMBER set PWD='2000' where ID='kim';