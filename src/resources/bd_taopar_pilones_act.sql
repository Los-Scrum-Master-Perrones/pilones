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

-- Volcando datos para la tabla db_taopar_pilones.clase_tabaco: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `clase_tabaco` DISABLE KEYS */;
REPLACE INTO `clase_tabaco` (`id_tabaco`, `nombre_tabaco`) VALUES
	(1, 'Hola');
/*!40000 ALTER TABLE `clase_tabaco` ENABLE KEYS */;

-- Volcando datos para la tabla db_taopar_pilones.control_temperatura: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `control_temperatura` DISABLE KEYS */;
REPLACE INTO `control_temperatura` (`id_temperatura`, `id_pilones`, `temperatura`, `fecha_revision`, `mantenimiento`) VALUES
	(2, 1, 80, '2021-02-15', 'Virado'),
	(3, 1, 80, '2021-02-15', 'Mojado');
/*!40000 ALTER TABLE `control_temperatura` ENABLE KEYS */;

-- Volcando datos para la tabla db_taopar_pilones.entrada_pilones: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `entrada_pilones` DISABLE KEYS */;
/*!40000 ALTER TABLE `entrada_pilones` ENABLE KEYS */;

-- Volcando datos para la tabla db_taopar_pilones.pilones: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `pilones` DISABLE KEYS */;
REPLACE INTO `pilones` (`id_pilon`, `numero_pilon`) VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5);
/*!40000 ALTER TABLE `pilones` ENABLE KEYS */;

-- Volcando datos para la tabla db_taopar_pilones.remision_proceso: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `remision_proceso` DISABLE KEYS */;
/*!40000 ALTER TABLE `remision_proceso` ENABLE KEYS */;

-- Volcando datos para la tabla db_taopar_pilones.tabla_pilon: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tabla_pilon` DISABLE KEYS */;
/*!40000 ALTER TABLE `tabla_pilon` ENABLE KEYS */;

-- Volcando datos para la tabla db_taopar_pilones.tabla_procesos: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `tabla_procesos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tabla_procesos` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
