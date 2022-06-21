DROP TABLE IF EXISTS PLACE;
DROP TABLE IF EXISTS REVIEW;
DROP TABLE IF EXISTS IMAGE;
DROP TABLE IF EXISTS MILEAGE;

CREATE TABLE PLACE
(
    id           UUID         NOT NULL COMMENT '장소ID',
    name         VARCHAR(100) NOT NULL COMMENT '장소명',
    introduction VARCHAR(200) NOT NULL COMMENT '장소소개',
    createdAt    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '장소 등록시간',
    updatedAt    DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '장소 수정시간',
    PRIMARY KEY (id)
);

CREATE TABLE REVIEW
(
    id        UUID         NOT NULL COMMENT '리뷰ID',
    content   VARCHAR(200) NOT NULL COMMENT '리뷰내용',
    userId    UUID         NOT NULL COMMENT '유저ID',
    placeId   UUID         NOT NULL COMMENT '장소ID',
    isDeleted TINYINT      NOT NULL DEFAULT 0 COMMENT '리뷰 삭제여부',
    createdAt DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '리뷰 등록시간',
    updatedAt DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '리뷰 수정시간',
    PRIMARY KEY (id),
    foreign key (placeId) references PLACE (id)
);

CREATE INDEX index_review_user_id_place_id ON REVIEW (userId, placeId);
CREATE INDEX index_review_place_id ON REVIEW (placeId);

CREATE TABLE IMAGE
(
    id        UUID     NOT NULL COMMENT '이미지ID',
    reviewId  UUID     NOT NULL COMMENT '리뷰ID',
    isDeleted TINYINT  NOT NULL DEFAULT 0 COMMENT '이미지 삭제여부',
    createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '장소 등록시간',
    updatedAt DATETIME          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '장소 수정시간',
    PRIMARY KEY (id)
);

CREATE INDEX index_image_review_id ON IMAGE (reviewId);

CREATE TABLE MILEAGE
(
    id          UUID        NOT NULL COMMENT '마일리지ID',
    mileageType VARCHAR(25) NOT NULL COMMENT '마일리지타입',
    mileage     int         NOT NULL COMMENT '적립마일리지',
    userId      UUID        NOT NULL COMMENT '유저ID',
    createdAt   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '마일리지 등록시간',
    PRIMARY KEY (id)
);

CREATE INDEX index_mileage_user_id ON MILEAGE (userId);