-- MySQL Script modified to remove country relationships
-- Wed Feb 5 20:22:00 2025
-- Modified by Amle
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema portalpeliculasusuario
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `portalpeliculasusuario`;
CREATE SCHEMA IF NOT EXISTS `portalpeliculasusuario` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `portalpeliculasusuario`;

-- -----------------------------------------------------
-- Table `PortalPeliculasUsuario`.`authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PortalPeliculasUsuario`.`authorities`;
CREATE TABLE IF NOT EXISTS `PortalPeliculasUsuario`.`authorities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `authority` VARCHAR(50) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `PortalPeliculasUsuario`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PortalPeliculasUsuario`.`users`;
CREATE TABLE IF NOT EXISTS `PortalPeliculasUsuario`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL UNIQUE,
  `email` VARCHAR(100) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `enabled` BOOLEAN NOT NULL DEFAULT TRUE,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `authority_id` INT NOT NULL,  -- Clave foránea
  
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_authority_id`
    FOREIGN KEY (`authority_id`)
    REFERENCES `PortalPeliculasUsuario`.`authorities` (`id`)
    ON DELETE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Schema portalpeliculasusuario
-- -----------------------------------------------------
USE `portalpeliculasusuario`;

-- -----------------------------------------------------
-- Table `PortalPeliculasUsuario`.`criticas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PortalPeliculasUsuario`.`criticas`;
CREATE TABLE IF NOT EXISTS `PortalPeliculasUsuario`.`criticas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `peliculaId` INT NOT NULL,
  `userId` INT NOT NULL,
  `valoracion` TEXT NOT NULL,
  `nota` INT NOT NULL CHECK (`nota` BETWEEN 1 AND 10),
  `fecha` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`userId`)
    REFERENCES `PortalPeliculasUsuario`.`users` (`id`)
    ON DELETE CASCADE
) ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
