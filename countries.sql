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

update countries 
set description = 'Страна в Южной Америке с тропическим климатом', 
date ='01.03.1502', population = '210'   where id = 1;

delete from countries;
