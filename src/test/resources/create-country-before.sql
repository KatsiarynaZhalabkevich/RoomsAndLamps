DELETE  FROM room;
DELETE FROM country;
INSERT INTO country (id,name) VALUES
(1,'Belarus'),
(2,'Russia'),
(3,'Ukraine'),
(4,'Poland');

ALTER SEQUENCE hibernate_sequence restart WITH 1;

