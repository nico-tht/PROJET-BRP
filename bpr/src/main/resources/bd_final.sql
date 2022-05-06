-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema test_brp
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `test_brp` ;

-- -----------------------------------------------------
-- Schema test_brp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test_brp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `test_brp` ;

-- -----------------------------------------------------
-- Table `test_brp`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_brp`.`address` ;

CREATE TABLE IF NOT EXISTS `test_brp`.`address` (
  `address_id` INT(11) NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `zip_code` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`address_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 26
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `test_brp`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_brp`.`users` ;

CREATE TABLE IF NOT EXISTS `test_brp`.`users` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `address_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `username_UNIQUE` ON `test_brp`.`users` (`username` ASC);

CREATE UNIQUE INDEX `UKr43af9ap4edm43mmtq01oddj6` ON `test_brp`.`users` (`username` ASC);

CREATE INDEX `users_address_fk_idx` ON `test_brp`.`users` (`address_id` ASC);


-- -----------------------------------------------------
-- Table `test_brp`.`book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_brp`.`book` ;

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
  PRIMARY KEY (`book_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `book_users_fk_idx` ON `test_brp`.`book` (`user_id` ASC);


-- -----------------------------------------------------
-- Table `test_brp`.`cb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_brp`.`cb` ;

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
DROP TABLE IF EXISTS `test_brp`.`inventory` ;

CREATE TABLE IF NOT EXISTS `test_brp`.`inventory` (
  `inventory_id` INT(11) NOT NULL AUTO_INCREMENT,
  `book_id` INT(11) NOT NULL,
  PRIMARY KEY (`inventory_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `inventory_book_fk_idx` ON `test_brp`.`inventory` (`book_id` ASC);


-- -----------------------------------------------------
-- Table `test_brp`.`rental`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_brp`.`rental` ;

CREATE TABLE IF NOT EXISTS `test_brp`.`rental` (
  `rental_id` INT(11) NOT NULL AUTO_INCREMENT,
  `rental_date` DATE NOT NULL,
  `return_date` DATE NULL DEFAULT NULL,
  `inventory_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`rental_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `rental_inventory_fk_idx` ON `test_brp`.`rental` (`inventory_id` ASC);

CREATE INDEX `rental_users_fk_idx` ON `test_brp`.`rental` (`user_id` ASC);


-- -----------------------------------------------------
-- Table `test_brp`.`delivery`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_brp`.`delivery` ;

CREATE TABLE IF NOT EXISTS `test_brp`.`delivery` (
  `delivery_id` INT(11) NOT NULL AUTO_INCREMENT,
  `rental_id` INT(11) NOT NULL,
  `delivery_method` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`delivery_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `delivery_rental_fk_idx` ON `test_brp`.`delivery` (`rental_id` ASC);


-- -----------------------------------------------------
-- Table `test_brp`.`forum`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_brp`.`forum` ;

CREATE TABLE IF NOT EXISTS `test_brp`.`forum` (
  `forum_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NULL DEFAULT NULL,
  `message` MEDIUMTEXT NULL DEFAULT NULL,
  `etat` INT(11) NULL DEFAULT NULL,
  `book_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`forum_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `forum_book_fk_idx` ON `test_brp`.`forum` (`book_id` ASC);


-- -----------------------------------------------------
-- Table `test_brp`.`payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_brp`.`payment` ;

CREATE TABLE IF NOT EXISTS `test_brp`.`payment` (
  `payment_id` INT(11) NOT NULL AUTO_INCREMENT,
  `amount` FLOAT NOT NULL,
  `payment_date` VARCHAR(45) NOT NULL,
  `rental_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `cb_id` INT(11) NOT NULL,
  `commission` FLOAT NOT NULL,
  PRIMARY KEY (`payment_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `payment_cb_fk_idx` ON `test_brp`.`payment` (`cb_id` ASC);

CREATE INDEX `payment_user_fk_idx` ON `test_brp`.`payment` (`user_id` ASC);

CREATE INDEX `payment_rental_fk_idx` ON `test_brp`.`payment` (`rental_id` ASC);


-- -----------------------------------------------------
-- Table `test_brp`.`photo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_brp`.`photo` ;

CREATE TABLE IF NOT EXISTS `test_brp`.`photo` (
  `photo_id` INT(11) NOT NULL AUTO_INCREMENT,
  `photo` BLOB NOT NULL,
  `photo_role` VARCHAR(45) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`photo_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `photo_users_fk_idx` ON `test_brp`.`photo` (`user_id` ASC);


-- -----------------------------------------------------
-- Table `test_brp`.`users_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test_brp`.`users_roles` ;

CREATE TABLE IF NOT EXISTS `test_brp`.`users_roles` (
  `role_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_role` VARCHAR(45) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `user_role_fk_idx` ON `test_brp`.`users_roles` (`user_id` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
