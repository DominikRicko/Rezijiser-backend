DROP TABLE power;
DROP TABLE water;
DROP TABLE gas;
DROP TABLE communal;
DROP TABLE hrt;
DROP TABLE reservation;
DROP TABLE telecommunications;
DROP TABLE trash;

CREATE TABLE bill(
    `id` INT NOT NULL AUTO_INCREMENT,
    `id_user` INT NOT NULL,
    `type` VARCHAR(17) NOT NULL,
    `cost` DECIMAL(6,2) NOT NULL,
    `spent` DECIMAL(6,4),
    `payday` DATE NOT NULL,
    `date_paid` DATE NOT NULL,
    `time_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `time_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    PRIMARY KEY(id),
    CONSTRAINT `power_user_fk1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);