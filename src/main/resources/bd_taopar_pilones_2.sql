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

-- Volcando estructura para procedimiento db_taopar_pilones.actualizar_control_pilones
DELIMITER //
CREATE PROCEDURE `actualizar_control_pilones`(
	IN `pa_id_control_pilones` BIGINT,
	IN `pa_nombre_tabaco` VARCHAR(50),
	IN `pa_variedad_tabaco` VARCHAR(50),
	IN `pa_finca` VARCHAR(50),
	IN `pa_fecha_entrada_pilon` DATE,
	IN `pa_numero_pilon` VARCHAR(50),
	IN `pa_entrada_tabaco_pilon` VARCHAR(50),
	IN `pa_salida_tabaco_pilon` VARCHAR(50),
	IN `pa_total_actual` DECIMAL(10,2),
	IN `pa_Total` DECIMAL(10,2)
)
BEGIN

if EXISTS (SELECT * FROM control_pilones WHERE control_pilones.id_control_pilones = pa_id_control_pilones
AND control_pilones.nombre_tabaco = pa_nombre_tabaco AND control_pilones.numero_pilon = pa_numero_pilon)
   then 
	UPDATE control_pilones SET nombre_tabaco = pa_nombre_tabaco, variedad_tabaco = pa_variedad_tabaco, finca = pa_finca,
	fecha_entrada_pilon = pa_fecha_entrada_pilon, numero_pilon = pa_numero_pilon, entrada_tabaco_pilon = pa_entrada_tabaco_pilon,
	 salida_tabaco_pilon = pa_salida_tabaco_pilon,total_actual = pa_total_actual, Total = pa_Total
	  WHERE control_pilones.id_control_pilones = pa_id_control_pilones;
   	SELECT 'Actualizado correctamente',1;
   ELSE 
   		if EXISTS (SELECT * FROM control_pilones WHERE control_pilones.id_control_pilones = pa_id_control_pilones
AND control_pilones.nombre_tabaco = pa_nombre_tabaco AND control_pilones.numero_pilon = pa_numero_pilon)
           then
           SELECT 'Registro de proceso ya existe',0;
           
      ELSE 
     	UPDATE control_pilones SET nombre_tabaco = pa_nombre_tabaco, variedad_tabaco = pa_variedad_tabaco, finca = pa_finca,
	fecha_entrada_pilon = pa_fecha_entrada_pilon, numero_pilon = pa_numero_pilon, entrada_tabaco_pilon = pa_entrada_tabaco_pilon,
	 salida_tabaco_pilon = pa_salida_tabaco_pilon,total_actual = pa_total_actual, Total = pa_Total
	  WHERE control_pilones.id_control_pilones = pa_id_control_pilones;
   	SELECT 'Actualizado correctamente',1;
   END if;
   END if;

END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.actualizar_entrada_pilones
DELIMITER //
CREATE PROCEDURE `actualizar_entrada_pilones`(
	IN `pa_id_entrada_pilones` INT,
	IN `pa_nombre_tabaco` VARCHAR(50),
	IN `pa_numero_pilon` VARCHAR(50),
	IN `pa_fecha_entrada_pilon` DATE,
	IN `pa_tiempo_adelanto_pilon` VARCHAR(50),
	IN `pa_fecha_estimada_salida` DATE,
	IN `pa_cantidad_lbs` DECIMAL(10,2)
)
BEGIN
if EXISTS (SELECT * FROM entrada_pilones WHERE entrada_pilones.id_entrada_pilones = pa_id_entrada_pilones 
AND entrada_pilones.nombre_tabaco = pa_nombre_tabaco)
   then 
	UPDATE entrada_pilones SET nombre_tabaco = pa_nombre_tabaco,numero_pilon = pa_numero_pilon,fecha_entrada_pilon = pa_fecha_entrada_pilon,
	tiempo_adelanto_pilon = pa_tiempo_adelanto_pilon,fecha_estimada_salida = pa_fecha_estimada_salida,cantidad_lbs = pa_cantidad_lbs
   	
   	WHERE entrada_pilones.id_entrada_pilones = pa_id_entrada_pilones;
   	SELECT 'Actualizado correctamente',1;
   ELSE 
   		if EXISTS (SELECT * FROM entrada_pilones WHERE entrada_pilones.id_entrada_pilones != pa_id_entrada_pilones 
			AND entrada_pilones.nombre_tabaco = pa_nombre_tabaco)
           then
           SELECT 'Entrada de pilón ya existe',0;
           
      ELSE 
     	UPDATE entrada_pilones SET nombre_tabaco = pa_nombre_tabaco,numero_pilon = pa_numero_pilon,fecha_entrada_pilon = pa_fecha_entrada_pilon,
		tiempo_adelanto_pilon = pa_tiempo_adelanto_pilon,fecha_estimada_salida = pa_fecha_estimada_salida,cantidad_lbs = pa_cantidad_lbs

   	WHERE entrada_pilones.id_entrada_pilones = pa_id_entrada_pilones;
   	SELECT 'Actualizado correctamente',1;
   END if;
   END if;


END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.actualizar_pilon
DELIMITER //
CREATE PROCEDURE `actualizar_pilon`(
	IN `pa_id_pilon` INT,
	IN `pa_numero_pilon` INT
)
BEGIN
if EXISTS (SELECT * FROM pilones WHERE pilones.id_pilon = pa_id_pilon AND pilones.numero_pilon = pa_numero_pilon)
   then 
	UPDATE pilones SET 
   	pilones.numero_pilon = pa_numero_pilon
   	
   	WHERE pilones.id_pilon = pa_id_pilon;
   	SELECT 'Actualizado correctamente',1;
   ELSE 
   		if EXISTS (SELECT * FROM pilones WHERE pilones.id_pilon != pa_id_pilon AND pilones.numero_pilon = pa_numero_pilon)
           then
           SELECT 'Nombre ya existe',0;
           
      ELSE 
           	UPDATE pilones SET 
   	pilones.numero_pilon = pa_numero_pilon
   	
   	WHERE pilones.id_pilon = pa_id_pilon;
   	SELECT 'Actualizado correctamente',1;
   END if;
   END if;
END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.actualizar_tabaco
DELIMITER //
CREATE PROCEDURE `actualizar_tabaco`(
	IN `pa_id_tabaco` INT,
	IN `pa_nombre_tabaco` VARCHAR(100)
)
BEGIN
  if EXISTS (SELECT * FROM clase_tabaco WHERE clase_tabaco.id_tabaco = pa_id_tabaco AND clase_tabaco.nombre_tabaco = pa_nombre_tabaco)
   then 
	UPDATE clase_tabaco SET 
   	clase_tabaco.nombre_tabaco = pa_nombre_tabaco
   	
   	WHERE clase_tabaco.id_tabaco = pa_id_tabaco;
   	SELECT 'Actualizado correctamente',1;
   ELSE 
   		if EXISTS (SELECT * FROM clase_tabaco WHERE clase_tabaco.id_tabaco != pa_id_tabaco AND clase_tabaco.nombre_tabaco = pa_nombre_tabaco)
           then
           SELECT 'Nombre ya existe',0;
           
      ELSE 
           	UPDATE clase_tabaco SET 
   	clase_tabaco.nombre_tabaco = pa_nombre_tabaco
   	
   	WHERE clase_tabaco.id_tabaco = pa_id_tabaco;
   	SELECT 'Actualizado correctamente',1;
   END if;
   END if;
END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.actualizar_tabla_pilon
DELIMITER //
CREATE PROCEDURE `actualizar_tabla_pilon`(
	IN `pa_id_tabla_pilon` BIGINT,
	IN `pa_fecha_proceso` DATE,
	IN `pa_id_remision` INT,
	IN `pa_entradas_salidas` VARCHAR(50),
	IN `pa_nombre_tabaco` VARCHAR(50),
	IN `pa_numero_pilon` VARCHAR(50),
	IN `pa_subtotal` DECIMAL(10,2),
	IN `pa_total_libras` DECIMAL(10,2),
	IN `pa_total_remision` DECIMAL(10,2)
)
BEGIN
if EXISTS (SELECT * FROM tabla_pilon WHERE tabla_pilon.id_tabla_pilon = pa_id_tabla_pilon 
AND tabla_pilon.nombre_tabaco = pa_nombre_tabaco AND tabla_pilon.numero_pilon = pa_numero_pilon)
   then 
	UPDATE tabla_pilon SET fecha_proceso = pa_fecha_proceso, id_remision = pa_id_remision, entradas_salidas = pa_entradas_salidas,
	nombre_tabaco = pa_nombre_tabaco, numero_pilon = pa_numero_pilon, subtotal = pa_subtotal, total_libras = pa_total_libras,
	total_remision=pa_total_remision WHERE tabla_pilon.id_tabla_pilon = pa_id_tabla_pilon;
   	SELECT 'Actualizado correctamente',1;
   ELSE 
   		if EXISTS (SELECT * FROM tabla_pilon WHERE tabla_pilon.id_tabla_pilon != pa_id_tabla_pilon 
			AND tabla_pilon.nombre_tabaco = pa_nombre_tabaco AND tabla_pilon.numero_pilon = pa_numero_pilon)
           then
           SELECT 'Registro de proceso ya existe',0;
           
      ELSE 
     	UPDATE tabla_pilon SET fecha_proceso = pa_fecha_proceso, id_remision = pa_id_remision, entradas_salidas = pa_entradas_salidas,
	nombre_tabaco = pa_nombre_tabaco, numero_pilon = pa_numero_pilon, subtotal = pa_subtotal, total_libras = pa_total_libras,
	total_remision=pa_total_remision WHERE tabla_pilon.id_tabla_pilon = pa_id_tabla_pilon;
   	SELECT 'Actualizado correctamente',1;
   END if;
   END if;

END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.actualizar_tabla_proceso
DELIMITER //
CREATE PROCEDURE `actualizar_tabla_proceso`(
	IN `pa_id_tabla_proceso` BIGINT,
	IN `pa_fecha_proceso` DATE,
	IN `pa_id_remision` INT,
	IN `pa_entradas_salidas` VARCHAR(50),
	IN `pa_nombre_tabaco` VARCHAR(50),
	IN `pa_numero_pilon` VARCHAR(50),
	IN `pa_subtotal` DECIMAL(10,2),
	IN `pa_total_libras` DECIMAL(10,2),
	IN `pa_total_remision` DECIMAL(10,2)
)
BEGIN
if EXISTS (SELECT * FROM tabla_procesos WHERE tabla_procesos.id_tabla_proceso = pa_id_tabla_proceso 
AND tabla_procesos.nombre_tabaco = pa_nombre_tabaco AND tabla_procesos.numero_pilon = pa_numero_pilon)
   then 
	UPDATE tabla_procesos SET fecha_proceso = pa_fecha_proceso, id_remision = pa_id_remision, entradas_salidas = pa_entradas_salidas,
	nombre_tabaco = pa_nombre_tabaco, numero_pilon = pa_numero_pilon, subtotal = pa_subtotal, total_libras = pa_total_libras,
	total_remision=pa_total_remision WHERE tabla_procesos.id_tabla_proceso = pa_id_tabla_proceso;
   	SELECT 'Actualizado correctamente',1;
   ELSE 
   		if EXISTS (SELECT * FROM tabla_procesos WHERE tabla_procesos.id_tabla_proceso != pa_id_tabla_proceso 
			AND tabla_procesos.nombre_tabaco = pa_nombre_tabaco AND tabla_procesos.numero_pilon = pa_numero_pilon)
           then
           SELECT 'Registro de proceso ya existe',0;
           
      ELSE 
     	UPDATE tabla_procesos SET fecha_proceso = pa_fecha_proceso, id_remision = pa_id_remision, entradas_salidas = pa_entradas_salidas,
		nombre_tabaco = pa_nombre_tabaco, numero_pilon = pa_numero_pilon, subtotal = pa_subtotal, total_libras = pa_total_libras,
		total_remision=pa_total_remision WHERE tabla_procesos.id_tabla_proceso = pa_id_tabla_proceso;
   	SELECT 'Actualizado correctamente',1;
   END if;
   END if;


END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.buscar_control_pilones
DELIMITER //
CREATE PROCEDURE `buscar_control_pilones`(
	IN `pa_valor` VARCHAR(50)
)
BEGIN
SELECT * FROM control_pilones WHERE CONCAT(nombre_tabaco,variedad_tabaco,finca,numero_pilon) LIKE CONCAT("%",pa_valor,"%");
END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.buscar_entradas_pilon
DELIMITER //
CREATE PROCEDURE `buscar_entradas_pilon`(
	IN `pa_nombre_tabaco` VARCHAR(50)
)
BEGIN
SELECT * FROM entrada_pilones WHERE CONCAT(nombre_tabaco,numero_pilon) LIKE CONCAT("%",pa_nombre_tabaco,"%");

END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.Buscar_pilones
DELIMITER //
CREATE PROCEDURE `Buscar_pilones`(
	IN `pa_numero_pilon` VARCHAR(50)
)
BEGIN

SELECT * FROM pilones WHERE numero_pilon LIKE CONCAT("%",pa_numero_pilon,"%");
END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.buscar_tabaco
DELIMITER //
CREATE PROCEDURE `buscar_tabaco`(
	IN `pa_nombre_tabaco` VARCHAR(50)
)
BEGIN

SELECT * FROM clase_tabaco WHERE nombre_tabaco LIKE CONCAT("%",pa_nombre_tabaco,"%");

END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.buscar_tabla_pilon
DELIMITER //
CREATE PROCEDURE `buscar_tabla_pilon`(
	IN `pa_valor` VARCHAR(50)
)
BEGIN
SELECT * FROM tabla_pilon WHERE CONCAT(id_remision,entradas_salidas,nombre_tabaco,numero_pilon) LIKE CONCAT("%",pa_valor,"%");
END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.buscar_tabla_proceso
DELIMITER //
CREATE PROCEDURE `buscar_tabla_proceso`(
	IN `pa_valor` VARCHAR(50)
)
BEGIN
SELECT * FROM tabla_procesos WHERE CONCAT(id_remision,entradas_salidas,nombre_tabaco,numero_pilon) LIKE CONCAT("%",pa_valor,"%");
END//
DELIMITER ;

-- Volcando estructura para tabla db_taopar_pilones.clase_tabaco
CREATE TABLE IF NOT EXISTS `clase_tabaco` (
  `id_tabaco` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_tabaco` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_tabaco`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla db_taopar_pilones.control_pilones
CREATE TABLE IF NOT EXISTS `control_pilones` (
  `id_control_pilones` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre_tabaco` varchar(100) NOT NULL DEFAULT '',
  `variedad_tabaco` varchar(100) NOT NULL DEFAULT '',
  `finca` varchar(100) NOT NULL DEFAULT '',
  `fecha_entrada_pilon` date NOT NULL,
  `numero_pilon` varchar(50) NOT NULL DEFAULT '',
  `entrada_tabaco_pilon` varchar(100) DEFAULT NULL,
  `salida_tabaco_pilon` varchar(100) DEFAULT NULL,
  `total_actual` decimal(10,2) DEFAULT NULL,
  `Total` decimal(10,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id_control_pilones`)
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para procedimiento db_taopar_pilones.eliminar_control_temp
DELIMITER //
CREATE PROCEDURE `eliminar_control_temp`(
	IN `Pa_id_control_temp` INT
)
BEGIN

DELETE FROM control_temperatura WHERE id_temperatura = pa_id_control_temp;
SELECT "Eliminado correctamente",1;

END//
DELIMITER ;

-- Volcando estructura para tabla db_taopar_pilones.entrada_pilones
CREATE TABLE IF NOT EXISTS `entrada_pilones` (
  `id_entrada_pilones` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_tabaco` varchar(50) NOT NULL DEFAULT '0',
  `numero_pilon` varchar(50) NOT NULL DEFAULT '0',
  `fecha_entrada_pilon` date NOT NULL DEFAULT '0000-00-00',
  `tiempo_adelanto_pilon` varchar(50) NOT NULL DEFAULT '0',
  `fecha_estimada_salida` date NOT NULL DEFAULT '0000-00-00',
  `cantidad_lbs` decimal(10,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id_entrada_pilones`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para procedimiento db_taopar_pilones.insertar_control_pilones
DELIMITER //
CREATE PROCEDURE `insertar_control_pilones`(
	IN `pa_nombre_tabaco` VARCHAR(100),
	IN `pa_variedad_tabaco` VARCHAR(100),
	IN `pa_finca` VARCHAR(100),
	IN `pa_fecha_entrada_pilon` DATE,
	IN `pa_numero_pilon` VARCHAR(50),
	IN `pa_entrada_tabaco_pilon` VARCHAR(100),
	IN `pa_salida_tabaco_pilon` VARCHAR(100),
	IN `pa_total_actual` DECIMAL(10,2),
	IN `pa_total` DECIMAL(10,2)
)
BEGIN

			INSERT INTO control_pilones (nombre_tabaco,variedad_tabaco, finca,fecha_entrada_pilon,
			numero_pilon,entrada_tabaco_pilon,salida_tabaco_pilon,total_actual,total) VALUES(pa_nombre_tabaco,
			pa_variedad_tabaco,pa_finca,pa_fecha_entrada_pilon,pa_numero_pilon,pa_entrada_tabaco_pilon,pa_salida_tabaco_pilon,pa_total_actual,pa_total);
			SELECT 'Guardado correctamente',1;

END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.insertar_control_temp
DELIMITER //
CREATE PROCEDURE `insertar_control_temp`(
	IN `pa_id_pilones` INT,
	IN `pa_temperatura` INT,
	IN `pa_fecha_revision` DATE,
	IN `pa_mantenimiento` VARCHAR(30)
)
BEGIN
if EXISTS(SELECT * FROM control_temperatura WHERE control_temperatura.id_pilones = pa_id_pilones and
control_temperatura.fecha_revision = pa_fecha_revision) then
		SELECT 'Temperatura del pión ya registrada',0;
	ELSE	
		
     INSERT INTO control_temperatura ( id_pilones, temperatura, fecha_revision, mantenimiento) VALUES (pa_id_pilones,
                                       pa_temperatura, pa_fecha_revision, pa_mantenimiento);
  		 SELECT "Guardado correctamente",1;
END if;
  
END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.insertar_entrada_pilon
DELIMITER //
CREATE PROCEDURE `insertar_entrada_pilon`(
	IN `pa_nombre_tabaco_entrada` VARCHAR(50),
	IN `pa_id_pilon_entrada` VARCHAR(50),
	IN `pa_fecha_entrada_pilon` DATE,
	IN `pa_tiempo_adelanto_entrada` VARCHAR(50),
	IN `pa_fecha_estimada_salida` DATE,
	IN `pa_cantidad_lbs_entrada` VARCHAR(50)
)
BEGIN

   INSERT INTO entrada_pilones(nombre_tabaco,numero_pilon,fecha_entrada_pilon,tiempo_adelanto_pilon,fecha_estimada_salida,cantidad_lbs) 
	VALUES (pa_nombre_tabaco_entrada,pa_id_pilon_entrada,pa_fecha_entrada_pilon,pa_tiempo_adelanto_entrada,pa_fecha_estimada_salida,
	pa_cantidad_lbs_entrada);
   SELECT 'Guardado correctamente ',1;
   
END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.insertar_pilones
DELIMITER //
CREATE PROCEDURE `insertar_pilones`(
	IN `pa_nombre_pilon` VARCHAR(50)
)
BEGIN
	if EXISTS(SELECT * FROM pilones WHERE pilones.numero_pilon = pa_nombre_pilon) then
		SELECT 'No se puede Guardar',1;
	ELSE	
		INSERT INTO pilones(numero_pilon) VALUES(pa_nombre_pilon); 
		SELECT 'Guardado correctamente',1;
	END if;
END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.insertar_remision_proceso
DELIMITER //
CREATE PROCEDURE `insertar_remision_proceso`(
	IN `pa_id_remision` INT,
	IN `pa_fecha_remision` DATE,
	IN `pa_destino_remision` VARCHAR(50),
	IN `pa_origen_remision` VARCHAR(50),
	IN `pa_descripcion1_remision` VARCHAR(100),
	IN `pa_cant_lbs_des_1` DECIMAL(10,2),
	IN `pa_descripcion2_remision` VARCHAR(100),
	IN `pa_cant_lbs_des_2` DECIMAL(10,2),
	IN `pa_descripcion3_remision` VARCHAR(100),
	IN `pa_cant_lbs_des_3` DECIMAL(10,2),
	IN `pa_descripcion4_remision` VARCHAR(100),
	IN `pa_cant_lbs_des_4` DECIMAL(10,2),
	IN `pa_descripcion5_remision` VARCHAR(100),
	IN `pa_cant_lbs_des_5` DECIMAL(10,2),
	IN `pa_total_remision` DECIMAL(10,2)
)
BEGIN
  if EXISTS ( SELECT * FROM remision_proceso WHERE id_remision = pa_id_remision) then 
  SELECT 'no se puede guardar',0;
  
  else
     INSERT INTO remision_proceso ( id_remision,fecha_remision, destino_remision, origen_remision, descripcion1_remision,
     cant_lbs_des_1,descripcion2_remision,cant_lbs_des_2, descripcion3_remision,cant_lbs_des_3,descripcion4_remision,cant_lbs_des_4,
	  descripcion5_remision,cant_lbs_des_5, total_remision) 
	  VALUES (pa_id_remision,pa_fecha_remision,pa_destino_remision,pa_origen_remision,pa_descripcion1_remision,
	  pa_cant_lbs_des_1,pa_descripcion2_remision,pa_cant_lbs_des_2,pa_descripcion3_remision,pa_cant_lbs_des_3,pa_descripcion4_remision,
	  pa_cant_lbs_des_4,pa_descripcion5_remision,pa_cant_lbs_des_5,pa_total_remision);
  SELECT 'Guardado correctamente',1;
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
	IN `pa_fecha_proceso` DATE,
	IN `pa_id_remision` INT,
	IN `pa_entradas_salidas` VARCHAR(30),
	IN `pa_nombre_tabaco` VARCHAR(50),
	IN `pa_numero_pilon` VARCHAR(50),
	IN `pa_subtotal` DECIMAL(10,2),
	IN `pa_total_libras` DECIMAL(10,2),
	IN `pa_total_remision` DECIMAL(10,2)
)
BEGIN
  if EXISTS ( SELECT * FROM tabla_pilon WHERE id_remision = pa_id_remision AND nombre_tabaco = pa_nombre_tabaco 
  AND numero_pilon = pa_numero_pilon) then 
  SELECT 'no se puede repetir el numero de remisión',0;
  
  else
     INSERT INTO tabla_pilon (fecha_proceso,id_remision, entradas_salidas, nombre_tabaco, numero_pilon, subtotal, total_libras,total_remision) 
	  VALUES (pa_fecha_proceso,pa_id_remision,pa_entradas_salidas,pa_nombre_tabaco,pa_numero_pilon,pa_subtotal,pa_total_libras,pa_total_remision);
  		SELECT 'Guardado correctamente',1;
  END if;
END//
DELIMITER ;

-- Volcando estructura para procedimiento db_taopar_pilones.insertar_tabla_procesos
DELIMITER //
CREATE PROCEDURE `insertar_tabla_procesos`(
	IN `pa_fecha_proceso` DATE,
	IN `pa_id_remision` INT,
	IN `pa_entradas_salidas` VARCHAR(30),
	IN `pa_nombre_tabaco` VARCHAR(50),
	IN `pa_numero_pilon` VARCHAR(50),
	IN `pa_subtotal` DECIMAL(10,2),
	IN `pa_total_libras` DECIMAL(10,2),
	IN `pa_total_remision` DECIMAL(10,2)
)
BEGIN
  if EXISTS ( SELECT * FROM tabla_procesos WHERE id_remision = pa_id_remision AND nombre_tabaco = pa_nombre_tabaco 
  AND numero_pilon = pa_numero_pilon ) then 
  SELECT 'no se puede repetir el numero de remisión',0;
  
  else
     INSERT INTO tabla_procesos (fecha_proceso,id_remision, entradas_salidas, nombre_tabaco, numero_pilon, subtotal, total_libras,total_remision) 
	  VALUES (pa_fecha_proceso,pa_id_remision,pa_entradas_salidas,pa_nombre_tabaco,pa_numero_pilon,pa_subtotal,pa_total_libras,pa_total_remision );
   SELECT 'Guardado correctamente',1;
  
  END if;
END//
DELIMITER ;

-- Volcando estructura para tabla db_taopar_pilones.pilones
CREATE TABLE IF NOT EXISTS `pilones` (
  `id_pilon` bigint(20) NOT NULL AUTO_INCREMENT,
  `numero_pilon` int(11) NOT NULL,
  PRIMARY KEY (`id_pilon`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla db_taopar_pilones.remision_proceso
CREATE TABLE IF NOT EXISTS `remision_proceso` (
  `id_remision_proceso` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_remision` int(11) NOT NULL DEFAULT 0,
  `fecha_remision` date NOT NULL DEFAULT '0000-00-00',
  `destino_remision` varchar(20) NOT NULL DEFAULT '0',
  `origen_remision` varchar(20) NOT NULL DEFAULT '0',
  `descripcion1_remision` varchar(100) NOT NULL DEFAULT '0',
  `cant_lbs_des_1` decimal(10,2) NOT NULL DEFAULT 0.00,
  `descripcion2_remision` varchar(100) DEFAULT '0',
  `cant_lbs_des_2` decimal(10,2) DEFAULT NULL,
  `descripcion3_remision` varchar(100) DEFAULT '0',
  `cant_lbs_des_3` decimal(10,2) DEFAULT NULL,
  `descripcion4_remision` varchar(100) DEFAULT '0',
  `cant_lbs_des_4` decimal(10,2) DEFAULT NULL,
  `descripcion5_remision` varchar(100) DEFAULT '0',
  `cant_lbs_des_5` decimal(10,2) DEFAULT NULL,
  `total_remision` decimal(10,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id_remision_proceso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para procedimiento db_taopar_pilones.revision_pendiente_pilon
DELIMITER //
CREATE PROCEDURE `revision_pendiente_pilon`()
BEGIN
 	SELECT * FROM control_temperatura WHERE control_temperatura.fecha_revision = DATE_FORMAT(NOW(), "%Y-%m-%d");
END//
DELIMITER ;

-- Volcando estructura para tabla db_taopar_pilones.tabla_pilon
CREATE TABLE IF NOT EXISTS `tabla_pilon` (
  `id_tabla_pilon` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_proceso` date NOT NULL,
  `id_remision` int(11) NOT NULL DEFAULT 0,
  `entradas_salidas` varchar(30) NOT NULL DEFAULT '0',
  `nombre_tabaco` varchar(50) DEFAULT NULL,
  `numero_pilon` varchar(50) DEFAULT '0',
  `subtotal` decimal(10,2) NOT NULL DEFAULT 0.00,
  `total_libras` decimal(10,2) NOT NULL DEFAULT 0.00,
  `total_remision` decimal(10,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id_tabla_pilon`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla db_taopar_pilones.tabla_procesos
CREATE TABLE IF NOT EXISTS `tabla_procesos` (
  `id_tabla_proceso` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_proceso` date NOT NULL,
  `id_remision` int(11) NOT NULL DEFAULT 0,
  `entradas_salidas` varchar(30) NOT NULL DEFAULT '0',
  `nombre_tabaco` varchar(50) DEFAULT '0',
  `numero_pilon` varchar(50) DEFAULT '0',
  `subtotal` decimal(10,2) NOT NULL DEFAULT 0.00,
  `total_libras` decimal(10,2) NOT NULL DEFAULT 0.00,
  `total_remision` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_tabla_proceso`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para procedimiento db_taopar_pilones.traer_datos_grafico_temperatura
DELIMITER //
CREATE PROCEDURE `traer_datos_grafico_temperatura`(
	IN `id_pilon` INT,
	IN `fecha_revision` DATE
)
BEGIN
   SELECT * 
	FROM control_temperatura 
	WHERE    YEAR(control_temperatura.fecha_revision) = YEAR(fecha_revision) 
			AND	
				month(control_temperatura.fecha_revision) =  month(fecha_revision)  
			AND 
				control_temperatura.id_pilones = id_pilon
	ORDER BY control_temperatura.fecha_revision;
END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
