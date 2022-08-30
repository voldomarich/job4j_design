create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
	departments_id int references departments(id)
);

insert into departments(name) values ('ОБРАЗОВАНИЕ');
insert into departments(name) values ('ЗДРАВООХРАНЕНИЕ');
insert into departments(name) values ('ЭКОЛОГИЯ');

insert into employees(name, departments_id) values ('Алексей', 1);
insert into employees(name, departments_id) values ('Александр', 1);
insert into employees(name, departments_id) values ('Владимир', 1);
insert into employees(name, departments_id) values ('Игорь', 1);
insert into employees(name, departments_id) values ('Роман', null);
insert into employees(name, departments_id) values ('Василий', null);
insert into employees(name, departments_id) values ('Антон', 2);
insert into employees(name, departments_id) values ('Дмитрий', 2);
insert into employees(name, departments_id) values ('Всеволод', 2);
insert into employees(name, departments_id) values ('Анна', 2);
insert into employees(name, departments_id) values ('Александра', 3);
insert into employees(name, departments_id) values ('Марина', 3);
insert into employees(name, departments_id) values ('Лилия', 3);
insert into employees(name, departments_id) values ('Евгений', 3);


select * from employees e left join departments d on e.departments_id = d.id;
select * from departments d right join employees e on d.id = e.departments_id;

select * from departments d left join employees e on d.id = e.departments_id;
select * from employees e right join departments d on e.departments_id = d.id;

select * from employees e left join departments d on e.departments_id = d.id where d.id is null;

select * from employees e full join departments d on e.departments_id = d.id;

select * from employees e left join departments d on e.departments_id = d.id
union
select * from employees e left join departments d on e.departments_id = d.id;


select * from employees e cross join departments d;
