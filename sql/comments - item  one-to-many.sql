create table comments(
    id serial primary key,
    name varchar(255)
);

create table item(
    id serial primary key,
    name varchar(255),
    comments_id int references comments(id)
);

insert into comments(name) values ('j');
insert into comments(name) values ('c');
insert into item(name, comments_id) VALUES ('q', 1);
insert into item(name, comments_id) VALUES ('d', 1);
insert into item(name, comments_id) VALUES ('f', 2);

select * from item;

select * from comments where id in (select id from comments);
