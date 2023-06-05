-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema nhn_academy_3
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema nhn_academy_3
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nhn_academy_3` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `nhn_academy_3` ;

-- -----------------------------------------------------
-- Table `nhn_academy_3`.`StatusCodes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nhn_academy_3`.`StatusCodes` ;

CREATE TABLE IF NOT EXISTS `nhn_academy_3`.`StatusCodes` (
    `status_code_seq` INT NOT NULL AUTO_INCREMENT,
    `status_code_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`status_code_seq`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nhn_academy_3`.`AuthorityCodes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nhn_academy_3`.`AuthorityCodes` ;

CREATE TABLE IF NOT EXISTS `nhn_academy_3`.`AuthorityCodes` (
    `authority_code_seq` INT NOT NULL AUTO_INCREMENT,
    `authority_code_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`authority_code_seq`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nhn_academy_3`.`Accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nhn_academy_3`.`Accounts` ;

CREATE TABLE IF NOT EXISTS `nhn_academy_3`.`Accounts` (
    `account_id` VARCHAR(20) NOT NULL,
    `account_password` VARCHAR(25) NOT NULL,
    `account_email` VARCHAR(100) NOT NULL,
    `account_name` VARCHAR(50) NULL DEFAULT NULL,
    `account_created_at` DATETIME NOT NULL,
    `status_code_seq` INT NOT NULL DEFAULT 1,
    `authority_code_seq` INT NOT NULL,
    PRIMARY KEY (`account_id`),
    INDEX `fk_Account_StatusCode_idx` (`status_code_seq` ASC) VISIBLE,
    INDEX `fk_Account_AuthorityCode1_idx` (`authority_code_seq` ASC) VISIBLE,
    CONSTRAINT `fk_Account_StatusCode`
    FOREIGN KEY (`status_code_seq`)
    REFERENCES `nhn_academy_3`.`StatusCodes` (`status_code_seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Account_AuthorityCode1`
    FOREIGN KEY (`authority_code_seq`)
    REFERENCES `nhn_academy_3`.`AuthorityCodes` (`authority_code_seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



-- 멤버 상태 코드 더미데이터 삽입
INSERT INTO StatusCodes VALUES (1, "가입");
INSERT INTO StatusCodes VALUES (2, "탈퇴");
INSERT INTO StatusCodes VALUES (3, "휴면");

-- 권한 코드 더미데이터 삽입
INSERT INTO AuthorityCodes VALUES (1, "관리자");
INSERT INTO AuthorityCodes VALUES (2, "회원");

-- 멤버 더미데이터 삽입
INSERT INTO Accounts VALUES ("goback10000", "gbm", "back10000@gmail.com", null, now(), 1);
INSERT INTO Accounts VALUES ("jeongzbum", "jzb", "zbum@naver.com", null, now(), 1);
INSERT INTO Accounts VALUES ("kimsiyeon", "ksy", "swanshwan715@gmail.com","김시연", now(), 1);
INSERT INTO Accounts VALUES ("goyoungeun", "gye", "youngeun@gmail.com", "고영은", now(), 1);
INSERT INTO Accounts VALUES ("mooneunji", "mej", "eunji@naver.com", "문은지", now(), 1);