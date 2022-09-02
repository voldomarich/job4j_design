create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('Седан');
insert into car_bodies(name) values ('Кроссовер');
insert into car_bodies(name) values ('Внедорожник');
insert into car_bodies(name) values ('Кабриолет');
insert into car_bodies(name) values ('Пикап');

insert into car_engines(name) values ('бензин 1.6 л');
insert into car_engines(name) values ('бензин 1.8 л');
insert into car_engines(name) values ('бензин 2.0 л');
insert into car_engines(name) values ('дизель 1.6 л');
insert into car_engines(name) values ('дизель 1.8 л');
insert into car_engines(name) values ('дизель 2.0 л');
insert into car_engines(name) values ('бензин 2.4 л');
insert into car_engines(name) values ('бензин 2.8 л');
insert into car_engines(name) values ('дизель 3.0 л');
insert into car_engines(name) values ('бензин 5.6 л');

insert into car_transmissions(name) values ('МКПП переднеприводная');
insert into car_transmissions(name) values ('АКПП полноприводная');
insert into car_transmissions(name) values ('АКПП переднеприводная');
insert into car_transmissions(name) values ('АКПП заднеприводная');
insert into car_transmissions(name) values ('Робот переднеприводный');

insert into cars(name, body_id, engine_id, transmission_id) values ('Toyota Corolla', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('Opel Insignia', 1, 6, 3);
insert into cars(name, body_id, engine_id, transmission_id) values ('Volkswagen Polo', 1, 2, 3);
insert into cars(name, body_id, transmission_id) values ('Mercedes Maybach', 1, 5);
insert into cars(name, body_id, engine_id, transmission_id) values ('Toyota Fortuner', 3, 8, 4);
insert into cars(name, body_id, engine_id, transmission_id) values ('Honda Crosstour', 2, 7, 3);
insert into cars(name, body_id, engine_id, transmission_id) values ('Infiniti QX80', 3, 10, 3);
insert into cars(name, body_id, engine_id, transmission_id) values ('Porsche Macan', 2, 9, 4);
insert into cars(name, body_id, engine_id, transmission_id) values ('Nissan Pathfinder', 3, 5, 3);
insert into cars(name, body_id, engine_id, transmission_id) values ('Jaguar XE', 1, 3, 5);

select c.name, cb.name, ce.name, ct.name 
from cars c
left join car_bodies cb
on c.body_id = cb.id
left join car_engines ce
on c.engine_id = ce.id
left join car_transmissions ct
on c.transmission_id = ct.id;


select cb.name
from cars c
right join car_bodies cb
on c.body_id = cb.id
where c.body_id is null;

select ce.name
from cars c
right join car_engines ce
on c.engine_id = ce.id
where c.engine_id is null;

select ct.name
from cars c
right join car_transmissions ct
on c.transmission_id = ct.id
where c.transmission_id is null;


