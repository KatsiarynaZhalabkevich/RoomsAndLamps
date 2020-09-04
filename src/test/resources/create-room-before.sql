DELETE FROM room;
INSERT INTO room (id, name, lamp_on, country_id)
VALUES (1, 'Room 1 test',0, 1),
       (2, 'Room 2 test',0, 1),
       (3, 'Room 3 test',0, 3),
       (4, 'Room 4 test',0, 4);

ALTER SEQUENCE hibernate_sequence restart WITH 5;