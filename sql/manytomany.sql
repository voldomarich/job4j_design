create table son(
    id serial primary key,
    name varchar(255)
);

create table father(
    id serial primary key,
    name varchar(255),
    son_id int references son(id)
);

insert into son(name) values ('Ilya');
insert into father(name, son_id) VALUES ('Igor', 1);

insert into son(name) values ('Ivan');
insert into father(name, son_id) VALUES ('Igor', 2);

select * from father;

select * from son where id in (select id from father);