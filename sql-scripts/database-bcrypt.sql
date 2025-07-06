CREATE DATABASE IF NOT EXISTS `student_tracker`;
USE `student_tracker`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.bcryptcalculator.com
--
-- Default passwords here are: 123
--

INSERT INTO `users` 
VALUES 
('cynwell','{bcrypt}$2a$10$TTnM4FNoqZjwb5FqR5TCLuMR6T9OaiOOzgEPgRrtxM1mnMNB0ApvS',1),
('ninni','{bcrypt}$2a$10$TTnM4FNoqZjwb5FqR5TCLuMR6T9OaiOOzgEPgRrtxM1mnMNB0ApvS',1),
('inch','{bcrypt}$2a$10$TTnM4FNoqZjwb5FqR5TCLuMR6T9OaiOOzgEPgRrtxM1mnMNB0ApvS',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities4_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('cynwell','ROLE_ADMIN'),
('ninni','ROLE_MANAGER'),
('inch','ROLE_EMPLOYEE');