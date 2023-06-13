
DROP TABLE IF EXISTS `Accounts` ;

DROP TABLE IF EXISTS `AuthorityCodes` ;

DROP TABLE IF EXISTS `StatusCodes` ;


CREATE TABLE IF NOT EXISTS `StatusCodes` (
                                             `status_code_seq` INT NOT NULL AUTO_INCREMENT,
                                             `status_code_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`status_code_seq`)
    );

CREATE TABLE IF NOT EXISTS `AuthorityCodes` (
                                                `authority_code_seq` INT NOT NULL AUTO_INCREMENT,
                                                `authority_code_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`authority_code_seq`)
    );

CREATE TABLE IF NOT EXISTS `Accounts` (
    `account_id` VARCHAR(20) NOT NULL,
    `account_password` char(60) NOT NULL,
    `account_email` VARCHAR(100) NOT NULL,
    `account_name` VARCHAR(50) NULL DEFAULT NULL,
    `account_created_at` DATETIME NOT NULL,
    `status_code_seq` INT NOT NULL,
    `authority_code_seq` INT NOT NULL,
    PRIMARY KEY (`account_id`),
    CONSTRAINT `fk_Account_StatusCode`
    FOREIGN KEY (`status_code_seq`)
    REFERENCES `StatusCodes` (`status_code_seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Account_AuthorityCode1`
    FOREIGN KEY (`authority_code_seq`)
    REFERENCES `AuthorityCodes` (`authority_code_seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );



-- 멤버 상태 코드 더미데이터 삽입
MERGE INTO StatusCodes VALUES (1, '가입');
MERGE INTO StatusCodes VALUES (2, '탈퇴');
MERGE INTO StatusCodes VALUES (3, '휴면');

-- 권한 코드 더미데이터 삽입
MERGE INTO AuthorityCodes VALUES (1, '관리자');
MERGE INTO AuthorityCodes VALUES (2, '회원');

-- 멤버 더미데이터 삽입
MERGE INTO Accounts VALUES ('goback10000', '$2a$10$fY14UMgpyxJQKxsdtBCzJeiQrWhd9jqKGmEkX0JdEeJFedTMo8W7q', 'back10000@gmail.com', 'marco', now(), 1, 1);
MERGE INTO Accounts VALUES ('jeongzbum', 'jzb', 'zbum@naver.com', null, now(), 1, 2);
MERGE INTO Accounts VALUES ('kimsiyeon', '$2a$10$2JrtuV13mUgKMNc6S25HVe0NX.q7vKlUpgQJi6WmYJ/B24XG7lp6S', 'swanshawn715@gmail.com','김시연', now(), 1, 2);
MERGE INTO Accounts VALUES ('goyoungeun', 'gye', 'youngeun@gmail.com', '고영은', now(), 1, 2);
MERGE INTO Accounts VALUES ('mooneunji', 'mej', 'eunji@naver.com', '문은지', now(), 1, 2);