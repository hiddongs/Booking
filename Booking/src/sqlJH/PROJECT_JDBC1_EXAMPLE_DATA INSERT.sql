CREATE SEQUENCE ACCOMMODATION_SEQ START WITH 100;

-- 1. 서울 강남구 테헤란로
INSERT INTO ACCOMMODATION (
    ACCOMMODATION_ID, ACCOMMODATION_NAME, ACCOMMODATION_ADDRESS, 
    ACCOMMODATION_DESCRIPTION, ACCOMMODATION_PRICE, LOCATION_NAME, 
    RECOMMENDATION_SEASON, ACCOMMODATION_STATUS, ALLOWED_NUMBER
) VALUES (
    ACCOMMODATION_SEQ.NEXTVAL, '강남 비즈니스 호텔', '서울 강남구 테헤란로 123길', 
    '강남구 중심에 위치한 비즈니스 호텔로 편리한 교통을 제공합니다.', 120000, '서울', 
    '봄', 1, 30
);

-- 2. 서울 마포구 홍대입구
INSERT INTO ACCOMMODATION (
    ACCOMMODATION_ID, ACCOMMODATION_NAME, ACCOMMODATION_ADDRESS, 
    ACCOMMODATION_DESCRIPTION, ACCOMMODATION_PRICE, LOCATION_NAME, 
    RECOMMENDATION_SEASON, ACCOMMODATION_STATUS, ALLOWED_NUMBER
) VALUES (
    ACCOMMODATION_SEQ.NEXTVAL, '홍대 게스트하우스', '서울 마포구 홍대입구로 345', 
    '홍대에서 가까운 곳에 위치한 편안한 게스트하우스입니다.', 50000, '서울', 
    '여름', 1, 15
);

-- 3. 서울 종로구 인사동
INSERT INTO ACCOMMODATION (
    ACCOMMODATION_ID, ACCOMMODATION_NAME, ACCOMMODATION_ADDRESS, 
    ACCOMMODATION_DESCRIPTION, ACCOMMODATION_PRICE, LOCATION_NAME, 
    RECOMMENDATION_SEASON, ACCOMMODATION_STATUS, ALLOWED_NUMBER
) VALUES (
    ACCOMMODATION_SEQ.NEXTVAL, '인사동 전통 한옥', '서울 종로구 인사동 12-4', 
    '전통 한옥에서 편안한 숙박을 즐길 수 있습니다.', 80000, '서울', 
    '가을', 1, 10
);

-- 4. 서울 강서구 마곡
INSERT INTO ACCOMMODATION (
    ACCOMMODATION_ID, ACCOMMODATION_NAME, ACCOMMODATION_ADDRESS, 
    ACCOMMODATION_DESCRIPTION, ACCOMMODATION_PRICE, LOCATION_NAME, 
    RECOMMENDATION_SEASON, ACCOMMODATION_STATUS, ALLOWED_NUMBER
) VALUES (
    ACCOMMODATION_SEQ.NEXTVAL, '마곡 비즈니스 리조트', '서울 강서구 마곡동 45', 
    '마곡지구에 위치한 현대적인 비즈니스 리조트입니다.', 150000, '서울', 
    '여름', 1, 20
);

-- 5. 서울 용산구 이태원
INSERT INTO ACCOMMODATION (
    ACCOMMODATION_ID, ACCOMMODATION_NAME, ACCOMMODATION_ADDRESS, 
    ACCOMMODATION_DESCRIPTION, ACCOMMODATION_PRICE, LOCATION_NAME, 
    RECOMMENDATION_SEASON, ACCOMMODATION_STATUS, ALLOWED_NUMBER
) VALUES (
    ACCOMMODATION_SEQ.NEXTVAL, '이태원 고급 리조트', '서울 용산구 이태원로 56', 
    '이태원에서 멋진 서울 시내를 볼 수 있는 고급 리조트입니다.', 200000, '서울', 
    '봄', 1, 25
);

-- 6. 서울 송파구 잠실
INSERT INTO ACCOMMODATION (
    ACCOMMODATION_ID, ACCOMMODATION_NAME, ACCOMMODATION_ADDRESS, 
    ACCOMMODATION_DESCRIPTION, ACCOMMODATION_PRICE, LOCATION_NAME, 
    RECOMMENDATION_SEASON, ACCOMMODATION_STATUS, ALLOWED_NUMBER
) VALUES (
    ACCOMMODATION_SEQ.NEXTVAL, '잠실 롯데호텔', '서울 송파구 잠실로 123', 
    '잠실에 위치한 고급 호텔로 롯데월드와 가까운 거리입니다.', 250000, '서울', 
    '여름', 1, 40
);

-- 7. 서울 동대문구 장안동
INSERT INTO ACCOMMODATION (
    ACCOMMODATION_ID, ACCOMMODATION_NAME, ACCOMMODATION_ADDRESS, 
    ACCOMMODATION_DESCRIPTION, ACCOMMODATION_PRICE, LOCATION_NAME, 
    RECOMMENDATION_SEASON, ACCOMMODATION_STATUS, ALLOWED_NUMBER
) VALUES (
    ACCOMMODATION_SEQ.NEXTVAL, '장안동 스타일 리조트', '서울 동대문구 장안동 78', 
    '동대문 근처에 위치한 예쁜 디자인의 리조트입니다.', 100000, '서울', 
    '가을', 1, 15
);

-- 8. 서울 서대문구 신촌
INSERT INTO ACCOMMODATION (
    ACCOMMODATION_ID, ACCOMMODATION_NAME, ACCOMMODATION_ADDRESS, 
    ACCOMMODATION_DESCRIPTION, ACCOMMODATION_PRICE, LOCATION_NAME, 
    RECOMMENDATION_SEASON, ACCOMMODATION_STATUS, ALLOWED_NUMBER
) VALUES (
    ACCOMMODATION_SEQ.NEXTVAL, '신촌 현대 호텔', '서울 서대문구 신촌로 25', 
    '신촌에 위치한 현대적인 스타일의 호텔입니다.', 130000, '서울', 
    '봄', 1, 35
);

-- 9. 서울 관악구 대학동
INSERT INTO ACCOMMODATION (
    ACCOMMODATION_ID, ACCOMMODATION_NAME, ACCOMMODATION_ADDRESS, 
    ACCOMMODATION_DESCRIPTION, ACCOMMODATION_PRICE, LOCATION_NAME, 
    RECOMMENDATION_SEASON, ACCOMMODATION_STATUS, ALLOWED_NUMBER
) VALUES (
    ACCOMMODATION_SEQ.NEXTVAL, '대학동 게스트하우스', '서울 관악구 대학동 12', 
    '서울대 근처에 위치한 저렴한 게스트하우스입니다.', 40000, '서울', 
    '여름', 1, 10
);

-- 10. 서울 성동구 왕십리
INSERT INTO ACCOMMODATION (
    ACCOMMODATION_ID, ACCOMMODATION_NAME, ACCOMMODATION_ADDRESS, 
    ACCOMMODATION_DESCRIPTION, ACCOMMODATION_PRICE, LOCATION_NAME, 
    RECOMMENDATION_SEASON, ACCOMMODATION_STATUS, ALLOWED_NUMBER
) VALUES (
    ACCOMMODATION_SEQ.NEXTVAL, '왕십리 고급 리조트', '서울 성동구 왕십리로 78', 
    '왕십리 역 근처에 위치한 고급 리조트입니다.', 180000, '서울', 
    '겨울', 1, 50
);


INSERT INTO ADMIN (ADMIN_ID, ADMIN_PASSWORD, ADMIN_EMAIL, ADMIN_NAME) VALUES ('ADMIN', 'ADMIN' , 'ADMIN@EMAIL.COM' , '어드민');

COMMIT;

CREATE SEQUENCE AMMD_MGMT_SEQ;


INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user001', 'password123', 'John Doe', 'john.doe@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user002', 'password456', 'Jane Smith', 'jane.smith@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user003', 'password789', 'Tom Brown', 'tom.brown@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user004', 'password321', 'Lisa Green', 'lisa.green@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user005', 'password654', 'James White', 'james.white@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user006', 'password987', 'Mary Black', 'mary.black@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user007', 'password111', 'Chris Blue', 'chris.blue@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user008', 'password222', 'Sophia Yellow', 'sophia.yellow@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user009', 'password333', 'Daniel Red', 'daniel.red@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user010', 'password444', 'Olivia Pink', 'olivia.pink@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user011', 'password555', 'Ethan Gray', 'ethan.gray@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user012', 'password666', 'Charlotte Purple', 'charlotte.purple@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user013', 'password777', 'Amelia White', 'amelia.white@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user014', 'password888', 'Lucas Black', 'lucas.black@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user015', 'password999', 'Ella Green', 'ella.green@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user016', 'password000', 'Liam Brown', 'liam.brown@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user017', 'password1111', 'Mason Blue', 'mason.blue@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user018', 'password2222', 'Amos Silver', 'amos.silver@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user019', 'password3333', 'Chloe Pink', 'chloe.pink@example.com');
INSERT INTO "USER" ("USER_ID", "PASSWORD", "NAME", "EMAIL") VALUES ('user020', 'password4444', 'Henry Gold', 'henry.gold@example.com');

CREATE SEQUENCE SUBJECT_ID_SEQ;
-- 1. 숙소 예약 관련 문의
INSERT INTO "QNA_SUBJECT" ("SUBJECT_ID", "SUBJECT_NAME") 
VALUES (SUBJECT_ID_SEQ.NEXTVAL, '숙소 예약 확인');

-- 2. 결제 관련 문의
INSERT INTO "QNA_SUBJECT" ("SUBJECT_ID", "SUBJECT_NAME") 
VALUES (SUBJECT_ID_SEQ.NEXTVAL, '결제 방법 및 문제');

-- 3. 객실 정보 문의
INSERT INTO "QNA_SUBJECT" ("SUBJECT_ID", "SUBJECT_NAME") 
VALUES (SUBJECT_ID_SEQ.NEXTVAL, '객실 정보 및 시설');

-- 4. 예약 취소 및 변경
INSERT INTO "QNA_SUBJECT" ("SUBJECT_ID", "SUBJECT_NAME") 
VALUES (SUBJECT_ID_SEQ.NEXTVAL, '예약 취소 및 변경');

-- 5. 체크인/체크아웃 시간 문의
INSERT INTO "QNA_SUBJECT" ("SUBJECT_ID", "SUBJECT_NAME") 
VALUES (SUBJECT_ID_SEQ.NEXTVAL, '체크인 및 체크아웃 시간');

-- 6. 추가 요금 관련 문의
INSERT INTO "QNA_SUBJECT" ("SUBJECT_ID", "SUBJECT_NAME") 
VALUES (SUBJECT_ID_SEQ.NEXTVAL, '추가 요금 안내');

-- 7. 시설 및 서비스 문의
INSERT INTO "QNA_SUBJECT" ("SUBJECT_ID", "SUBJECT_NAME") 
VALUES (SUBJECT_ID_SEQ.NEXTVAL, '호텔 시설 및 서비스');

-- 8. 주차 및 교통 문의
INSERT INTO "QNA_SUBJECT" ("SUBJECT_ID", "SUBJECT_NAME") 
VALUES (SUBJECT_ID_SEQ.NEXTVAL, '주차 및 교통 정보');

-- 9. 주변 관광지 정보 문의
INSERT INTO "QNA_SUBJECT" ("SUBJECT_ID", "SUBJECT_NAME") 
VALUES (SUBJECT_ID_SEQ.NEXTVAL, '주변 관광지 정보');

-- 10. 할인 및 프로모션 문의
INSERT INTO "QNA_SUBJECT" ("SUBJECT_ID", "SUBJECT_NAME") 
VALUES (SUBJECT_ID_SEQ.NEXTVAL, '할인 및 프로모션');

-- 11. 시설 예약 관련 문의
INSERT INTO "QNA_SUBJECT" ("SUBJECT_ID", "SUBJECT_NAME") 
VALUES (SUBJECT_ID_SEQ.NEXTVAL, '회의실 및 기타 시설 예약');

-- 12. 피트니스 센터 및 수영장 이용 문의
INSERT INTO "QNA_SUBJECT" ("SUBJECT_ID", "SUBJECT_NAME") 
VALUES (SUBJECT_ID_SEQ.NEXTVAL, '피트니스 센터 및 수영장 이용');

-- 13. 애완동물 동반 여부 문의
INSERT INTO "QNA_SUBJECT" ("SUBJECT_ID", "SUBJECT_NAME") 
VALUES (SUBJECT_ID_SEQ.NEXTVAL, '애완동물 동반 여부');

-- 14. 음식 및 레스토랑 문의
INSERT INTO "QNA_SUBJECT" ("SUBJECT_ID", "SUBJECT_NAME") 
VALUES (SUBJECT_ID_SEQ.NEXTVAL, '음식 및 레스토랑 서비스');

-- 15. 특별 요청 사항 관련 문의
INSERT INTO "QNA_SUBJECT" ("SUBJECT_ID", "SUBJECT_NAME") 
VALUES (SUBJECT_ID_SEQ.NEXTVAL, '특별 요청 사항 (예: 흡연실, 장애인 편의시설)');

SELECT * FROM QNA_SUBJECT;

CREATE SEQUENCE QNA_SEQ;

-- 1. 숙소 예약 확인 관련 문의
INSERT INTO "QNA" (
    "QNA_ID", "USER_ID", "SUBJECT_ID", "ADMIN_ID", "QNA_CONTENT", 
    "QNA_ANSWERED_STATUS", "QNA_QUESTIONED_DATE"
) 
VALUES (
    QNA_SEQ.NEXTVAL, 'user001', 1, 'ADMIN', '숙소 예약이 제대로 되었는지 확인하고 싶습니다.', 0, SYSDATE
);

-- 2. 결제 방법 및 문제 관련 문의
INSERT INTO "QNA" (
    "QNA_ID", "USER_ID", "SUBJECT_ID", "ADMIN_ID", "QNA_CONTENT", 
    "QNA_ANSWERED_STATUS", "QNA_QUESTIONED_DATE"
) 
VALUES (
    QNA_SEQ.NEXTVAL, 'user002', 2, 'ADMIN', '결제 과정에서 오류가 발생했습니다. 어떻게 해결할 수 있을까요?', 0, SYSDATE
);

-- 3. 객실 정보 및 시설 관련 문의
INSERT INTO "QNA" (
    "QNA_ID", "USER_ID", "SUBJECT_ID", "ADMIN_ID", "QNA_CONTENT", 
    "QNA_ANSWERED_STATUS", "QNA_QUESTIONED_DATE"
) 
VALUES (
    QNA_SEQ.NEXTVAL, 'user003', 3, 'ADMIN', '객실 내 시설에 대한 더 자세한 정보를 알고 싶습니다.', 0, SYSDATE
);

-- 4. 예약 취소 및 변경 관련 문의
INSERT INTO "QNA" (
    "QNA_ID", "USER_ID", "SUBJECT_ID", "ADMIN_ID", "QNA_CONTENT", 
    "QNA_ANSWERED_STATUS", "QNA_QUESTIONED_DATE"
) 
VALUES (
    QNA_SEQ.NEXTVAL, 'user004', 4, 'ADMIN', '예약을 취소하거나 변경하려면 어떻게 해야 하나요?', 0, SYSDATE
);

-- 5. 체크인/체크아웃 시간 관련 문의
INSERT INTO "QNA" (
    "QNA_ID", "USER_ID", "SUBJECT_ID", "ADMIN_ID", "QNA_CONTENT", 
    "QNA_ANSWERED_STATUS", "QNA_QUESTIONED_DATE"
) 
VALUES (
    QNA_SEQ.NEXTVAL, 'user005', 5, 'ADMIN', '체크인과 체크아웃 시간을 더 유연하게 변경할 수 있나요?', 0, SYSDATE
);

-- 6. 추가 요금 안내 관련 문의
INSERT INTO "QNA" (
    "QNA_ID", "USER_ID", "SUBJECT_ID", "ADMIN_ID", "QNA_CONTENT", 
    "QNA_ANSWERED_STATUS", "QNA_QUESTIONED_DATE"
) 
VALUES (
    QNA_SEQ.NEXTVAL, 'user006', 6, 'ADMIN', '숙소 이용 시 추가 요금이 발생하는 부분이 궁금합니다.', 0, SYSDATE
);

-- 7. 호텔 시설 및 서비스 관련 문의
INSERT INTO "QNA" (
    "QNA_ID", "USER_ID", "SUBJECT_ID", "ADMIN_ID", "QNA_CONTENT", 
    "QNA_ANSWERED_STATUS", "QNA_QUESTIONED_DATE"
) 
VALUES (
    QNA_SEQ.NEXTVAL, 'user007', 7, 'ADMIN', '호텔 내에서 제공하는 서비스는 어떤 것들이 있나요?', 0, SYSDATE
);

-- 8. 주차 및 교통 정보 관련 문의
INSERT INTO "QNA" (
    "QNA_ID", "USER_ID", "SUBJECT_ID", "ADMIN_ID", "QNA_CONTENT", 
    "QNA_ANSWERED_STATUS", "QNA_QUESTIONED_DATE"
) 
VALUES (
    QNA_SEQ.NEXTVAL, 'user008', 8, 'ADMIN', '주차공간과 근처 교통수단에 대해 알고 싶습니다.', 0, SYSDATE
);

-- 9. 주변 관광지 정보 관련 문의
INSERT INTO "QNA" (
    "QNA_ID", "USER_ID", "SUBJECT_ID", "ADMIN_ID", "QNA_CONTENT", 
    "QNA_ANSWERED_STATUS", "QNA_QUESTIONED_DATE"
) 
VALUES (
    QNA_SEQ.NEXTVAL, 'user009', 9, 'ADMIN', '숙소 근처 관광지에 대한 정보를 알려주세요.', 0, SYSDATE
);

-- 10. 할인 및 프로모션 관련 문의
INSERT INTO "QNA" (
    "QNA_ID", "USER_ID", "SUBJECT_ID", "ADMIN_ID", "QNA_CONTENT", 
    "QNA_ANSWERED_STATUS", "QNA_QUESTIONED_DATE"
) 
VALUES (
    QNA_SEQ.NEXTVAL, 'user010', 10, 'ADMIN', '현재 진행 중인 할인이나 프로모션이 있나요?', 0, SYSDATE
);
COMMIT;
