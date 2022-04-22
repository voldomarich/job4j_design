create table countries(
	id serial primary key,
	name varchar(128),
	date date,
	description text,
	population int,
	language char(64)
);

insert into countries(name, description, language) values('Бразилия', 'государство', 'португальский');
select * from countries;

insert into countries(date, population) values('01.03.1502', '210');
select * from countries;

update countries set description = 'Страна в Южной Америке с тропическим климатом';
select * from countries;

delete from countries;
