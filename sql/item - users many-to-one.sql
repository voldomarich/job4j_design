create table item(
    id serial primary key,
    name varchar(255)
);

create table users(
    id serial primary key,
    name varchar(255),
    item_id int references item(id)
);

insert into item(name) values ('Miron');
insert into item(name) values ('Lev');
insert into users(name, item_id) VALUES ('Ilya', 1);
insert into users(name, item_id) VALUES ('Ivan', 1);
insert into users(name, item_id) VALUES ('Invar', 2);

select * from users;

select * from item where id in (select id from users);
