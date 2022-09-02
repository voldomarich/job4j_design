INSERT INTO fauna VALUES(1, 'reptile anaconda', 4000, date '1600-01-01');
INSERT INTO fauna VALUES(2, 'fish greenland polar shark', 90000, null);
INSERT INTO fauna VALUES(3, 'animal jaguar', 12000, null);
INSERT INTO fauna VALUES(4, 'fish walking shark', 11000, date '2006-01-01');
INSERT INTO fauna VALUES(5, 'bird himalayan thrush', 10000, date '2016-01-01');

SELECT * FROM fauna WHERE name LIKE 'fish%';
SELECT * FROM fauna WHERE avg_age BETWEEN 10000 AND 21000;
SELECT * FROM fauna WHERE discovery_date IS null;
SELECT * FROM fauna WHERE discovery_date < date '1950-01-01';
