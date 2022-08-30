create table teens(
    id serial primary key,
    name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values('Алексей', 'male');
insert into teens(name, gender) values('Александр', 'male');
insert into teens(name, gender) values('Артур', 'male');
insert into teens(name, gender) values('Андрей', 'male');
insert into teens(name, gender) values('Дмитрий', 'male');
insert into teens(name, gender) values('Владимир', 'male');
insert into teens(name, gender) values('Антон', 'male');
insert into teens(name, gender) values('Игорь', 'male');
insert into teens(name, gender) values('Евгений', 'male');
insert into teens(name, gender) values('Марина', 'female');
insert into teens(name, gender) values('Александра', 'female');
insert into teens(name, gender) values('Виктория', 'female');
insert into teens(name, gender) values('Римма', 'female');
insert into teens(name, gender) values('Лидия', 'female');
insert into teens(name, gender) values('Любовь', 'female');
insert into teens(name, gender) values('Татьяна', 'female');
insert into teens(name, gender) values('Лилия', 'female');
insert into teens(name, gender) values('Карина', 'female');


select n1.name as m, n2.name as f, (n1.name, n2.name) as paar
from teens n1 cross join teens n2 where n1.gender = 'female' and n1.gender != n2.gender;

