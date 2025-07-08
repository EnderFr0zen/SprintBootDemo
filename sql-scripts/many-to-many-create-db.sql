USE `student_tracker`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `many_to_many_course_learner`;
DROP TABLE IF EXISTS `many_to_many_learner`;
DROP TABLE IF EXISTS `many_to_many_review`;
DROP TABLE IF EXISTS `many_to_many_course`;
DROP TABLE IF EXISTS `many_to_many_instructor`;
DROP TABLE IF EXISTS `many_to_many_instructor_detail`;


CREATE TABLE `many_to_many_instructor_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `youtube_channel` varchar(128) DEFAULT NULL,
  `hobby` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `many_to_many_instructor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `instructor_detail_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DETAIL_idx` (`instructor_detail_id`),
  CONSTRAINT `MANY_TO_MANY_FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) 
  REFERENCES `many_to_many_instructor_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `many_to_many_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `instructor_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `TITLE_UNIQUE` (`title`),
  KEY `FK_INSTRUCTOR_idx` (`instructor_id`),
  CONSTRAINT `FK_INSTRUCTOR` 
  FOREIGN KEY (`instructor_id`) 
  REFERENCES `many_to_many_instructor` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=latin1;


CREATE TABLE `many_to_many_review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` varchar(256) DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_COURSE_ID_idx` (`course_id`),
  CONSTRAINT `MANY_TO_MANY_FK_COURSE` 
  FOREIGN KEY (`course_id`) 
  REFERENCES `many_to_many_course` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `many_to_many_learner` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `many_to_many_course_learner` (
  `course_id` int NOT NULL,
  `learner_id` int NOT NULL,
  PRIMARY KEY (`course_id`,`learner_id`),
  KEY `FK_LEARNER_idx` (`learner_id`),
  CONSTRAINT `MANY_TO_MANY_FK_COURSE_05` FOREIGN KEY (`course_id`) 
  REFERENCES `many_to_many_course` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `MANY_TO_MANY_FK_LEARNER` FOREIGN KEY (`learner_id`) 
  REFERENCES `many_to_many_learner` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
