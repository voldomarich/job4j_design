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

insert into devices(name, price) values ('TV set', 8000), ('TV set', 11000), ('Smartphone', 14000), ('PC', 18200), ('Watch', 2000);
insert into people(name) values ('Иван'), ('Илья'), ('Игорь');
insert into devices_people(device_id, people_id) values (1, 2), (1, 3);
insert into devices_people(device_id, people_id) values (2, 1);
insert into devices_people(device_id, people_id) values (3, 1), (3, 2), (3, 3);
insert into devices_people(device_id, people_id) values (4, 1), (4, 3);
insert into devices_people(device_id, people_id) values (5, 1), (5, 2), (5, 3);

select avg(price) from devices;
select min(price) from devices;
select max(price) from devices;

select p.name, avg(d.price)
from devices_people as dp
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name
order by avg(d.price) desc;

select p.name, avg(d.price)
from devices_people as dp
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000
order by avg(d.price) desc;
