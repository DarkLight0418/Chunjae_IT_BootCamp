DROP TABLE IF EXISTS BOARD;

CREATE TABLE BOARD (
   SEQ INT AUTO_INCREMENT PRIMARY KEY, 
   WRITER VARCHAR(15), 
   EMAIL VARCHAR(20),
   SUBJECT VARCHAR(20), 
   CONTENT VARCHAR(20), 
   RDATE DATETIME DEFAULT CURRENT_TIMESTAMP
); 

INSERT INTO BOARD (WRITER, EMAIL, SUBJECT, CONTENT)
VALUES ('고창우', 'a@hanmail.net','제목1', '내용1'),
       ('박수빈', 'b@hanmail.net','제목2', '내용2'),
       ('양진석', 'c@hanmail.net','제목3', '내용3'),
       ('김한재', 'd@hanmail.net','제목4', '내용4'),
       ('김효상', 'e@hanmail.net','제목5', '내용5'),
       ('오연희', 'f@hanmail.net','제목6', '내용6'),
       ('정현영', 'g@hanmail.net','제목7', '내용7'),
       ('김승완', 'h@hanmail.net','제목8', '내용8'), 
       ('최윤서', 'i@hanmail.net','제목9', '내용9'),
       ('서준서', 'j@hanmail.net','제목10', '내용10'),
       ('이민영', 'k@hanmail.net','제목11', '내용11'),
       ('박현지', 'l@hanmail.net','제목12', '내용12'),
       ('이금영', 'm@hanmail.net','제목13', '내용13');


SELECT * FROM BOARD ORDER BY SEQ DESC;


--ALTER TABLE BOARD
--    ADD COLUMN FNAME VARCHAR(30) DEFAULT '' AFTER CONTENT,
--    ADD COLUMN OFNAME VARCHAR(30) DEFAULT '' AFTER FNAME;