create table item(
    id serial primary key,
    name varchar(255)
);

create table category(
    id serial primary key,
    name varchar(255),
    item_id int references item(id)
);

insert into item(name) values ('N');
insert into item(name) values ('L');
insert into category(name, item_id) VALUES ('I', 1);
insert into category(name, item_id) VALUES ('V', 1);
insert into category(name, item_id) VALUES ('P', 2);

select * from category;

select * from item where id in (select id from category);
