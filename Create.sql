create table role(
    id serial primary key,
    name varchar(255)
);

create table users(
    id serial primary key,
    name varchar(255),
    role_id int references role(id)
);

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
	state_id int references state(id)
);

select * from item;
select * from users where id in (select id from item);
select * from category where id in (select id from item);
select * from state where id in (select id from item);

create table comments(
    id serial primary key,
    name varchar(255),
	item_id int references item(id)
);

select * from comments;
select * from item where id in (select id from comments);

create table attachs(
    id serial primary key,
    name varchar(255),
	item_id int references item(id)
);

select * from attachs;
select * from item where id in (select id from attachs);
