create table house_material(
    id serial primary key,
    name varchar(255)
);

create table house_windows(
    id serial primary key,
    name varchar(255)
);

create table house_neighbourhood(
    id serial primary key,
    name varchar(255)
);

create table house_status(
    id serial primary key,
    name varchar(255)
);

create table house_class(
    id serial primary key,
    name varchar(255)
);

create table houses(
    id serial primary key,
    name varchar(255),
	floors_count int,
	material_id int references house_material(id),
	window_id int references house_windows(id),
	neighbourhood_id int references house_neighbourhood(id),
	status_id int references house_status(id),
	class_id int references house_class(id)
);

insert into house_material(name) values ('Кирпичный');
insert into house_material(name) values ('Панельный');
insert into house_material(name) values ('Монолитный');

insert into house_windows(name) values ('Панорамные');
insert into house_windows(name) values ('Классические');
insert into house_windows(name) values ('Смешанные');

insert into house_neighbourhood(name) values ('Хамовники');
insert into house_neighbourhood(name) values ('Тверской');
insert into house_neighbourhood(name) values ('Раменки');
insert into house_neighbourhood(name) values ('Якиманка');
insert into house_neighbourhood(name) values ('Замоскворечье');
insert into house_neighbourhood(name) values ('Пресненский');

insert into house_status(name) values ('В процессе строительства');
insert into house_status(name) values ('Новостройка, сдана');
insert into house_status(name) values ('Вторичное жильё');
insert into house_status(name) values ('Под снос');

insert into house_class(name) values ('Премиум-класс');
insert into house_class(name) values ('Комфорт-класс');
insert into house_class(name) values ('Эконом-класс');


insert into houses(name, floors_count, material_id, window_id, neighbourhood_id, status_id, class_id) 
values ('A', 29, 3, 3, 4, 2, 2);
insert into houses(name, floors_count, material_id, window_id, neighbourhood_id, status_id, class_id)  
values ('B', 8, 1, 2, 6, 3, 1);
insert into houses(name, floors_count, material_id, window_id, neighbourhood_id, status_id, class_id) 
values ('C', 14, 2, 3, 1, 2, 2);
insert into houses(name, floors_count, material_id, window_id, neighbourhood_id, status_id, class_id)  
values ('D', 20, 3, 3, 2, 2, 2);
insert into houses(name, floors_count, material_id, window_id, neighbourhood_id, status_id, class_id)  
values ('E', 50, 2, 2, 3, 2, 3);
insert into houses(name, floors_count, material_id, window_id, neighbourhood_id, status_id, class_id)  
values ('G', 7, 1, 1, 1, 2, 1);
insert into houses(name, floors_count, material_id, window_id, neighbourhood_id, status_id, class_id)  
values ('J', 11, 2, 1, 5, 1, 2);
insert into houses(name, floors_count, material_id, window_id, neighbourhood_id, status_id, class_id)  
values ('Q', 27, 3, 3, 1, 2, 1);
insert into houses(name, floors_count, material_id, window_id, neighbourhood_id, status_id, class_id)  
values ('I', 4, 3, 3, 6, 3, 1);
insert into houses(name, floors_count, material_id, window_id, neighbourhood_id, status_id, class_id)  
values ('X', 29, 3, 3, 4, 4, 3);

create view show_houses_with_9_or_less_floors_in_Khamovniki
    as select h.name as house, h.floors_count as floors, hn.name as area from houses as h
         join house_material hm on hm.id = h.material_id
         join house_windows hw on hw.id = h.window_id
		 join house_neighbourhood hn on hn.id = h.neighbourhood_id
		 join house_status hs on hs.id = h.status_id
         join house_class hc on hc.id = h.class_id
         group by (h.name, h.floors_count, hn.name) having h.floors_count < 10 or hn.name = 'Хамовники';

alter view show_houses_with_9_or_less_floors_in_Khamovniki
rename to show_houses_with_less_than_10_floors_or_in_Khamovniki;

select * from show_houses_with_less_than_10_floors_or_in_Khamovniki;
		 