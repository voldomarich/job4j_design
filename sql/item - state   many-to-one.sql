create table item(
    id serial primary key,
    name varchar(255)
);

create table state(
    id serial primary key,
    name varchar(255),
    item_id int references item(id)
);

insert into item(name) values ('N');
insert into item(name) values ('L');
insert into state(name, item_id) VALUES ('I', 1);
insert into state(name, item_id) VALUES ('V', 1);
insert into state(name, item_id) VALUES ('P', 2);

select * from state;

select * from item where id in (select id from state);
