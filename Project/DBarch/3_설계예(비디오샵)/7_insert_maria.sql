insert into JANGR values(10, '액션');
insert into JANGR values(20, '호러');
insert into JANGR values(30, '멜로');

insert into GRADE values(10, '유아');
insert into GRADE values(12, '12세');
insert into GRADE values(15, '15세');
insert into GRADE values(18, '18세');
insert into GRADE values(20, '성인');

insert into MOVIE values('m0001','트로이', 'Warner Bros', 'USA', '볼프강페터젠', '브래드피트', '2004-05-21', 10, 15);
insert into MOVIE values('m0002', '러셀 크로우', 'Dream Works', 'USA', '론하워드', '러셀크로우', '2002-02-22', 10, 18);
insert into MOVIE values('m0003', '터미네이터', 'Warner Bros', 'USA', '제임스카메론', '아놀드', '1984-12-22', 10, 12);
insert into MOVIE values('m0004', '타이타닉', 'Warner Bros', 'USA', '제임스카메론', '디카프리오,', '1998-02-20', 30, 12);

insert into CHAINSTORE values('c01', '신촌지점', '김인희', '01012341234', '12345', '서울시 신촌', '2019-05-27');
insert into CHAINSTORE values('c02', '홍대지점', '김나리', '01012344321', '54321', '서울시 홍대입구', '2019-05-28');

insert into VIDEO values('v0001', 'c01', '2013-02-22', 15000, '0', 'm0001');
insert into VIDEO values('v0002', 'c02', '2005-05-18', 13000, '1', 'm0001');
insert into VIDEO values('v0003', 'c01', '2005-07-12', 11000, '0', 'm0003');
insert into VIDEO values('v0004', 'c02', '2006-05-28', 17000, '1', 'm0004');

insert into MEMBER values('tiger@naver.com', 'c01', '국지예', '01023454567', '12345', '서울시 마포구', current_date, 1000);
insert into MEMBER values('tiger@naver.com', 'c02', '국지예', '01023454568', '12346', '서울시 영등포구', current_date, 2000);
insert into MEMBER values('lion@naver.com', 'c01', '최지수',  '01023454569', '41234', '경기도 군포시', current_date, 3000);
insert into MEMBER values('rabbit@naver.com', 'c02', '오희영','01023454560', '71234', '제주도 서귀포시', current_date, 5000);
insert into MEMBER values('dog@naver.com', 'c02', '서혜미',   '01023454561', '91234', '대전시 서구 둔산동', current_date, 4000);

insert into RENT values(NULL,  '2006-01-01', 1000, '2006-01-03', '0', 'v0001', 'c01', 'tiger@naver.com');
insert into RENT values(NULL,  '2006-02-01', 2000, '2006-02-03', '1', 'v0002', 'c02', 'tiger@naver.com');
insert into RENT values(NULL,  '2006-04-01', 4000, '2006-04-03', '1', 'v0002', 'c02', 'rabbit@naver.com');
insert into RENT values(NULL,  '2006-03-01', 3000, '2006-03-03', '0', 'v0003', 'c01', 'lion@naver.com');
insert into RENT values(NULL,  '2006-05-01', 5000, '2006-05-03', '0', 'v0004', 'c02', 'dog@naver.com');

insert into LATEFEE values(2, 500, '2006-01-05');
insert into LATEFEE values(3, 700, '2006-02-07');

insert into AMOUNT values('2016-01-05', 220000, 100);
insert into AMOUNT values('2016-01-06', 350000, 150);

insert into RENTAMOUNT values('0', 100, 1000, '인기도 최하');
insert into RENTAMOUNT values('1', 200, 2000, '인기도 하');
insert into RENTAMOUNT values('2', 300, 3000, '인기도 중');
insert into RENTAMOUNT values('3', 400, 4000, '인기도 상');
insert into RENTAMOUNT values('4', 500, 5000, '인기도 최상');

commit;

select * from JANGR;
select * from GRADE;
select count(*) from MOVIE;
select * from CHAINSTORE;
select * from VIDEO;
select M_EMAIL, C_CODE, M_JOINDATE from MEMBER;
select * from RENT;
select * from LATEFEE;
select * from AMOUNT;
select * from RENTAMOUNT;
