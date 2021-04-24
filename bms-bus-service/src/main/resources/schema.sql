INSERT INTO `bms_bus_service`.`buses`(id, name, seatNo) values(1,'Bus1', 10);
INSERT INTO `bms_bus_service`.`buses`(id, name, seatNo) values(2,'Bus2', 15);
INSERT INTO `bms_bus_service`.`buses`(id, name, seatNo) values(3,'Bus3', 20);


INSERT INTO `bms_bus_service`.`cities`
(`id`,
`latitude`,
`longitude`,
`name`)
VALUES
(1,
54.687157,
25.279652,
'Vilnius');

INSERT INTO `bms_bus_service`.`cities`
(`id`,
`latitude`,
`longitude`,
`name`)
VALUES
(2,
54.896870,
23.892429,
'Kaunas');

INSERT INTO `bms_bus_service`.`cities`
(`id`,
`latitude`,
`longitude`,
`name`)
VALUES
(3,
55.932080,
23.314220,
'Šiauliai');

INSERT INTO `bms_bus_service`.`routes`
(`id`,
`from_city_id`,
`bus_id`,
`to_city_id`)
VALUES
(1,
1,
1,
2);

INSERT INTO `bms_bus_service`.`routes`
(`id`,
`from_city_id`,
`bus_id`,
`to_city_id`)
VALUES
(2,
1,
2,
3);

INSERT INTO `bms_bus_service`.`routes`
(`id`,
`from_city_id`,
`bus_id`,
`to_city_id`)
VALUES
(3,
2,
3,
3);

INSERT INTO `bms_bus_service`.`timetable`
(`id`,
`hour`,
`minute`,
`route_id`)
VALUES
(1,
7,
0,
1);

INSERT INTO `bms_bus_service`.`timetable`
(`id`,
`hour`,
`minute`,
`route_id`)
VALUES
(2,
9,
0,
1);

INSERT INTO `bms_bus_service`.`timetable`
(`id`,
`hour`,
`minute`,
`route_id`)
VALUES
(7,
9,
0,
2);

INSERT INTO `bms_bus_service`.`timetable`
(`id`,
`hour`,
`minute`,
`route_id`)
VALUES
(8,
10,
0,
1);

INSERT INTO `bms_bus_service`.`timetable`
(`id`,
`hour`,
`minute`,
`route_id`)
VALUES
(9,
11,
0,
2);

INSERT INTO `bms_bus_service`.`timetable`
(`id`,
`hour`,
`minute`,
`route_id`)
VALUES
(10,
13,
0,
2);

INSERT INTO `bms_bus_service`.`timetable`
(`id`,
`hour`,
`minute`,
`route_id`)
VALUES
(11,
14,
0,
2);

INSERT INTO `bms_bus_service`.`timetable`
(`id`,
`hour`,
`minute`,
`route_id`)
VALUES
(12,
16,
0,
2);

INSERT INTO `bms_bus_service`.`timetable`
(`id`,
`hour`,
`minute`,
`route_id`)
VALUES
(3,
11,
0,
1);

INSERT INTO `bms_bus_service`.`timetable`
(`id`,
`hour`,
`minute`,
`route_id`)
VALUES
(4,
14,
0,
1);

INSERT INTO `bms_bus_service`.`timetable`
(`id`,
`hour`,
`minute`,
`route_id`)
VALUES
(5,
16,
0,
1);

INSERT INTO `bms_bus_service`.`timetable`
(`id`,
`hour`,
`minute`,
`route_id`)
VALUES
(6,
18,
0,
1);


