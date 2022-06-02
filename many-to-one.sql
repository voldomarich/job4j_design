create table father(
    id serial primary key,
    name varchar(255)
);

create table son(
    id serial primary key,
    name varchar(255),
    father_id int references father(id)
);

insert into father(name) values ('Igor');
insert into father(name) values ('Ippolit');
insert into son(name, father_id) VALUES ('Ilya', 1);
insert into son(name, father_id) VALUES ('Ivan', 1);
insert into son(name, father_id) VALUES ('Invar', 2);

select * from son;

select * from father where id in (select id from son);
