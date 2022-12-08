-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema movie_reservation
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema movie_reservation
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `movie_reservation` DEFAULT CHARACTER SET utf8mb3 ;
USE `movie_reservation` ;

-- -----------------------------------------------------
-- Table `movie_reservation`.`movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_reservation`.`movie` (
  `movie_id` BINARY(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `desc` VARCHAR(200) NOT NULL DEFAULT '',
  `running_time` TIME NULL DEFAULT NULL,
  `title` VARCHAR(45) NULL DEFAULT NULL,
  `poster_image_url` VARCHAR(255) NULL DEFAULT NULL,
  `release_date` DATE NULL DEFAULT NULL,
  `status` TINYINT NULL DEFAULT '1',
  PRIMARY KEY (`movie_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `movie_reservation`.`theater`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_reservation`.`theater` (
  `theater_id` BINARY(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `theater_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`theater_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `movie_reservation`.`screen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_reservation`.`screen` (
  `screen_id` BINARY(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `screen_name` VARCHAR(45) NULL DEFAULT NULL,
  `theater_theater_id` BINARY(16) NOT NULL,
  PRIMARY KEY (`screen_id`),
  INDEX `fk_screen_Theater1_idx` (`theater_theater_id` ASC) VISIBLE,
  CONSTRAINT `fk_screen_Theater1`
    FOREIGN KEY (`theater_theater_id`)
    REFERENCES `movie_reservation`.`theater` (`theater_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `movie_reservation`.`movie_schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_reservation`.`movie_schedule` (
  `movie_schedule_id` BINARY(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `start_time` DATETIME NULL DEFAULT NULL,
  `movie_movie_id` BINARY(16) NOT NULL,
  `screen_screen_id` BINARY(16) NOT NULL,
  PRIMARY KEY (`movie_schedule_id`),
  INDEX `fk_movie_schedule_movie1_idx` (`movie_movie_id` ASC) VISIBLE,
  INDEX `fk_movie_schedule_screen1_idx` (`screen_screen_id` ASC) VISIBLE,
  CONSTRAINT `fk_movie_schedule_movie1`
    FOREIGN KEY (`movie_movie_id`)
    REFERENCES `movie_reservation`.`movie` (`movie_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_movie_schedule_screen1`
    FOREIGN KEY (`screen_screen_id`)
    REFERENCES `movie_reservation`.`screen` (`screen_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `movie_reservation`.`movie_ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_reservation`.`movie_ticket` (
  `movie_ticket_id` BINARY(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `price` BIGINT NULL DEFAULT NULL,
  `reservation_time` DATETIME NULL DEFAULT now(),
  `seat_count` BIGINT NOT NULL DEFAULT '0',
  `user_phone_number` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`movie_ticket_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `movie_reservation`.`seat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_reservation`.`seat` (
  `seat_id` BINARY(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `seat_row` VARCHAR(1) NULL DEFAULT NULL,
  `seat_col` INT NULL DEFAULT NULL,
  `screen_screen_id` BINARY(16) NOT NULL,
  PRIMARY KEY (`seat_id`),
  INDEX `fk_seat_screen1_idx` (`screen_screen_id` ASC) VISIBLE,
  CONSTRAINT `fk_seat_screen1`
    FOREIGN KEY (`screen_screen_id`)
    REFERENCES `movie_reservation`.`screen` (`screen_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `movie_reservation`.`reserved_seat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_reservation`.`reserved_seat` (
  `reserved_seat_id` BINARY(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `movie_ticket_movie_ticket_id` BINARY(16) NOT NULL,
  `movie_schedule_movie_schedule_id` BINARY(16) NOT NULL,
  `seat_seat_id` BINARY(16) NOT NULL,
  PRIMARY KEY (`reserved_seat_id`),
  INDEX `fk_reserved_seat_movie_ticket1_idx` (`movie_ticket_movie_ticket_id` ASC) VISIBLE,
  INDEX `fk_reserved_seat_movie_schedule1_idx` (`movie_schedule_movie_schedule_id` ASC) VISIBLE,
  INDEX `fk_reserved_seat_seat1_idx` (`seat_seat_id` ASC) VISIBLE,
  CONSTRAINT `fk_reserved_seat_movie_schedule1`
    FOREIGN KEY (`movie_schedule_movie_schedule_id`)
    REFERENCES `movie_reservation`.`movie_schedule` (`movie_schedule_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reserved_seat_movie_ticket1`
    FOREIGN KEY (`movie_ticket_movie_ticket_id`)
    REFERENCES `movie_reservation`.`movie_ticket` (`movie_ticket_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reserved_seat_seat1`
    FOREIGN KEY (`seat_seat_id`)
    REFERENCES `movie_reservation`.`seat` (`seat_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

USE `movie_reservation` ;

-- -----------------------------------------------------
-- procedure createTheater
-- -----------------------------------------------------

DELIMITER $$
USE `movie_reservation`$$
CREATE DEFINER=`root`@`%` PROCEDURE `createTheater`(in theaterName varchar(45) ,in numberOfScreen INT)
BEGIN
DECLARE i INT DEFAULT 1;
	insert into movie_reservation.theater(theater_name) values(theaterName);
	WHILE (i <= numberOfScreen) DO
    insert into movie_reservation.screen(screen_name,theater_theater_id) select concat(i), theater_id from movie_reservation.theater where theater_name=theaterName;
    call insertSeat(concat(i));
	SET i = i + 1; -- ⓔ i값에 1더해주고 WHILE문 처음으로 이동
    END WHILE;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertSeat
-- -----------------------------------------------------

DELIMITER $$
USE `movie_reservation`$$
CREATE DEFINER=`root`@`%` PROCEDURE `insertSeat`(IN screenName varchar(2))
BEGIN
DECLARE i INT DEFAULT 1; -- ⓑ i변수 선언, defalt값으로 1설정
    WHILE (i <= 10) DO -- ⓒ for문 작성(i가 1000이 될 때까지 반복)
        INSERT INTO movie_reservation.seat(seat_row,seat_col,screen_screen_id) select 'a',i,screen_id from movie_reservation.screen where screen_name=screenName;
        INSERT INTO movie_reservation.seat(seat_row,seat_col,screen_screen_id) select 'b',i,screen_id from movie_reservation.screen where screen_name=screenName;
        INSERT INTO movie_reservation.seat(seat_row,seat_col,screen_screen_id) select 'c',i,screen_id from movie_reservation.screen where screen_name=screenName;
        INSERT INTO movie_reservation.seat(seat_row,seat_col,screen_screen_id) select 'd',i,screen_id from movie_reservation.screen where screen_name=screenName;
        INSERT INTO movie_reservation.seat(seat_row,seat_col,screen_screen_id) select 'e',i,screen_id from movie_reservation.screen where screen_name=screenName;
        INSERT INTO movie_reservation.seat(seat_row,seat_col,screen_screen_id) select 'f',i,screen_id from movie_reservation.screen where screen_name=screenName;
        SET i = i + 1; -- ⓔ i값에 1더해주고 WHILE문 처음으로 이동
    END WHILE;
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

# dockerfile로 image화 할 경우 실행 아래의 sql 문들이 되지 않는다.
# insert into movie_reservation.movie (title,`desc`,running_time,poster_image_url,release_date)
# values(
# '올빼미',
# '맹인이지만 뛰어난 침술 실력을 지닌 \‘경수\’는
# 어의 \‘이형익\’에게 그 재주를 인정받아 궁으로 들어간다.
# 그 무렵, 청에 인질로 끌려갔던 \‘소현세자\’가 8년 만에 귀국하고,
# \‘인조\’는 아들을 향한 반가움도 잠시 정체 모를 불안감에 휩싸인다.',
# SEC_TO_TIME(118*60),
# 'https://img.megabox.co.kr/SharedImg/2022/11/24/xFO8r2xbXzxoMD9iXbuKC1n5Bo79InhQ_420.jpg',
# '2022-11-23'
# );
# call createTheater('부천점',3);
