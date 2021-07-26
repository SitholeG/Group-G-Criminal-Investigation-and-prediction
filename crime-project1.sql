DROP DATABASE  IF EXISTS `crime_investigation_and_prediction`;

CREATE DATABASE  IF NOT EXISTS `crime_investigation_and_prediction`;
USE `crime_investigation_and_prediction`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `Mobile_number` varchar(50) NOT NULL,
  `work_area` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



INSERT INTO `user` (username,password,first_name,last_name,email)
VALUES 
('john','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','John','Doe','john@gmail.com'),
('mary','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Mary','Public','mary@outlook.com'),
('susan','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Susan','Adams','susan@outlook.com');


--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `role` (name)
VALUES 
('ROLE_OFFICER'),('ROLE_ADMIN');

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 2),
(2, 1),
(3, 1);


DROP TABLE IF EXISTS `case`;

CREATE TABLE `case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `case_name` varchar(50) NOT NULL,
  `note` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `cases_users`;

CREATE TABLE `cases_users` (
  `case_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`case_id`),
  
  KEY `FK_USER_idx` (`user_id`),
  
  CONSTRAINT `FK_CASE_06` FOREIGN KEY (`case_id`) 
  REFERENCES `case` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table `users_case`
--

USE `crime_investigation_and_predection`;
INSERT INTO `case`(case_name, note) values('Test', 'test case');

INSERT INTO `cases_users` (case_id,user_id)
VALUES 
(1, 1),
(1, 2),
(1, 3);

DROP TABLE IF EXISTS `suspect`;

CREATE TABLE `suspect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `case_id` int(11) NOT NULL,
  `suspect_name` varchar(50) NOT NULL,
  `relationship` varchar(50) NOT NULL,
  `address` varchar(255) NOT NULL,
  `rank` int(11) NOT NULL,
  `Mobile_number` varchar(50) NOT NULL,
  `note` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_CASE_07` FOREIGN KEY (`case_id`) 
  REFERENCES `case` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `evidence`;

CREATE TABLE `evidence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `case_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `suspect_id` int(11) NOT NULL,
  `evidence` varchar(250) NOT NULL,
  `type` varchar(50) NOT NULL,
  `rank` int(11) NOT NULL,
  `note` varchar(50) NOT NULL,
  `date` datetime not null,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_CASE_08` FOREIGN KEY (`case_id`) 
  REFERENCES `case` (`id`),
  CONSTRAINT `FK_USER_02` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`),
  CONSTRAINT `FK_SUSPECT` FOREIGN KEY (`suspect_id`) 
  REFERENCES `suspect` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `Action_logs`;

CREATE TABLE `Action_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `case_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `action` varchar(250) NOT NULL,
  `date` datetime not null,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_CASE_09` FOREIGN KEY (`case_id`) 
  REFERENCES `case` (`id`),
  CONSTRAINT `FK_USER_03` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `case_results`;

CREATE TABLE `case_results` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `case_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `case_results` varchar(250) NOT NULL,
  `date` datetime not null,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_CASE_10` FOREIGN KEY (`case_id`) 
  REFERENCES `case` (`id`),
  CONSTRAINT `FK_USER_04` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;