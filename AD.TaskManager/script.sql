DROP DATABASE IF EXISTS TaskManager;
CREATE DATABASE TaskManager;
USE TaskManager;


CREATE TABLE Categories
(
	`id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(255)
);

CREATE TABLE Tasks
(
	`id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`title` VARCHAR(255) NOT NULL,
	`description` VARCHAR(1024),
	`priority` INTEGER NOT NULL,
	`status` INTEGER NOT NULL,
	`init_date` DATE NOT NULL,
	`end_date` DATE NOT NULL,
	`level` INTEGER NOT NULL,
	`fk_category` INTEGER NOT NULL,
	`fk_dependency` INTEGER
);

ALTER TABLE Tasks
    ADD FOREIGN KEY (fk_category) REFERENCES Categories (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    ADD FOREIGN KEY (fk_dependency) REFERENCES Tasks (id)
        ON UPDATE CASCADE ON DELETE CASCADE;


DROP USER IF EXISTS taskManager@localhost;
CREATE USER TaskManager@localhost IDENTIFIED BY 'ABC-11abc22';

GRANT ALL PRIVILEGES ON TaskManager.* TO TaskManager@localhost;


