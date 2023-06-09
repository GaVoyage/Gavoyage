
use gavoyage;

-- drop table 구문
drop table if exists DailyPlan;
drop table if exists Scrap;
drop table if exists Follow;
drop table if exists UserLikeAttraction;
drop table if exists Comment;
drop table if exists Likes;
drop table if exists Recommend;
drop table if exists Unrecommend;
drop table if exists Review;
drop table if exists Plan;
drop table if exists User;

-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- User Table Create SQL
-- 테이블 생성 SQL - User
CREATE TABLE User
(
    `userIdx`       BIGINT          NOT NULL    AUTO_INCREMENT,
    `email`         VARCHAR(45)     NOT NULL    COMMENT '사용자 이메일',
    `nickname`      VARCHAR(45)     NOT NULL    COMMENT '사용자 이름',
    `userPassword`  VARCHAR(200)    NOT NULL    COMMENT '비밀번호',
    `userImageUrl`  TEXT            NULL        COMMENT '프로필 사진',
    `phoneNumber`   VARCHAR(20)     NOT NULL    COMMENT '전화번호',
    `socialType`    CHAR(50)        NULL        COMMENT 'KAKAO, NAVER',
    `socialID`      VARCHAR(200)    NULL        COMMENT '인증 서버에서 제공하는 유저 PK',
    `refreshToken`  VARCHAR(200)    NULL        COMMENT 'refresh token',
    `userRole`      CHAR(50)        NOT NULL    DEFAULT 'ROLE_USER' COMMENT 'ROLE_ADMIN : 관리자 / ROLE_USER : 일반 회원',
    `status`        CHAR(1)         NOT NULL    DEFAULT 'Y' COMMENT 'N : 탈퇴한 회원 / Y : 활동 중인 회원',
    `createdAt`     TIMESTAMP       NOT NULL    DEFAULT current_timestamp,
    `modifiedAt`    TIMESTAMP       NULL        DEFAULT current_timestamp on update current_timestamp,
    CONSTRAINT PK_MEMBERS PRIMARY KEY (userIdx)
);

-- 테이블 Comment 설정 SQL - User
ALTER TABLE User COMMENT '사용자 테이블';


-- Plan Table Create SQL
-- 테이블 생성 SQL - Plan
CREATE TABLE Plan
(
    `planIdx`     BIGINT          NOT NULL    AUTO_INCREMENT,
    `userIdx`     BIGINT          NOT NULL,
    `title`       VARCHAR(200)    NOT NULL    COMMENT '여행 계획 제목',
    `startDate`   TIMESTAMP       NOT NULL    COMMENT '여행 시작일',
    `endDate`     TIMESTAMP       NOT NULL    COMMENT '여행 종료일',
    `status`      CHAR(1)         NOT NULL    DEFAULT 'Y',
    `createdAt`   TIMESTAMP       NOT NULL    DEFAULT current_timestamp,
    `modifiedAt`  TIMESTAMP       NULL        DEFAULT current_timestamp on update current_timestamp,
    CONSTRAINT PK_PLAN PRIMARY KEY (planIdx)
);

-- 테이블 Comment 설정 SQL - Plan
ALTER TABLE Plan COMMENT '여행 계획 테이블';

-- Foreign Key 설정 SQL - Plan(userIdx) -> User(userIdx)
ALTER TABLE Plan
    ADD CONSTRAINT FK_Plan_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Plan(userIdx)
-- ALTER TABLE Plan
-- DROP FOREIGN KEY FK_Plan_userIdx_User_userIdx;


-- Review Table Create SQL
-- 테이블 생성 SQL - Review
CREATE TABLE Review
(
    `reviewIdx`   BIGINT          NOT NULL    AUTO_INCREMENT,
    `userIdx`     BIGINT          NOT NULL,
    `planIdx`     BIGINT          NOT NULL,
    `title`       VARCHAR(200)    NOT NULL,
    `contents`    TEXT            NOT NULL,
    `hit`         INT             NOT NULL,
    `status`      CHAR(1)         NOT NULL    DEFAULT 'Y',
    `createdAt`   TIMESTAMP       NOT NULL    DEFAULT current_timestamp,
    `modifiedAt`  TIMESTAMP       NULL        DEFAULT current_timestamp on update current_timestamp,
    CONSTRAINT PK_REVIEW PRIMARY KEY (reviewIdx)
);

-- 테이블 Comment 설정 SQL - Review
ALTER TABLE Review COMMENT '여행 후기 테이블';

-- Foreign Key 설정 SQL - Review(userIdx) -> User(userIdx)
ALTER TABLE Review
    ADD CONSTRAINT FK_Review_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Review(userIdx)
-- ALTER TABLE Review
-- DROP FOREIGN KEY FK_Review_userIdx_User_userIdx;

-- Foreign Key 설정 SQL - Review(planIdx) -> Plan(planIdx)
ALTER TABLE Review
    ADD CONSTRAINT FK_Review_planIdx_Plan_planIdx FOREIGN KEY (planIdx)
        REFERENCES Plan (planIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Review(planIdx)
-- ALTER TABLE Review
-- DROP FOREIGN KEY FK_Review_planIdx_Plan_planIdx;


-- Likes Table Create SQL
-- 테이블 생성 SQL - Likes
CREATE TABLE Likes
(
    `likeIdx`    BIGINT       NOT NULL    AUTO_INCREMENT,
    `userIdx`    BIGINT       NOT NULL,
    `reviewIdx`  BIGINT       NOT NULL,
    `status`     CHAR(1)      NOT NULL    DEFAULT 'Y',
    `createdAt`  TIMESTAMP    NOT NULL    DEFAULT current_timestamp,
    `modifedAt`  TIMESTAMP    NULL        DEFAULT current_timestamp on update current_timestamp,
    CONSTRAINT PK_LIKES PRIMARY KEY (likeIdx)
);

-- 테이블 Comment 설정 SQL - Likes
ALTER TABLE Likes COMMENT '사용자가 좋아요한 여행 후기';

-- Foreign Key 설정 SQL - Likes(userIdx) -> User(userIdx)
ALTER TABLE Likes
    ADD CONSTRAINT FK_Likes_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Likes(userIdx)
-- ALTER TABLE Likes
-- DROP FOREIGN KEY FK_Likes_userIdx_User_userIdx;

-- Foreign Key 설정 SQL - Likes(reviewIdx) -> Review(reviewIdx)
ALTER TABLE Likes
    ADD CONSTRAINT FK_Likes_reviewIdx_Review_reviewIdx FOREIGN KEY (reviewIdx)
        REFERENCES Review (reviewIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Likes(reviewIdx)
-- ALTER TABLE Likes
-- DROP FOREIGN KEY FK_Likes_reviewIdx_Review_reviewIdx;


-- Follow Table Create SQL
-- 테이블 생성 SQL - Follow
CREATE TABLE Follow
(
    `followIdx`      BIGINT       NOT NULL    AUTO_INCREMENT,
    `userIdx`        BIGINT       NOT NULL,
    `targetUserIdx`  BIGINT       NOT NULL,
    `status`         CHAR(1)      NOT NULL    DEFAULT 'Y',
    `createdAt`      TIMESTAMP    NOT NULL    DEFAULT current_timestamp,
    `modifiedAt`     TIMESTAMP    NULL        DEFAULT current_timestamp on update current_timestamp,
    CONSTRAINT PK_FOLLOW PRIMARY KEY (followIdx)
);

-- Foreign Key 설정 SQL - Follow(userIdx) -> User(userIdx)
ALTER TABLE Follow
    ADD CONSTRAINT FK_Follow_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Follow(userIdx)
-- ALTER TABLE Follow
-- DROP FOREIGN KEY FK_Follow_userIdx_User_userIdx;

-- Foreign Key 설정 SQL - Follow(targetUserIdx) -> User(userIdx)
ALTER TABLE Follow
    ADD CONSTRAINT FK_Follow_targetUserIdx_User_userIdx FOREIGN KEY (targetUserIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Follow(targetUserIdx)
-- ALTER TABLE Follow
-- DROP FOREIGN KEY FK_Follow_targetUserIdx_User_userIdx;


-- Comment Table Create SQL
-- 테이블 생성 SQL - Comment
CREATE TABLE Comment
(
    `commentIdx`  BIGINT       NOT NULL    AUTO_INCREMENT,
    `userIdx`     BIGINT       NOT NULL,
    `reviewIdx`   BIGINT       NOT NULL,
    `contents`    TEXT         NOT NULL,
    `status`      CHAR(1)      NOT NULL    DEFAULT 'Y',
    `createdAt`   TIMESTAMP    NOT NULL    DEFAULT current_timestamp,
    `modifiedAt`  TIMESTAMP    NULL        DEFAULT current_timestamp on update current_timestamp,
    CONSTRAINT PK_COMMENTS PRIMARY KEY (commentIdx)
);

-- 테이블 Comment 설정 SQL - Comment
ALTER TABLE Comment COMMENT '여행 후기에 달린 댓글';

-- Foreign Key 설정 SQL - Comment(userIdx) -> User(userIdx)
ALTER TABLE Comment
    ADD CONSTRAINT FK_Comment_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Comment(userIdx)
-- ALTER TABLE Comment
-- DROP FOREIGN KEY FK_Comment_userIdx_User_userIdx;

-- Foreign Key 설정 SQL - Comment(reviewIdx) -> Review(reviewIdx)
ALTER TABLE Comment
    ADD CONSTRAINT FK_Comment_reviewIdx_Review_reviewIdx FOREIGN KEY (reviewIdx)
        REFERENCES Review (reviewIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Comment(reviewIdx)
-- ALTER TABLE Comment
-- DROP FOREIGN KEY FK_Comment_reviewIdx_Review_reviewIdx;


-- Recommend Table Create SQL
-- 테이블 생성 SQL - Recommend
CREATE TABLE Recommend
(
    `recommendIdx`  BIGINT       NOT NULL    AUTO_INCREMENT,
    `reviewIdx`       BIGINT       NOT NULL,
    `content_id`      INT          NOT NULL,
    `status`          CHAR(1)      NOT NULL    DEFAULT 'Y',
    `createdAt`       TIMESTAMP    NOT NULL    DEFAULT current_timestamp,
    `modifiedAt`      TIMESTAMP    NULL        DEFAULT current_timestamp on update current_timestamp,
    CONSTRAINT PK_RECOMMEND PRIMARY KEY (recommendIdx)
);

-- 테이블 Comment 설정 SQL - Recommend
ALTER TABLE Recommend COMMENT '여행 후기 내 추천하는 여행지';

-- Foreign Key 설정 SQL - Recommend(reviewIdx) -> Review(reviewIdx)
ALTER TABLE Recommend
    ADD CONSTRAINT FK_Recommend_reviewIdx_Review_reviewIdx FOREIGN KEY (reviewIdx)
        REFERENCES Review (reviewIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Recommend(reviewIdx)
-- ALTER TABLE Recommend
-- DROP FOREIGN KEY FK_Recommend_reviewIdx_Review_reviewIdx;


-- Unrecommend Table Create SQL
-- 테이블 생성 SQL - Unrecommend
CREATE TABLE Unrecommend
(
    `unrecommendIdx`  BIGINT       NOT NULL    AUTO_INCREMENT,
    `reviewIdx`       BIGINT       NOT NULL,
    `content_id`      INT          NOT NULL,
    `status`          CHAR(1)      NOT NULL    DEFAULT 'Y',
    `createdAt`       TIMESTAMP    NOT NULL    DEFAULT current_timestamp,
    `modifiedAt`      TIMESTAMP    NULL        DEFAULT current_timestamp on update current_timestamp,
    CONSTRAINT PK_UNRECOMMEND PRIMARY KEY (unrecommendIdx)
);

-- 테이블 Comment 설정 SQL - Unrecommend
ALTER TABLE Unrecommend COMMENT '여행 후기 내 비추천하는 여행지';

-- Foreign Key 설정 SQL - Unrecommend(reviewIdx) -> Review(reviewIdx)
ALTER TABLE Unrecommend
    ADD CONSTRAINT FK_Unrecommend_reviewIdx_Review_reviewIdx FOREIGN KEY (reviewIdx)
        REFERENCES Review (reviewIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Unrecommend(reviewIdx)
-- ALTER TABLE Unrecommend
-- DROP FOREIGN KEY FK_Unrecommend_reviewIdx_Review_reviewIdx;


-- DailyPlan Table Create SQL
-- 테이블 생성 SQL - DailyPlan
CREATE TABLE DailyPlan
(
    `dailyPlanIdx`  BIGINT       NOT NULL    AUTO_INCREMENT,
    `planIdx`       BIGINT       NOT NULL,
    `content_id`    INT          NOT NULL,
    `dailyDate`     TIMESTAMP    NOT NULL,
    `status`        CHAR(1)      NOT NULL    DEFAULT 'Y',
    `createdAt`     TIMESTAMP    NOT NULL    DEFAULT current_timestamp,
    `modifiedAt`    TIMESTAMP    NULL        DEFAULT current_timestamp on update current_timestamp,
     PRIMARY KEY (dailyPlanIdx)
);

-- 테이블 Comment 설정 SQL - DailyPlan
ALTER TABLE DailyPlan COMMENT '여행 계획 내부 Day별 계획';

-- Foreign Key 설정 SQL - DailyPlan(planIdx) -> Plan(planIdx)
ALTER TABLE DailyPlan
    ADD CONSTRAINT FK_DailyPlan_planIdx_Plan_planIdx FOREIGN KEY (planIdx)
        REFERENCES Plan (planIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 설정 SQL - DailyPlan(content_id) -> attraction_info(content_id)
ALTER TABLE DailyPlan
    ADD CONSTRAINT FK_DailyPlan_content_id_Attraction_Info_content_id FOREIGN KEY (content_id)
        REFERENCES attraction_info (content_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - DailyPlan(planIdx)
-- ALTER TABLE DailyPlan
-- DROP FOREIGN KEY FK_DailyPlan_planIdx_Plan_planIdx;


-- Scrap Table Create SQL
-- 테이블 생성 SQL - Scrap
CREATE TABLE Scrap
(
    `scrapIdx`    BIGINT       NOT NULL    AUTO_INCREMENT,
    `userIdx`     BIGINT       NOT NULL,
    `content_id`  INT          NOT NULL,
    `status`      CHAR(1)      NOT NULL    DEFAULT 'Y',
    `createdAt`   TIMESTAMP    NOT NULL    DEFAULT current_timestamp,
    `modifiedAt`  TIMESTAMP    NOT NULL    DEFAULT current_timestamp on update current_timestamp on update current_timestamp,
     PRIMARY KEY (scrapIdx)
);

-- 테이블 Comment 설정 SQL - Scrap
ALTER TABLE Scrap COMMENT '사용자가 스크랩한 여행지';

-- Foreign Key 설정 SQL - Scrap(userIdx) -> User(userIdx)
ALTER TABLE Scrap
    ADD CONSTRAINT FK_Scrap_userIdx_User_userIdx FOREIGN KEY (userIdx)
        REFERENCES User (userIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 설정 SQL - Scrap(content_id) -> Attraction_Info(content_id)
ALTER TABLE Scrap
    ADD CONSTRAINT FK_Scrap_content_id_Attraction_Info_content_id FOREIGN KEY (content_id)
        REFERENCES attraction_info (content_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Scrap(userIdx)
-- ALTER TABLE Scrap
-- DROP FOREIGN KEY FK_Scrap_userIdx_User_userIdx;


