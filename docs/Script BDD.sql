-- SCRIPT BDD GRUPO 21 OO2 2021
-- INFO DE USUARIOS
-- ADMIN, user: admin1 - pass: 123456
-- AUDITOR, user: auditor1 - pass: 654321


-- CREA BDD
CREATE DATABASE  IF NOT EXISTS `grupo-21-bdd-oo2-2021`;
USE `grupo-21-bdd-oo2-2021`;


-- CREA TABLAS

CREATE TABLE `lugar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigopostal` varchar(255) NOT NULL,
  `lugar` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `persona` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) NOT NULL,
  `createdat` datetime(6) NOT NULL,
  `documento` bigint NOT NULL,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `tipodocumento` int NOT NULL,
  `updatedat` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKflilha0kj19qtd8tjk6rv6vm1` (`tipodocumento`,`documento`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `rodado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `createdat` datetime(6) NOT NULL,
  `dominio` varchar(255) NOT NULL,
  `updatedat` datetime(6) DEFAULT NULL,
  `vehiculo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_13m3g5b74jl938lil3rvxy9w3` (`dominio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `activo` bit(1) DEFAULT NULL,
  `createdat` datetime(6) NOT NULL,
  `role` varchar(100) NOT NULL,
  `updatedat` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_s21d8k5lywjjc7inw14brj6ro` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
  `activo` bit(1) DEFAULT NULL,
  `password` varchar(60) NOT NULL,
  `username` varchar(45) NOT NULL,
  `id` int NOT NULL,
  `user_role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKh2wc2dtfdo8maylne7mgubowq` (`user_role_id`),
  CONSTRAINT `FKh2wc2dtfdo8maylne7mgubowq` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`id`),
  CONSTRAINT `FKq9e1epiqip339syujm4brhw7u` FOREIGN KEY (`id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `permiso` (
  `tipo` varchar(31) NOT NULL,
  `id_permiso` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `id_desde_menor` bit(1) NOT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `cant_dias` int DEFAULT NULL,
  `vacaciones` bit(1) DEFAULT NULL,
  `pedido_id` int NOT NULL,
  `rodado_id` int DEFAULT NULL,
  PRIMARY KEY (`id_permiso`),
  KEY `FKft1wik02q1fqdvh64y3pa5kj9` (`pedido_id`),
  KEY `FK4myhtjulhfu3qp0gn2ft2oarn` (`rodado_id`),
  CONSTRAINT `FK4myhtjulhfu3qp0gn2ft2oarn` FOREIGN KEY (`rodado_id`) REFERENCES `rodado` (`id`),
  CONSTRAINT `FKft1wik02q1fqdvh64y3pa5kj9` FOREIGN KEY (`pedido_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `rel_permiso_lugar` (
  `fk_permiso` int NOT NULL,
  `fk_lugar` int NOT NULL,
  PRIMARY KEY (`fk_permiso`,`fk_lugar`),
  KEY `FK9x1q43hvmdy9gt9ha2qcrnty1` (`fk_lugar`),
  CONSTRAINT `FK9x1q43hvmdy9gt9ha2qcrnty1` FOREIGN KEY (`fk_lugar`) REFERENCES `lugar` (`id`),
  CONSTRAINT `FKj5rs2w5c4pi1ur6x35hrai8re` FOREIGN KEY (`fk_permiso`) REFERENCES `permiso` (`id_permiso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- CREA PERSONAS

INSERT INTO `persona` (`id`,`apellido`,`createdat`,`documento`,`email`,`nombre`,`tipodocumento`,`updatedat`) VALUES (9,'Ventura','2021-06-01 20:58:52.000000',12345678,'ventura666@gmail.com','Luis',1,'2021-06-01 20:58:52.000000');
INSERT INTO `persona` (`id`,`apellido`,`createdat`,`documento`,`email`,`nombre`,`tipodocumento`,`updatedat`) VALUES (10,'Tauro','2021-06-01 20:58:52.000000',23456789,'marce123@hotmail.com','Marcela',0,'2021-06-01 20:58:52.000000');
INSERT INTO `persona` (`id`,`apellido`,`createdat`,`documento`,`email`,`nombre`,`tipodocumento`,`updatedat`) VALUES (11,'Roccasalvo','2021-05-30 00:57:53.051141',22345691,'susy_rock@gmail.com','Susana',0,'2021-05-30 00:57:53.051141');
INSERT INTO `persona` (`id`,`apellido`,`createdat`,`documento`,`email`,`nombre`,`tipodocumento`,`updatedat`) VALUES (12,'Soldán','2021-06-01 22:58:13.311360',12462151,'ssoldan35@yahoo.com','Silvio',2,'2021-06-01 22:58:13.311360');
INSERT INTO `persona` (`id`,`apellido`,`createdat`,`documento`,`email`,`nombre`,`tipodocumento`,`updatedat`) VALUES (13,'Süller','2021-06-01 23:01:22.319064',11514921,'silsu58@gmail.com','Silvia',0,'2021-06-01 23:01:22.319064');
INSERT INTO `persona` (`id`,`apellido`,`createdat`,`documento`,`email`,`nombre`,`tipodocumento`,`updatedat`) VALUES (14,'Rivero','2021-06-01 23:09:28.624466',7114261,'riverito88@hotmail.com','Luis',0,'2021-06-01 23:09:28.624466');
INSERT INTO `persona` (`id`,`apellido`,`createdat`,`documento`,`email`,`nombre`,`tipodocumento`,`updatedat`) VALUES (15,'Mendoza','2021-06-01 23:32:27.553053',12332199,'fmendoza74@gmail.com','Flavio',0,'2021-06-01 23:32:27.553053');


-- CREA ROLES

INSERT INTO user_role VALUES(1, b'1', NOW(), 'ROLE_ADMIN', NOW());
INSERT INTO user_role VALUES(2, b'1', NOW(), 'ROLE_AUDITOR', NOW());


-- CREA USUARIOS

INSERT INTO `grupo-21-bdd-oo2-2021`.`user` VALUES (b'1', '$2a$10$nauXseps08y1qK9Z7EkOqODxaQLOW8rVQV/jXugNFh2YB0vweEigK', 'admin1', 9, 1);
INSERT INTO `grupo-21-bdd-oo2-2021`.`user` VALUES (b'1', '$2a$10$5cSXuenrSRurzG3S2yZWWex6Cbe9YMUUlEIbwYePC9uEkMYqu5BWW', 'auditor1', 10, 2);


-- CREA RODADOS

INSERT INTO `rodado` (`id`,`createdat`,`dominio`,`updatedat`,`vehiculo`) VALUES (2,'2021-05-30 02:07:59.116703','ABC123','2021-05-30 02:07:59.116703','Renault Megane');
INSERT INTO `rodado` (`id`,`createdat`,`dominio`,`updatedat`,`vehiculo`) VALUES (3,'2021-05-30 02:12:31.834870','DEF456','2021-05-30 02:12:31.834870','Peugeot 206');
INSERT INTO `rodado` (`id`,`createdat`,`dominio`,`updatedat`,`vehiculo`) VALUES (4,'2021-06-01 22:58:13.324364','AB789CD','2021-06-01 22:58:13.324364','Volkswagen Polo');
INSERT INTO `rodado` (`id`,`createdat`,`dominio`,`updatedat`,`vehiculo`) VALUES (5,'2021-06-01 23:19:43.129135','EF256GH','2021-06-01 23:19:43.129135','Renault Sandero');
INSERT INTO `rodado` (`id`,`createdat`,`dominio`,`updatedat`,`vehiculo`) VALUES (6,'2021-06-01 23:32:27.560053','OL159IS','2021-06-01 23:32:27.560053','Zanella Styler 150');


-- CREA PERMISOS

INSERT INTO `permiso` (`tipo`,`id_permiso`,`fecha`,`id_desde_menor`,`motivo`,`cant_dias`,`vacaciones`,`pedido_id`,`rodado_id`) VALUES ('Diario',1,'2021-06-01',b'0','Asistir a adulto mayor',NULL,NULL,11,NULL);
INSERT INTO `permiso` (`tipo`,`id_permiso`,`fecha`,`id_desde_menor`,`motivo`,`cant_dias`,`vacaciones`,`pedido_id`,`rodado_id`) VALUES ('Periodo',3,'2021-06-24',b'1',NULL,4,b'1',10,2);
INSERT INTO `permiso` (`tipo`,`id_permiso`,`fecha`,`id_desde_menor`,`motivo`,`cant_dias`,`vacaciones`,`pedido_id`,`rodado_id`) VALUES ('Periodo',4,'2021-06-03',b'0',NULL,10,b'0',9,3);
INSERT INTO `permiso` (`tipo`,`id_permiso`,`fecha`,`id_desde_menor`,`motivo`,`cant_dias`,`vacaciones`,`pedido_id`,`rodado_id`) VALUES ('Diario',5,'2021-07-09',b'0','Retorno de menor al hogar del tutelar',NULL,NULL,9,NULL);
INSERT INTO `permiso` (`tipo`,`id_permiso`,`fecha`,`id_desde_menor`,`motivo`,`cant_dias`,`vacaciones`,`pedido_id`,`rodado_id`) VALUES ('Periodo',6,'2021-06-11',b'0',NULL,8,b'0',12,4);
INSERT INTO `permiso` (`tipo`,`id_permiso`,`fecha`,`id_desde_menor`,`motivo`,`cant_dias`,`vacaciones`,`pedido_id`,`rodado_id`) VALUES ('Periodo',7,'2021-06-02',b'0',NULL,5,b'1',13,4);
INSERT INTO `permiso` (`tipo`,`id_permiso`,`fecha`,`id_desde_menor`,`motivo`,`cant_dias`,`vacaciones`,`pedido_id`,`rodado_id`) VALUES ('Diario',8,'2021-06-08',b'1','Asistencia a establecimiento de salud',NULL,NULL,14,NULL);
INSERT INTO `permiso` (`tipo`,`id_permiso`,`fecha`,`id_desde_menor`,`motivo`,`cant_dias`,`vacaciones`,`pedido_id`,`rodado_id`) VALUES ('Diario',9,'2021-07-17',b'1','Cambio de domicilio',NULL,NULL,12,NULL);
INSERT INTO `permiso` (`tipo`,`id_permiso`,`fecha`,`id_desde_menor`,`motivo`,`cant_dias`,`vacaciones`,`pedido_id`,`rodado_id`) VALUES ('Diario',10,'2021-07-14',b'1','Comparecencia a una citación en virtud de la Ley',NULL,NULL,10,NULL);
INSERT INTO `permiso` (`tipo`,`id_permiso`,`fecha`,`id_desde_menor`,`motivo`,`cant_dias`,`vacaciones`,`pedido_id`,`rodado_id`) VALUES ('Periodo',11,'2021-06-30',b'1',NULL,5,b'0',11,5);
INSERT INTO `permiso` (`tipo`,`id_permiso`,`fecha`,`id_desde_menor`,`motivo`,`cant_dias`,`vacaciones`,`pedido_id`,`rodado_id`) VALUES ('Periodo',12,'2021-06-06',b'1',NULL,3,b'0',15,6);


-- CREA LUGARES

INSERT INTO `lugar` VALUES (1, "1846", "ADROGUÉ");
INSERT INTO `lugar` VALUES (2, "1846", "ALMIRANTE BROWN");
INSERT INTO `lugar` VALUES (3, "1852", "BURZACO");
INSERT INTO `lugar` VALUES (4, "1849", "CLAYPOLE");
INSERT INTO `lugar` VALUES (5, "1856", "GLEW");
INSERT INTO `lugar` VALUES (6, "1846", "JOSÉ MARMOL");
INSERT INTO `lugar` VALUES (7, "1854", "LONGCHAMPS");
INSERT INTO `lugar` VALUES (8, "1852", "MINISTRO RIVADAVIA");
INSERT INTO `lugar` VALUES (9, "1847", "RAFAEL CALZADA");
INSERT INTO `lugar` VALUES (10, "1870", "AVELLANEDA");
INSERT INTO `lugar` VALUES (11, "1871", "DOCK SUD");
INSERT INTO `lugar` VALUES (12, "1872", "SARANDÍ");
INSERT INTO `lugar` VALUES (13, "1875", "WILDE");
INSERT INTO `lugar` VALUES (14, "1824", "LANÚS");
INSERT INTO `lugar` VALUES (15, "1828", "BANFIELD");
INSERT INTO `lugar` VALUES (16, "1836", "LLAVALLOL");
INSERT INTO `lugar` VALUES (17, "1832", "LOMAS DE ZAMORA");
INSERT INTO `lugar` VALUES (18, "1834", "TEMPERLEY");
INSERT INTO `lugar` VALUES (19, "1834", "TURDERA");


-- CREA REL_PERMISO_LUGAR

INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (1,1);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (4,1);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (1,2);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (5,2);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (11,2);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (3,3);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (10,3);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (4,4);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (5,4);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (9,4);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (10,5);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (3,6);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (8,7);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (11,7);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (9,8);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (12,9);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (6,11);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (8,12);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (12,13);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (7,16);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (6,19);
INSERT INTO `rel_permiso_lugar` (`fk_permiso`,`fk_lugar`) VALUES (7,19);