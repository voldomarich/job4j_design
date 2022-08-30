create table father(
    id serial primary key,
    name varchar(255),
    age int
);

create table son(
    id serial primary key,
    name varchar(255),
	age int,
    father_id int references father(id)
);

insert into father(name, age) values ('Igor', 35);
insert into father(name, age) values ('Alexey', 37);
insert into father(name, age) values ('Roman', 39);

insert into son(name, age, father_id) values ('Ivan', 5, 1);
insert into son(name, age, father_id) values ('Boris', 10, 2);
insert into son(name, age, father_id) values ('Petr', 1, 3);
insert into son(name, age, father_id) values ('Vasya', 4, 3);
insert into son(name, age, father_id) values ('Anya', 4, 3);
insert into son(name, age) values ('Artur', 4);
insert into son(name, age) values ('Andrey', 4);

select s.name, s.age, f.name, f.age 
from son as s join father as f on s.father_id = f.id;

select s.name as "Имя сына", s.age as "Возраст сына", f.name as "Имя отца", f.age as "Возраст отца" 
from son as s join father as f on s.father_id = f.id;

select s.name as "Имя сына", s.age as "Возраст сына", f.name as "Имя отца", f.age as "Возраст отца" 
from son s join father f on s.father_id = f.id;

