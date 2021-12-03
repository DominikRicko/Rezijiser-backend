SET NAMES utf8;
SET time_zone = `+00:00`;
SET foreign_key_checks = 0;
SET sql_mode = `NO_AUTO_VALUE_ON_ZERO`;

ALTER TABLE user ADD password varchar(64) CHARACTER SET utf16 NOT NULL;