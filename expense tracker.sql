-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: expense_tracker
-- Source Schemata: expense_tracker
-- Created: Sun Jul 31 15:44:48 2022
-- Workbench Version: 8.0.18
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema expense_tracker
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `expense_tracker` ;
CREATE SCHEMA IF NOT EXISTS `expense_tracker` ;

-- ----------------------------------------------------------------------------
-- Table expense_tracker.category
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `expense_tracker`.`category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table expense_tracker.expense
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `expense_tracker`.`expense` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `expense_date` DATE NULL DEFAULT NULL,
  `price` DOUBLE NOT NULL,
  `category_id` BIGINT(20) NULL DEFAULT NULL,
  `user_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKmvjm59reb5i075vu38bwcaqj6` (`category_id` ASC) VISIBLE,
  INDEX `FKekyts7i8w5cam119wj1itdom2` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKekyts7i8w5cam119wj1itdom2`
    FOREIGN KEY (`user_id`)
    REFERENCES `expense_tracker`.`users` (`id`),
  CONSTRAINT `FKmvjm59reb5i075vu38bwcaqj6`
    FOREIGN KEY (`category_id`)
    REFERENCES `expense_tracker`.`category` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table expense_tracker.roles
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `expense_tracker`.`roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table expense_tracker.user_roles
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `expense_tracker`.`user_roles` (
  `user_id` BIGINT(20) NOT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id` ASC) VISIBLE,
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6`
    FOREIGN KEY (`role_id`)
    REFERENCES `expense_tracker`.`roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f`
    FOREIGN KEY (`user_id`)
    REFERENCES `expense_tracker`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------------------------------------------------------
-- Table expense_tracker.users
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `expense_tracker`.`users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `is_enabled` BIT(1) NOT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `reset_password_token` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  `verification_code` VARCHAR(64) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK6dotkott2kjsp8vw4d0m25fb7` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
SET FOREIGN_KEY_CHECKS = 1;
