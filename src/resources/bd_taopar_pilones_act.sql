-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.5.8-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para db_taopar_pilones
CREATE DATABASE IF NOT EXISTS `db_taopar_pilones` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_taopar_pilones`;

-- Volcando estructura para tabla db_taopar_pilones.clase_tabaco
CREATE TABLE IF NOT EXISTS `clase_tabaco` (
  `id_tabaco` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_tabaco` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_tabaco`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla db_taopar_pilones.control_temperatura
CREATE TABLE IF NOT EXISTS `control_temperatura` (
  `id_temperatura` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_pilones` int(11) NOT NULL DEFAULT 0,
  `temperatura` int(11) NOT NULL DEFAULT 0,
  `fecha_revision` date NOT NULL DEFAULT '0000-00-00',
  `mantenimiento` varchar(20) NOT NULL,
  PRIMARY KEY (`id_temperatura`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla db_taopar_pilones.entrada_pilones
CREATE TABLE IF NOT EXISTS `entrada_pilones` (
  `id_entrada_pilones` int(11) NOT NULL AUTO_INCREMENT,
  `Id_tabaco` int(11) NOT NULL DEFAULT 0,
  `id_pilon` int(11) NOT NULL DEFAULT 0,
  `fecha_entrada_pilon` date NOT NULL DEFAULT '0000-00-00',
  `tiempo_adelanto_pilon` varchar(50) NOT NULL DEFAULT '0',
  `fecha_estimada_salida` date NOT NULL DEFAULT '0000-00-00',
  `cantidad_lbs` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_entrada_pilones`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para procedimiento db_taopar_pilones.insertar_control_temp
DELIMITER //
CREATE PROCEDURE `insertar_control_temp`(
	IN `pa_id_pilones` INT,
	IN `pa_temperatura` INT,
	IN `pa_fecha_revision` DATE,
	IN `pa_mantenimiento` VARCHAR(30)
)
BEGIN
  
     INSERT INTO control_temperatura ( id_pilones, temperatura, fecha_revision, mantenimiento) VALUES (pa_id_pilones,
                                       pa_temperatura, pa_fecha_revision, pa_mantenimiento);
  
  SELECT "Guardado correctamente",1;

  
END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.insertar_entrada_pilon
DELIMITER //
CREATE PROCEDURE `insertar_entrada_pilon`(
	IN `pa_id_entrada_pilon` INT,
	IN `pa_id_tabaco_entrada` INT,
	IN `pa_id_pilon_entrada` INT,
	IN `pa_fecha_entrada_pilon` DATE,
	IN `pa_tiempo_adelanto_entrada` VARCHAR(50),
	IN `pa_fecha_estimada_salida` DATE,
	IN `pa_cantidad_lbs_entrada` VARCHAR(50)
)
BEGIN

   INSERT INTO pilones(numero_pilon, id_tabaco) VALUES (pa_id_entrada_pilon,pa_id_tabaco_entrada,pa_id_pilon_entrada,pa_fecha_entrada_pilon,
	pa_tiempo_adelanto_entrada,pa_fecha_estimada_salida,pa_cantidad_lbs_entrada);
   SELECT 'Guardado correctamente ',1;
   
END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.insertar_pilones
DELIMITER //
CREATE PROCEDURE `insertar_pilones`(
	IN `pa_numero_pilon` INT
)
BEGIN
  if EXISTS (SELECT * FROM pilones WHERE numero_pilon = pa_numero_pilon) then
   SELECT 'No se puede repetir el numero de pilon ',0;
   else
   
   INSERT INTO pilones(numero_pilon) VALUES (pa_numero_pilon);
   SELECT 'Guardado correctamente ',1;
   
   END IF;
END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.insertar_remision_proceso
DELIMITER //
CREATE PROCEDURE `insertar_remision_proceso`(
	IN `pa_id_remision_proceso` BIGINT,
	IN `pa_id_remision` INT,
	IN `pa_fecha_remision` DATE,
	IN `pa_destino_remision` VARCHAR(50),
	IN `pa_origen_remision` VARCHAR(50),
	IN `pa_descripcion1_remision` VARCHAR(100),
	IN `pa_descripcion2_remision` VARCHAR(100),
	IN `pa_descripcion3_remision` VARCHAR(100),
	IN `pa_descripcion4_remision` VARCHAR(100),
	IN `pa_descripcion5_remision` VARCHAR(100),
	IN `pa_cantidad_lbs_remision` DECIMAL(10,2),
	IN `pa_total_remision` DECIMAL(10,2)
)
BEGIN
  if EXISTS ( SELECT * FROM remision_proceso WHERE id_remision = pa_id_remision) then 
  SELECT 'no se puede';
  
  else
     INSERT INTO remision_proceso ( id_remision_proceso,id_remision,fecha_remision, destino_remision, origen_remision, descripcion1_remision,
	   descripcion2_remision, descripcion3_remision,descripcion4_remision,descripcion5_remision, cantidad_lbs_remision,total_remision) 
	  VALUES (pa_id_remision_proceso,pa_id_remision,pa_fecha_remision,pa_destino_remision,pa_origen_remision,pa_descripcion1_remision,
	   pa_descripcion2_remision,pa_descripcion3_remision,pa_descripcion4_remision,pa_descripcion5_remision,pa_cantidad_lbs_remision,
		pa_total_remision);
  
  END if;
END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.insertar_tabaco
DELIMITER //
CREATE PROCEDURE `insertar_tabaco`(
	IN `pa_nombre` VARCHAR(100)
)
BEGIN

	if EXISTS (SELECT * from clase_tabaco WHERE nombre_tabaco = pa_nombre) then 
			SELECT 'No se puede repetir el nombre/n de la clase de tabaco',0;
	else
			INSERT INTO clase_tabaco (nombre_tabaco) VALUES(pa_nombre);
			SELECT 'Guardado correctamente',1;
	end IF;

END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.insertar_tabla_pilon
DELIMITER //
CREATE PROCEDURE `insertar_tabla_pilon`(
	IN `pa_id_tabla_pilon` BIGINT,
	IN `pa_fecha_proceso` DATE,
	IN `pa_id_remision` INT,
	IN `pa_entradas_salidas` VARCHAR(30),
	IN `pa_id_tabaco` INT,
	IN `pa_id_pilon` INT,
	IN `pa_subtotal` DECIMAL(10,2),
	IN `pa_total_libras` DECIMAL(10,2)
)
BEGIN
  if EXISTS ( SELECT * FROM tabla_pilon WHERE id_remision = pa_id_remision) then 
  SELECT 'no se puede';
  
  else
     INSERT INTO tabla_pilon ( id_tabla_pilon,fecha_proceso,id_remision, entradas_salidas, id_tabaco, id_pilon, subtotal, total_libras) 
	  VALUES (pa_id_tabla_pilon,pa_fecha_proceso,pa_id_remision,pa_entradas_salidas,pa_id_tabaco,pa_id_pilon,pa_subtotal,pa_total_libras );
  
  END if;
END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.insertar_tabla_procesos
DELIMITER //
CREATE PROCEDURE `insertar_tabla_procesos`(
	IN `pa_id_tabla_proceso` BIGINT,
	IN `pa_fecha_proceso` DATE,
	IN `pa_id_remision` INT,
	IN `pa_entradas_salidas` VARCHAR(30),
	IN `pa_id_tabaco` INT,
	IN `pa_id_pilon` INT,
	IN `pa_subtotal` DECIMAL(10,2),
	IN `pa_total_libras` DECIMAL(10,2)
)
BEGIN
  if EXISTS ( SELECT * FROM tabla_procesos WHERE id_remision = pa_id_remision) then 
  SELECT 'no se puede';
  
  else
     INSERT INTO tabla_procesos ( id_tabla_proceso,fecha_proceso,id_remision, entradas_salidas, id_tabaco, id_pilon, subtotal, total_libras) 
	  VALUES (pa_id_tabla_proceso,pa_fecha_proceso,pa_id_remision,pa_entradas_salidas,pa_id_tabaco,pa_id_pilon,pa_subtotal,pa_total_libras );
  
  END if;
END//
DELIMITER ;

-- Volcando estructura para tabla db_taopar_pilones.pilones
CREATE TABLE IF NOT EXISTS `pilones` (
  `id_pilon` bigint(20) NOT NULL AUTO_INCREMENT,
  `numero_pilon` int(11) NOT NULL,
  PRIMARY KEY (`id_pilon`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla db_taopar_pilones.remision_proceso
CREATE TABLE IF NOT EXISTS `remision_proceso` (
  `id_remision_proceso` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_remision` int(11) NOT NULL DEFAULT 0,
  `fecha_remision` date NOT NULL DEFAULT '0000-00-00',
  `destino_remision` varchar(20) NOT NULL DEFAULT '0',
  `origen_remision` varchar(20) NOT NULL DEFAULT '0',
  `descripcion1_remision` varchar(100) NOT NULL DEFAULT '0',
  `descripcion2_remision` varchar(100) DEFAULT '0',
  `descripcion3_remision` varchar(100) DEFAULT '0',
  `descripcion4_remision` varchar(100) DEFAULT '0',
  `descripcion5_remision` varchar(100) DEFAULT '0',
  `cantidad_lbs_remision` decimal(10,2) NOT NULL DEFAULT 0.00,
  `total_remision` decimal(10,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id_remision_proceso`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla db_taopar_pilones.tabla_pilon
CREATE TABLE IF NOT EXISTS `tabla_pilon` (
  `id_tabla_pilon` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_proceso` date NOT NULL,
  `id_remision` int(11) NOT NULL DEFAULT 0,
  `entradas_salidas` varchar(30) NOT NULL DEFAULT '0',
  `id_tabaco` int(11) NOT NULL DEFAULT 0,
  `id_pilon` int(11) NOT NULL DEFAULT 0,
  `subtotal` decimal(10,2) NOT NULL DEFAULT 0.00,
  `total_libras` decimal(10,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id_tabla_pilon`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla db_taopar_pilones.tabla_procesos
CREATE TABLE IF NOT EXISTS `tabla_procesos` (
  `id_tabla_proceso` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_proceso` date NOT NULL,
  `id_remision` int(11) NOT NULL DEFAULT 0,
  `entradas_salidas` varchar(30) NOT NULL DEFAULT '0',
  `id_tabaco` int(11) NOT NULL DEFAULT 0,
  `id_pilon` int(11) NOT NULL DEFAULT 0,
  `subtotal` decimal(10,2) NOT NULL DEFAULT 0.00,
  `total_libras` decimal(10,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id_tabla_proceso`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
