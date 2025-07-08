USE `student_tracker`;

DROP TABLE IF EXISTS `one_to_many_course`;
DROP TABLE IF EXISTS `one_to_many_instructor`;
DROP TABLE IF EXISTS `one_to_many_instructor_detail`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `one_to_many_instructor_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `youtube_channel` varchar(128) DEFAULT NULL,
  `hobby` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `one_to_many_instructor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `instructor_detail_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DETAIL_idx` (`instructor_detail_id`),
  CONSTRAINT `ONE_TO_MANY_FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) 
  REFERENCES `one_to_many_instructor_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `one_to_many_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `instructor_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `TITLE_UNIQUE` (`title`),
  KEY `FK_INSTRUCTOR_idx` (`instructor_id`),
  CONSTRAINT `ONE_TO_MANY_FK_INSTRUCTOR` 
  FOREIGN KEY (`instructor_id`) 
  REFERENCES `one_to_many_instructor` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
