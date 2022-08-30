create table users(
    id serial primary key,
    name varchar(255)
);

create table role(
    id serial primary key,
    name varchar(255),
    users_id int references users(id)
);

insert into users(name) values ('Miron');
insert into users(name) values ('Lev');
insert into role(name, users_id) VALUES ('Roman', 1);
insert into role(name, users_id) VALUES ('Ivan', 1);
insert into role(name, users_id) VALUES ('Petr', 2);

select * from role;

select * from item where id in (select id from role);

