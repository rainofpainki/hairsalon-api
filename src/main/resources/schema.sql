-- 헤어샵
CREATE TABLE SHOP
(
  SHOP_ID NUMBER NOT NULL
, SHOP_NAME VARCHAR2(50) NOT NULL
, SHOP_THUMB_URL VARCHAR2(200)
, SHOP_ADDRESS VARCHAR2(200)
, SHOP_START_HOUR_OF_WEEKDAY VARCHAR2(5) NOT NULL
, SHOP_END_HOUR_OF_WEEKDAY VARCHAR2(5) NOT NULL
, SHOP_START_HOUR_OF_WEEKEND VARCHAR2(5) NOT NULL
, SHOP_END_HOUR_OF_WEEKEND VARCHAR2(5) NOT NULL
, SHOP_TEL_NUMBER VARCHAR2(20)
, SHOP_MESSAGE VARCHAR2(500)
, CONSTRAINT SHOP_PK PRIMARY KEY
  (
    SHOP_ID
  )
  ENABLE
);
COMMENT ON COLUMN SHOP.SHOP_ID IS '헤어샵 ID';
COMMENT ON COLUMN SHOP.SHOP_NAME IS '헤어샵 이름';
COMMENT ON COLUMN SHOP.SHOP_THUMB_URL IS '헤어샵 썸네일 URL';
COMMENT ON COLUMN SHOP.SHOP_ADDRESS IS '헤어샵 주소';
COMMENT ON COLUMN SHOP.SHOP_START_HOUR_OF_WEEKDAY IS '평일 영업시작시간';
COMMENT ON COLUMN SHOP.SHOP_END_HOUR_OF_WEEKDAY IS '평일 영업종료시간';
COMMENT ON COLUMN SHOP.SHOP_START_HOUR_OF_WEEKEND IS '주말 영업시작시간';
COMMENT ON COLUMN SHOP.SHOP_END_HOUR_OF_WEEKEND IS '주말 영업종료시간';
COMMENT ON COLUMN SHOP.SHOP_TEL_NUMBER IS '헤어샵 전화번호';
COMMENT ON COLUMN SHOP.SHOP_MESSAGE IS '헤어샵 소개 메시지';

-- 시술
CREATE TABLE "PROCEDURE"
(
  PROCEDURE_ID NUMBER NOT NULL
, SHOP_ID NUMBER NOT NULL
, PROCEDURE_NAME VARCHAR2(50) NOT NULL
, PROCEDURE_PRICE NUMBER NOT NULL
, PROCEDURE_HOURS NUMBER
, CONSTRAINT PROCEDURE_PK PRIMARY KEY
  (
    PROCEDURE_ID
  )
  ENABLE
);

ALTER TABLE "PROCEDURE"
ADD CONSTRAINT PROCEDURE_SHOP_FK FOREIGN KEY
(
  SHOP_ID
)
REFERENCES SHOP
(
  SHOP_ID
)
ON DELETE CASCADE ENABLE;

COMMENT ON COLUMN "PROCEDURE".PROCEDURE_ID IS '시술 ID';
COMMENT ON COLUMN "PROCEDURE".SHOP_ID IS '헤어샵 ID';
COMMENT ON COLUMN "PROCEDURE".PROCEDURE_NAME IS '시술 이름';
COMMENT ON COLUMN "PROCEDURE".PROCEDURE_PRICE IS '시술 가격';
COMMENT ON COLUMN "PROCEDURE".PROCEDURE_HOURS IS '시술 소요시간';

-- 스타일리스트
CREATE TABLE STYLIST
(
  STYLIST_ID NUMBER NOT NULL
, SHOP_ID NUMBER NOT NULL
, STYLIST_NAME VARCHAR2(20) NOT NULL
, STYLIST_MESSAGE VARCHAR2(500)
, STYLIST_THUMB_URL VARCHAR2(200)
, CONSTRAINT STYLIST_PK PRIMARY KEY
  (
    STYLIST_ID
  )
  ENABLE
);

ALTER TABLE STYLIST
ADD CONSTRAINT STYLIST_SHOP_FK FOREIGN KEY
(
  SHOP_ID
)
REFERENCES SHOP
(
  SHOP_ID
)
ON DELETE CASCADE ENABLE;

COMMENT ON COLUMN STYLIST.STYLIST_ID IS '스타일리스트 ID';
COMMENT ON COLUMN STYLIST.SHOP_ID IS '헤어샵 ID';
COMMENT ON COLUMN STYLIST.STYLIST_NAME IS '스타일리스트 이름';
COMMENT ON COLUMN STYLIST.STYLIST_MESSAGE IS '스타일리스트 소개 메시지';
COMMENT ON COLUMN STYLIST.STYLIST_THUMB_URL IS '스타일리스트 썸네일 URL';

-- 예약
CREATE TABLE RESERVATION
(
  RESERVATION_ID NUMBER NOT NULL
, RESERVATION_DATETIME DATE NOT NULL
, RESERVATION_HOURS NUMBER NOT NULL
, USER_ID NUMBER NOT NULL
, SHOP_ID NUMBER NOT NULL
, STYLIST_ID NUMBER NOT NULL
, PROCEDURE_ID NUMBER NOT NULL
, PRICE NUMBER
, CONSTRAINT RESERVATION_PK PRIMARY KEY
  (
    RESERVATION_ID
  )
  ENABLE
);

ALTER TABLE RESERVATION
ADD CONSTRAINT RESERVATION_PROCEDURE_FK FOREIGN KEY
(
  PROCEDURE_ID
)
REFERENCES "PROCEDURE"
(
  PROCEDURE_ID
)
ON DELETE SET NULL ENABLE;

ALTER TABLE RESERVATION
ADD CONSTRAINT RESERVATION_SHOP_FK FOREIGN KEY
(
  SHOP_ID
)
REFERENCES SHOP
(
  SHOP_ID
)
ON DELETE SET NULL ENABLE;

ALTER TABLE RESERVATION
ADD CONSTRAINT RESERVATION_STYLIST_FK FOREIGN KEY
(
  STYLIST_ID
)
REFERENCES STYLIST
(
  STYLIST_ID
)
ON DELETE SET NULL ENABLE;

COMMENT ON COLUMN RESERVATION.RESERVATION_ID IS '예약 ID';
COMMENT ON COLUMN RESERVATION.RESERVATION_DATETIME IS '예약 일시';
COMMENT ON COLUMN RESERVATION.RESERVATION_HOURS IS '시술 소요시간';
COMMENT ON COLUMN RESERVATION.USER_ID IS '회원 ID';
COMMENT ON COLUMN RESERVATION.SHOP_ID IS '헤어샵 ID';
COMMENT ON COLUMN RESERVATION.STYLIST_ID IS '스타일리스트 ID';
COMMENT ON COLUMN RESERVATION.PROCEDURE_ID IS '시술 ID';
COMMENT ON COLUMN RESERVATION.PRICE IS '시술 금액';


-- 시퀀스
CREATE SEQUENCE SHOP_SEQ INCREMENT BY 1;
CREATE SEQUENCE PROCEDURE_SEQ INCREMENT BY 1;
CREATE SEQUENCE STYLIST_SEQ INCREMENT BY 1;
CREATE SEQUENCE RESERVATION_SEQ INCREMENT BY 1;


