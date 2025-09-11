DROP TABLE IF EXISTS BOARD2;

CREATE TABLE BOARD2 (
   SEQ INT AUTO_INCREMENT PRIMARY KEY, 
   WRITER VARCHAR(15), 
   EMAIL VARCHAR(20),
   SUBJECT VARCHAR(20), 
   CONTENT VARCHAR(20), 
   RDATE DATETIME DEFAULT CURRENT_TIMESTAMP
); 

INSERT INTO BOARD2 (WRITER, EMAIL, SUBJECT, CONTENT)
VALUES ('고창우', 'a@hanmail.net','제목1', '내용1'),
       ('박수빈', 'b@hanmail.net','제목2', '내용2'),
       ('양진석', 'c@hanmail.net','제목3', '내용3'),
       ('김한재', 'siuuuuu@hanmail.net','호날두', 'si~'),
       ('김효상', 'e@hanmail.net','제목5', '내용5'),
       ('오연희', 'f@hanmail.net','제목6', '내용6'),
       ('정현영', 'g@hanmail.net','제목7', '내용7'),
       ('김승완', 'h@hanmail.net','제목8', '내용8'),
       ('최윤서', 'i@hanmail.net','제목9', '내용9'),
       ('서준서', 'j@hanmail.net','제목10', '내용10'),
       ('이민영', 'k@hanmail.net','제목11', '내용11'),
       ('박현지', 'l@hanmail.net','제목12', '내용12'),
       ('이금영', 'm@hanmail.net','제목13', '내용13');


SELECT * FROM BOARD2 ORDER BY SEQ DESC;

update BOARD set WRITER="십오에이", EMAIL="15aaa@gmail.com", SUBJECT="제목15", CONTENT="내용15", RDATE=NOW() where SEQ=15