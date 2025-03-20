-- MySQL Script modified to remove country relationships
-- Sun Jan 5 21:23:00 2025
-- Modified by Amle

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema portalpeliculas
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `portalpeliculas`;

-- -----------------------------------------------------
-- Schema portalpeliculas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `portalpeliculas` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `portalpeliculas`;

-- -----------------------------------------------------
-- Table `portalpeliculas`.`peliculas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `portalpeliculas`.`peliculas`;

CREATE TABLE IF NOT EXISTS `portalpeliculas`.`peliculas` (
  `idPelicula` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(255) NOT NULL,
  `annio` INT NOT NULL,
  `direccion` VARCHAR(255) NOT NULL,
  `genero` VARCHAR(100) NOT NULL,
  `sinopsis` TEXT NOT NULL,
  `duracion` INT NOT NULL CHECK (`duracion` >= 0),
  `imagen_Portada` VARCHAR(255),
  `pais` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idPelicula`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `portalpeliculas`.`actores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `portalpeliculas`.`actores`;

CREATE TABLE IF NOT EXISTS `portalpeliculas`.`actores` (
  `idActor` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `fecha_nacimiento` DATE NULL,
  `pais` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idActor`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `portalpeliculas`.`reparto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `portalpeliculas`.`reparto`;

CREATE TABLE IF NOT EXISTS `portalpeliculas`.`reparto` (
  `idPelicula` INT NOT NULL,
  `idActor` INT NOT NULL,
  PRIMARY KEY (`idPelicula`, `idActor`),
  CONSTRAINT `idPelicula`
    FOREIGN KEY (`idPelicula`)
    REFERENCES `portalpeliculas`.`peliculas` (`idPelicula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idActor`
    FOREIGN KEY (`idActor`)
    REFERENCES `portalpeliculas`.`actores` (`idActor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;