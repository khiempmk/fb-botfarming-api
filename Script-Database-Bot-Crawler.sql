CREATE TABLE `bot_profile` (
  `uid` varchar(255) PRIMARY KEY,
  `name` varchar(255),
  `phone` varchar(255),
  `password` varchar(255),
  `country` varchar(255),
  `education` varchar(255),
  `gender` varchar(255),
  `birthday` varchar(255),
  `number_friend` integer,
  `number_follow` integer,
  `source_avatar` varchar(255),
  `created_at` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP ,
  `updated_at` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP ,
  `last_time_dead` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP 
);

CREATE TABLE `bot_status` (
  `uid` varchar(255) PRIMARY KEY,
  `status` integer,
  `created_at` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP ,
  `updated_at` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP ,
  `unlocked_at` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP 
);

CREATE TABLE `bot_ip` (
  `uid` varchar(255) PRIMARY KEY,
  `ip` varchar(255),
  `subnet` varchar(255),
  `gateway` varchar(255),
  `proxy` varchar(255)
);

CREATE TABLE `bot_crawler` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `uid` varchar(255),
  `cookie` varchar(10000),
  `fb_dtsg` varchar(255),
  `created_at` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP ,
  `updated_at` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP 
);

CREATE TABLE `bot_otp` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `uid` varchar(255),
  `otp_sms` integer,
  `created_at` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP 
);

CREATE TABLE `bot_history` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `uid` varchar(255),
  `user_action` integer,
  `old_status` integer,
  `new_status` integer,
  `updated_at` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP ,
  `created_at` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP 
);

CREATE TABLE `users` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `user_login` varchar(255),
  `user_password` varchar(255),
  `created_at` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP ,
  `expired_date` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP ,
  `last_login` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP ,
  `status` integer
);

CREATE TABLE `job_bot` (
  `job_id` integer PRIMARY KEY AUTO_INCREMENT,
  `job_type` integer,
  `uid` varchar(255),
  `is_completed` boolean,
  `is_processed` boolean,
  `created_at` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP ,
  `updated_at` timestamp NOT NULL DEFAULT  CURRENT_TIMESTAMP,
  `user_action` integer
);

ALTER TABLE `bot_status` ADD FOREIGN KEY (`uid`) REFERENCES `bot_profile` (`uid`);

ALTER TABLE `bot_ip` ADD FOREIGN KEY (`uid`) REFERENCES `bot_profile` (`uid`);

ALTER TABLE `bot_crawler` ADD FOREIGN KEY (`uid`) REFERENCES `bot_profile` (`uid`);

ALTER TABLE `bot_otp` ADD FOREIGN KEY (`uid`) REFERENCES `bot_profile` (`uid`);

ALTER TABLE `bot_history` ADD FOREIGN KEY (`uid`) REFERENCES `bot_profile` (`uid`);

ALTER TABLE `bot_history` ADD FOREIGN KEY (`user_action`) REFERENCES `users` (`id`);

ALTER TABLE `job_bot` ADD FOREIGN KEY (`uid`) REFERENCES `bot_profile` (`uid`);

ALTER TABLE `job_bot` ADD FOREIGN KEY (`user_action`) REFERENCES `users` (`id`);
