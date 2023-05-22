INSERT IGNORE INTO `airport` (`code`)
VALUES ('LHR'),
       ('LAX'),
       ('SFO'),
       ('JFK'),
       ('LGW'),
       ('YYZ');

INSERT IGNORE INTO `cabin` (`code`, `bonus`)
VALUES ('M', 0),
       ('W', 20),
       ('J', 50),
       ('F', 100);

INSERT IGNORE INTO `route` (`departure_airport_code`, `arrival_airport_code`, `avios`)
VALUES ('LHR', 'LAX', 4500),
       ('LHR', 'SFO', 4400),
       ('LHR', 'JFK', 3200),
       ('LGW', 'YYZ', 3250);
