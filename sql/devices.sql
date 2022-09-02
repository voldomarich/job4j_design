create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('TV set', 800), ('TV set', 1000), ('Smartphone', 5500), ('PC', 8200), ('Watch', 2000);
insert into people(name) values ('Иван'), ('Илья'), ('Игорь');
insert into devices_people(device_id, people_id) values (1, 2), (1, 3);
insert into devices_people(device_id, people_id) values (2, 1), (2, 3);
insert into devices_people(device_id, people_id) values (3, 1), (3, 2), (3, 3);
insert into devices_people(device_id, people_id) values (4, 1), (4, 2), (4, 3);
insert into devices_people(device_id, people_id) values (5, 2), (5, 3);

select avg(price) from devices;
select min(price) from devices;
select max(price) from devices;

select p.name, avg()
from devices_people as dp
join people as p
on dp.people_id = p.id 
group by p.name;

select p.name, avg()
from devices_people as dp
join people as p
on dp.people_id = p.id 
group by p.name
having avg(d.price) > 5000;
