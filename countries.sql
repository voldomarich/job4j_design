create table countries(
	id serial primary key,
	name varchar(128),
	date date,
	description text,
	population int,
	language char(64)
);
