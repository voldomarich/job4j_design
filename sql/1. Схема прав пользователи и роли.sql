create database tracker;

create table role(
    id serial primary key,
    name varchar(255)
);

create table users(
    id serial primary key,
    name varchar(255),
    role_id int references role(id)
);

insert into role(name) values ('композитор');
insert into role(name) values ('исполнитель');

insert into users(name, role_id) VALUES ('Пётр', 1);

insert into users(name, role_id) VALUES ('Варвара', 2);
insert into users(name, role_id) VALUES ('Кирилл', 2);

select * from users;
select * from role where id in (select id from users);


 create table rules(
     id serial primary key,
     name varchar(255)
 );

 create table role_rules(
     id serial primary key,
     rules_id int references rules(id),
	 role_id int references role(id)
 );
 
 insert into rules(name) values ('N');
 select * from role_rules;
 
create table category(
    id serial primary key,
    name varchar(255)
);

create table state(
    id serial primary key,
    name varchar(255)
);

create table item(
    id serial primary key,
    name varchar(255),
	users_id int references users(id),
	category_id int references category(id),
	state_id int references state(id),
);

insert into category(name) values ('первая категория');
insert into category(name) values ('вторая категория');
insert into state(name) values ('+');
insert into state(name) values ('-');
insert into item(name, users_id) VALUES ('Море', 1);
insert into item(name, users_id) VALUES ('Гора', 2);
insert into item(name, category_id) VALUES ('Море', 1);
insert into item(name, state_id) VALUES ('Море', 1);
insert into item(name, category_id) VALUES ('Гора', 2);
insert into item(name, state_id) VALUES ('Гора', 2);
insert into item(name, category_id) VALUES ('Море', 2);
insert into item(name, state_id) VALUES ('Море', 2);

select * from item;
select * from users where id in (select id from item);
select * from category where id in (select id from item);
select * from state where id in (select id from item);

create table comments(
    id serial primary key,
    name varchar(255),
	item_id int references item(id),
);

insert into comments(name, item_id) VALUES ('nnn', 1);
insert into comments(name, item_id) VALUES ('ppp', 2);

select * from comments;
select * from item where id in (select id from comments);

create table attachs(
    id serial primary key,
    name varchar(255),
	item_id int references item(id),
);

insert into attachs(name, item_id) VALUES ('A', 1);
insert into attachs(name, item_id) VALUES ('B', 2);

select * from attachs;
select * from item where id in (select id from attachs);

