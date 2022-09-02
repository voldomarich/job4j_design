create table type(
    id serial primary key,
    name varchar(255)
);

create table products(
    id serial primary key,
    name varchar(255),
	expired_date date,
    price float,
	type_id int references type(id)
);

insert into type(name) values ('СЫР');
insert into type(name) values ('МОЛОКО');
insert into type(name) values ('ЗАМОРОЖЕННЫЕ ПРОДУКТЫ');

insert into products(name, expired_date, price, type_id) values ('Сыр Тильзитер', '04.09.2022', 280, 1);
insert into products(name, expired_date, price, type_id) values ('Сыр Тильзитер', '04.09.2022', 280, 1);
insert into products(name, expired_date, price, type_id) values ('Сыр Тильзитер', '04.09.2022', 280, 1);
insert into products(name, expired_date, price, type_id) values ('Сыр Маасдам', '14.09.2022', 480, 1);
insert into products(name, expired_date, price, type_id) values ('Сыр Бри', '24.09.2022', 820, 1);
insert into products(name, expired_date, price, type_id) values ('Сыр Бри', '24.09.2022', 820, 1);
insert into products(name, expired_date, price, type_id) values ('Сыр Бри', '24.09.2022', 820, 1);
insert into products(name, expired_date, price, type_id) values ('Сыр Бри', '24.09.2022', 820, 1);
insert into products(name, expired_date, price, type_id) values ('Сыр Бри', '24.09.2022', 820, 1);
insert into products(name, expired_date, price, type_id) values ('Сыр Бри', '24.09.2022', 820, 1);
insert into products(name, expired_date, price, type_id) values ('Сыр Бри', '24.09.2022', 820, 1);
insert into products(name, expired_date, price, type_id) values ('Молоко А', '04.09.2022', 120, 2);
insert into products(name, expired_date, price, type_id) values ('Молоко А', '04.09.2022', 120, 2);
insert into products(name, expired_date, price, type_id) values ('Молоко А', '04.09.2022', 120, 2);
insert into products(name, expired_date, price, type_id) values ('Молоко А', '04.09.2022', 120, 2);
insert into products(name, expired_date, price, type_id) values ('Молоко А', '04.09.2022', 120, 2);
insert into products(name, expired_date, price, type_id) values ('Молоко А', '04.09.2022', 120, 2);
insert into products(name, expired_date, price, type_id) values ('Молоко А', '04.09.2022', 120, 2);
insert into products(name, expired_date, price, type_id) values ('Молоко А', '04.09.2022', 120, 2);
insert into products(name, expired_date, price, type_id) values ('Молоко А', '04.09.2022', 120, 2);
insert into products(name, expired_date, price, type_id) values ('Молоко А', '04.09.2022', 120, 2);insert into products(name, expired_date, price, type_id) values ('Молоко А', '04.09.2022', 120, 2);
insert into products(name, expired_date, price, type_id) values ('Молоко А', '04.09.2022', 120, 2);
insert into products(name, expired_date, price, type_id) values ('Молоко Б', '22.09.2022', 80, 2);
insert into products(name, expired_date, price, type_id) values ('Кефир А', '17.08.2022', 130, 2);
insert into products(name, expired_date, price, type_id) values ('Айран А', '28.08.2022', 100, 2);
insert into products(name, expired_date, price, type_id) values ('Мороженое Эскимо', '17.09.2022', 100, 3);
insert into products(name, expired_date, price, type_id) values ('Мороженое Пломбир', '05.09.2022', 80, 3);
insert into products(name, expired_date, price, type_id) values ('Мясо мороженое', '30.08.2022', 820, 3);
insert into products(name, expired_date, price, type_id) values ('Мясо мороженое', '30.08.2022', 820, 3);
insert into products(name, expired_date, price, type_id) values ('Пельмени', '06.08.2022', 450, 3);

select p.name
from products as p
join type as t
on p.type_id = t.id
group by t.name, p.name
having t.name = 'СЫР';

select * from products where name like 'Мороженое%' and name like '%мороженое';

select * from products where expired_date < current_date;

select name, max(price) from products group by price having price = max(price);

select t.name, count(p.name)
from products as p
join type as t
on p.type_id = t.id 
group by t.name
order by count(p.name) desc, t.name;

select t.name, p.name
from products as p
join type as t
on p.type_id = t.id
group by t.name, p.name
having t.name = 'СЫР'
or t.name = 'МОЛОКО';

select t.name, count(p.name)
from products as p
join type as t
on p.type_id = t.id 
group by t.name
having count(p.type_id) < 10
order by count(p.name) desc, t.name;

