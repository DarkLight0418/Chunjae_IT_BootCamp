CREATE TABLE 회원카드 (
  id INT PRIMARY KEY AUTO_INCREMENT,
  이용_기간 ENUM('3개월', '6개월', '12개월'),
  시작일 DATE,
  종료일 DATE,
  라커룸_여부 BOOLEAN,
  PT_여부 BOOLEAN,
  이름 VARCHAR(15),
  전화번호 VARCHAR(100),
  우편번호 CHAR(6),
  성별 CHAR(2),
  나이 INT,
  email VARCHAR(50)
);

CREATE TABLE 회원결제정산 (
  id INT PRIMARY KEY AUTO_INCREMENT,
  회원ID INT,
  결제_내역 TEXT,
  결제_방식 ENUM('카드', '현금', '계좌이체'),
  결제_일자 DATE,
  회원권 BOOLEAN,
  운동복 BOOLEAN,
  PT BOOLEAN,
  라커룸 BOOLEAN,
  FOREIGN KEY (회원ID) REFERENCES 회원카드(id) ON DELETE CASCADE
);

CREATE TABLE 출입관리 (
  id INT PRIMARY KEY AUTO_INCREMENT,
  회원ID INT,
  방문일시 DATETIME,
  출입기록 TEXT,
  FOREIGN KEY (회원ID) REFERENCES 회원카드(id) ON DELETE CASCADE
);

CREATE TABLE 트레이너 (
  트레이너_ID INT PRIMARY KEY AUTO_INCREMENT,
  이름 VARCHAR(20),
  성별 CHAR(1),
  담당_회원_리스트 TEXT,
  근무_시간표 TEXT,
  자격증_및_경력 TEXT,
  월급 DECIMAL(10,2)
);

CREATE TABLE 소모품 (
  품목명 VARCHAR(20) PRIMARY KEY,
  남은갯수 INT DEFAULT 0,
  주문예정일 DATE,
  가격 DECIMAL(10,2)
);

CREATE TABLE 운동기구 (
  기구명 VARCHAR(30) PRIMARY KEY,
  사용_기간 VARCHAR(30),
  상태 ENUM('정상', '고장', '수리중'),
  메이커 VARCHAR(30),
  갯수 INT
);

CREATE TABLE 유지비 (
  전기세 DECIMAL(10,2),
  수도세 DECIMAL(10,2),
  PT_트레이너_월급 DECIMAL(10,2),
  월세 DECIMAL(10,2),
  소모품비 DECIMAL(10,2)
);


CREATE TABLE PT_수업_정보 (
  트레이너_ID INT PRIMARY KEY AUTO_INCREMENT,
  수업시간 INT(2),
  날짜 DATE,
  요일 
  id INT PRIMARY KEY AUTO_INCREMENT,
)