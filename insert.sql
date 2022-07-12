insert into role(name) values ('композитор');
insert into role(name) values ('исполнитель');
insert into users(name, role_id) VALUES ('Пётр', 1);
insert into users(name, role_id) VALUES ('Варвара', 2);
insert into users(name, role_id) VALUES ('Кирилл', 2);


insert into rules(name) values ('N');


insert into category(name) values ('первая категория');
insert into category(name) values ('вторая категория');
insert into state(name) values ('+');
insert into state(name) values ('-');
insert into item(name, users_id) VALUES ('Море', 1);
insert into item(name, users_id) VALUES ('Гора', 2);
insert into item(name, category_id) VALUES ('Море', 1);
insert into item(name, state_id) VALUES ('Море', 1);
insert into item(name, category_id) VALUES ('Гора', 2);
insert into item(name, state_id) VALUES ('Гора', 2);
insert into item(name, category_id) VALUES ('Море', 2);
insert into item(name, state_id) VALUES ('Море', 2);

insert into comments(name, item_id) VALUES ('nnn', 1);
insert into comments(name, item_id) VALUES ('ppp', 2);

insert into attachs(name, item_id) VALUES ('A', 1);
insert into attachs(name, item_id) VALUES ('B', 2);
