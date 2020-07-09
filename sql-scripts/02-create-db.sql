DROP SCHEMA IF EXISTS `running_app`;

CREATE SCHEMA `running_app`;

use `running_app`;

SET FOREIGN_KEY_CHECKS = 0;

create table `user`(
	`id` int(11) not null AUTO_INCREMENT,
    `username` varchar(45) not null,
	`password` varchar(80) not null,
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `training` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `date` DATE DEFAULT NULL,
  `is_done` BOOLEAN DEFAULT false,
  `series` int(11) DEFAULT NULL,
  `training_details_id` int(11) DEFAULT NULL,
  `training_result_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DETAIL_IDX` (`training_details_id`),
  constraint `FK_SERIES` FOREIGN KEY (`series`) references `series` (`id`)
  on delete set null on update no action,
  constraint `FK_USER_TRAINING` FOREIGN KEY (`user_id`) references `user` (`id`)
  on delete cascade on update cascade,
  constraint `FK_DETAIL` FOREIGN KEY (`training_details_id`) references `training_details` (`id`)
  on delete no action on update no action,
  constraint `FK_RESULT` FOREIGN KEY (`training_result_id`) references `training_result` (`id`)
  on delete no action on update no action
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `training_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `distance` double(5,2) DEFAULT NULL,
  `min_hr` int DEFAULT NULL,
  `max_hr` int DEFAULT NULL,
  `time` time DEFAULT NULL,
  `fast_distance` double(5,2) DEFAULT NULL,
  `recover_distance` double(5,2) DEFAULT NULL,
  `fast_time` time DEFAULT NULL,
  `recover_time` time DEFAULT NULL,
  `interval_quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `training_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `act_distance` double(5,2) DEFAULT NULL,
  `act_hr` int DEFAULT NULL,
  `act_time` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `goal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `start_date` DATE DEFAULT NULL,
  `end_date` DATE DEFAULT NULL,
  `distance` double(5,2) DEFAULT NULL,
  `distance_progress` double(5,2) DEFAULT NULL,
  `time` time DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `quantity_progress` int DEFAULT NULL,
  `is_done` BOOLEAN DEFAULT false,
  PRIMARY KEY (`id`),
  constraint `FK_USER_GOAL` FOREIGN KEY (`user_id`) references `user` (`id`)
  on delete cascade on update cascade
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `series` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  constraint `FK_USER_SERIES` FOREIGN KEY (`user_id`) references `user` (`id`)
  on delete cascade on update cascade
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

