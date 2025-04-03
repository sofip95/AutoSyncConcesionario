
CREATE DATABASE IF NOT EXISTS `proyecto` ;
USE `proyecto`;

CREATE TABLE IF NOT EXISTS `prueba_manejo` (
  `id_prueba` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fecha_prueba` date DEFAULT NULL,
  `id_vehiculo` varchar(10) NOT NULL,
  `id_cliente` int(11) unsigned NOT NULL,
  `id_empleado` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_prueba`),
  KEY `fk_id_cliente_1` (`id_cliente`),
  KEY `fk_id_empleado_1` (`id_empleado`),
  KEY `fk_placa_1` (`id_vehiculo`),
  CONSTRAINT `fk_id_cliente_1` FOREIGN KEY (`id_cliente`) REFERENCES `usuario` (`id_usuario`) ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT `fk_id_empleado_1` FOREIGN KEY (`id_empleado`) REFERENCES `usuario` (`id_usuario`) ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT `fk_placa_1` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`placa`) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `contrasenia` varchar(250) DEFAULT NULL,
  `nombre` varchar(250) DEFAULT NULL,
  `edad` int(3) unsigned DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `correo` varchar(250) DEFAULT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `rol` enum('Admin','Cliente','Empleado') DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

CREATE TABLE IF NOT EXISTS `vehiculo` (
  `placa` varchar(10) NOT NULL,
  `marca` varchar(250) NOT NULL,
  `modelo` varchar(250) DEFAULT NULL,
  `anio` year(4) DEFAULT NULL,
  `color` varchar(250) DEFAULT NULL,
  `precio_venta` float unsigned NOT NULL,
  PRIMARY KEY (`placa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

CREATE TABLE IF NOT EXISTS `venta` (
  `id_venta` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `id_cliente` int(11) unsigned NOT NULL,
  `id_vehiculo` varchar(10) NOT NULL,
  `fecha_venta` date DEFAULT NULL,
  `precio_venta` float NOT NULL,
  PRIMARY KEY (`id_venta`),
 KEY `fk_id_cliente_2` (`id_cliente`),
  KEY `fk_placa_2` (`id_vehiculo`),
  CONSTRAINT `fk_id_cliente_2` FOREIGN KEY (`id_cliente`) REFERENCES `usuario` (`id_usuario`) ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT `fk_placa_2` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`placa`) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

