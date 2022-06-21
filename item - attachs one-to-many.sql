create table attachs(
    id serial primary key,
    name varchar(255)
);

create table item(
    id serial primary key,
    name varchar(255),
    attachs_id int references attachs(id)
);

insert into attachs(name) values ('j');
insert into attachs(name) values ('c');
insert into item(name, comments_id) VALUES ('q', 1);
insert into item(name, comments_id) VALUES ('d', 1);
insert into item(name, comments_id) VALUES ('f', 2);

select * from item;

select * from attachs where id in (select id from attachs);
