CREATE TABLE `users` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `email` varchar(255),
  `password` varchar(255),
  `oauth_token` varchar(255),
  `is_admin` boolean
);

CREATE TABLE `services` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `token` varchar(255),
  `expiration_date` datetime
);

CREATE TABLE `widgets` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `service_id` int,
  `order` int,
  `column_pos` int
);

CREATE TABLE `widgets_paramaters` (
  `id` int PRIMARY KEY,
  `ordinal` int,
  `value` varchar(255)
);

ALTER TABLE `users` ADD FOREIGN KEY (`id`) REFERENCES `services` (`user_id`);

ALTER TABLE `services` ADD FOREIGN KEY (`id`) REFERENCES `widgets` (`service_id`);

ALTER TABLE `widgets` ADD FOREIGN KEY (`id`) REFERENCES `widgets_paramaters` (`id`);
