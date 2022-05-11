-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema test_brp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema test_brp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test_brp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `test_brp` ;

-- -----------------------------------------------------
-- Table `test_brp`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_brp`.`address` (
  `address_id` INT(11) NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `zip_code` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`address_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 29
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test_brp`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_brp`.`users` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `address_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `UKr43af9ap4edm43mmtq01oddj6` (`username` ASC),
  INDEX `users_address_fk_idx` (`address_id` ASC),
  CONSTRAINT `FKditu6lr4ek16tkxtdsne0gxib`
    FOREIGN KEY (`address_id`)
    REFERENCES `test_brp`.`address` (`address_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test_brp`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_brp`.`book` (
  `book_id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `ISBN` VARCHAR(45) NOT NULL,
  `author` VARCHAR(45) NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  `rental_rate` INT(11) NULL DEFAULT NULL,
  `replacement_cost` FLOAT NOT NULL,
  `user_id` INT(11) NOT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`book_id`),
  INDEX `book_users_fk_idx` (`user_id` ASC),
  CONSTRAINT `FK9cv1tt952k857xoia51k1vj12`
    FOREIGN KEY (`user_id`)
    REFERENCES `test_brp`.`users` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test_brp`.`cb`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_brp`.`cb` (
  `cb_id` INT(11) NOT NULL AUTO_INCREMENT,
  `cb_holder` VARCHAR(45) NOT NULL,
  `cb_cryptogram` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cb_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test_brp`.`inventory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_brp`.`inventory` (
  `inventory_id` INT(11) NOT NULL AUTO_INCREMENT,
  `book_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`inventory_id`),
  INDEX `inventory_book_fk_idx` (`book_id` ASC),
  INDEX `inventory_user_fk_idx` (`user_id` ASC),
  CONSTRAINT `FK6s70ikopm646wy54vwowsnp6d`
    FOREIGN KEY (`user_id`)
    REFERENCES `test_brp`.`users` (`user_id`),
  CONSTRAINT `FK8rus8qhfxl7ds6cv9xtawtrrk`
    FOREIGN KEY (`book_id`)
    REFERENCES `test_brp`.`book` (`book_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test_brp`.`rental`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_brp`.`rental` (
  `rental_id` INT(11) NOT NULL AUTO_INCREMENT,
  `rental_date` DATE NOT NULL,
  `return_date` DATE NULL DEFAULT NULL,
  `inventory_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`rental_id`),
  INDEX `rental_inventory_fk_idx` (`inventory_id` ASC),
  INDEX `rental_users_fk_idx` (`user_id` ASC),
  CONSTRAINT `FKha0q4deoej14ctjwlnwghdoi9`
    FOREIGN KEY (`inventory_id`)
    REFERENCES `test_brp`.`inventory` (`inventory_id`),
  CONSTRAINT `FKj1ty59tjbwlmx7p4uotyto7lp`
    FOREIGN KEY (`user_id`)
    REFERENCES `test_brp`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test_brp`.`delivery`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_brp`.`delivery` (
  `delivery_id` INT(11) NOT NULL AUTO_INCREMENT,
  `rental_id` INT(11) NOT NULL,
  `delivery_method` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`delivery_id`),
  INDEX `delivery_rental_fk_idx` (`rental_id` ASC),
  CONSTRAINT `FKcvjuvmlk6vqa8j47slcdom8iu`
    FOREIGN KEY (`rental_id`)
    REFERENCES `test_brp`.`rental` (`rental_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test_brp`.`forum`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_brp`.`forum` (
  `forum_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NULL DEFAULT NULL,
  `message` MEDIUMTEXT NULL DEFAULT NULL,
  `etat` INT(11) NULL DEFAULT NULL,
  `book_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`forum_id`),
  INDEX `forum_book_fk_idx` (`book_id` ASC),
  CONSTRAINT `FKrpfhlx3a2objxh51pl68y9h27`
    FOREIGN KEY (`book_id`)
    REFERENCES `test_brp`.`book` (`book_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test_brp`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_brp`.`payment` (
  `payment_id` INT(11) NOT NULL AUTO_INCREMENT,
  `amount` FLOAT NOT NULL,
  `payment_date` VARCHAR(45) NOT NULL,
  `rental_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `cb_id` INT(11) NOT NULL,
  `commission` FLOAT NOT NULL,
  PRIMARY KEY (`payment_id`),
  INDEX `payment_cb_fk_idx` (`cb_id` ASC),
  INDEX `payment_user_fk_idx` (`user_id` ASC),
  INDEX `payment_rental_fk_idx` (`rental_id` ASC),
  CONSTRAINT `FK6ma2j46gjbggrp573uya2ub45`
    FOREIGN KEY (`rental_id`)
    REFERENCES `test_brp`.`rental` (`rental_id`),
  CONSTRAINT `FKlstd80dqdidvhn6ne3y1n5xmm`
    FOREIGN KEY (`cb_id`)
    REFERENCES `test_brp`.`cb` (`cb_id`),
  CONSTRAINT `FKmi2669nkjesvp7cd257fptl6f`
    FOREIGN KEY (`user_id`)
    REFERENCES `test_brp`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test_brp`.`photo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_brp`.`photo` (
  `photo_id` INT(11) NOT NULL AUTO_INCREMENT,
  `photo` LONGBLOB NOT NULL,
  `photo_role` VARCHAR(45) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `book_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`photo_id`),
  INDEX `photo_users_fk_idx` (`user_id` ASC),
  INDEX `FKchbun2i3ols18pi6gulmc9ilc` (`book_id` ASC),
  CONSTRAINT `FKbcys1buwxiujfybp4506v57w3`
    FOREIGN KEY (`user_id`)
    REFERENCES `test_brp`.`users` (`user_id`),
  CONSTRAINT `FKchbun2i3ols18pi6gulmc9ilc`
    FOREIGN KEY (`book_id`)
    REFERENCES `test_brp`.`book` (`book_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test_brp`.`users_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_brp`.`users_roles` (
  `role_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_role` VARCHAR(45) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`role_id`),
  INDEX `user_role_fk_idx` (`user_id` ASC),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa`
    FOREIGN KEY (`user_id`)
    REFERENCES `test_brp`.`users` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
