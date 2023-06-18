
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
MERGE INTO Accounts VALUES ('goback10000', '$2a$10$qaQbVL0LdebZRLzK8ikjZeXAkVq7C5zskT3jkZMeySwQzzSpq6kZa', 'back10000@gmail.com', 'marco', now(), 1, 1);
INSERT INTO Accounts VALUES ('jeongzbum', '$2a$10$9h4ng65aLPTR2aph81M/1eYbJte3lnOp0mbbjVsjHCy/C6dA5UEda', 'zbum@naver.com', 'zbum', now(), 1, 2);
INSERT INTO Accounts VALUES ('kimsiyeon', '$2a$10$0xDGDSomXq6yxyV3s1G0vOJ1iWg3YcV8tGUpn5BQar1aC/41B6ib2', 'swanshwan715@gmail.com','김시연', now(), 1, 2);
INSERT INTO Accounts VALUES ('goyoungeun', '$2a$10$WbBDLZfrOQuydtpHHz5Vp.AsCALJAPkyZCXGnVjDQZN7m0qPSdlIi', 'youngeun@gmail.com', '고영은', now(), 1, 2);
INSERT INTO Accounts VALUES ('mooneunji', '$2a$10$OC4KQ1V.ytPmM0WR99mxLewiPsFNR.cEi6yGsdtH2EKim8ypDmqAu', 'eunji@naver.com', '문은지', now(), 1, 2);