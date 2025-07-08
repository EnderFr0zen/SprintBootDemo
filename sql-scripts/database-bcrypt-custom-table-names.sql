CREATE DATABASE IF NOT EXISTS `student_tracker`;
USE `student_tracker`;

DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `members`;

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `user_id` varchar(50) NOT NULL,
  `pw` char(68) NOT NULL,
  `active` tinyint NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `members`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.bcryptcalculator.com
--
-- Default passwords here are: 123
--

INSERT INTO `members`
VALUES
('cynwell','{bcrypt}$2a$10$TTnM4FNoqZjwb5FqR5TCLuMR6T9OaiOOzgEPgRrtxM1mnMNB0ApvS',1),
('ninni','{bcrypt}$2a$10$TTnM4FNoqZjwb5FqR5TCLuMR6T9OaiOOzgEPgRrtxM1mnMNB0ApvS',1),
('inch','{bcrypt}$2a$10$TTnM4FNoqZjwb5FqR5TCLuMR6T9OaiOOzgEPgRrtxM1mnMNB0ApvS',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `roles` (
  `user_id` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `authorities5_idx_1` (`user_id`,`role`),
  CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `roles`
--

INSERT INTO `roles`
VALUES
('cynwell','ROLE_ADMIN'),
('ninni','ROLE_MANAGER'),
('inch','ROLE_EMPLOYEE');
