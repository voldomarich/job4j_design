create table role(
     id serial primary key,
     name varchar(255)
 );

 create table rules(
     id serial primary key,
     name varchar(255)
 );

 create table role_rules(
     id serial primary key,
     rules_id int references rules(id),
     role_id int references role(id)
 );
 