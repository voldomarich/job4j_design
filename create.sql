create table car(
    id serial primary key,
    name varchar(255)
);

create table matrícula(
    id serial primary key,
    number int
);

create table car_matrícula(
    id serial primary key,
    car_id int references car(id) unique,
    matrícula_id int references matrícula(id) unique
);