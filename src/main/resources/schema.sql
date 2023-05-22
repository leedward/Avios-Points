CREATE TABLE IF NOT EXISTS `airport`
(
    `code` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`code`)
);

CREATE TABLE IF NOT EXISTS `cabin`
(
    `code`  VARCHAR(50) NOT NULL,
    `bonus` INT         NOT NULL,
    PRIMARY KEY (`code`)
);

CREATE TABLE IF NOT EXISTS `route`
(
    `id`                     BIGINT AUTO_INCREMENT NOT NULL,
    `departure_airport_code` VARCHAR(50),
    `arrival_airport_code`   VARCHAR(50),
    `avios`                  INT                   NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`departure_airport_code`) REFERENCES `airport` (`code`),
    FOREIGN KEY (`arrival_airport_code`) REFERENCES `airport` (`code`),
    UNIQUE (`departure_airport_code`, `arrival_airport_code`)
);
