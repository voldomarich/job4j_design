create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) VALUES ('глюкометр', 'J&J', 83, 600);
insert into products (name, producer, count, price) VALUES ('термометр', 'C', 20, 280);
insert into products (name, producer, count, price) VALUES ('напольные весы', 'BJ', 28, 4200);


begin transaction;

insert into products (name, producer, count, price) VALUES ('робот-пылесос', 'Takira', 14, 87000);

commit transaction;

select * from products;



begin transaction;

insert into products (name, producer, count, price) VALUES ('электронные часы', 'Takira', 10, 22200);

savepoint savepoint_one;

delete from products where price = 600;
update products set price = price + 100 where name = 'напольные весы' and name = 'электронные часы';

select * from products;

rollback to savepoint_one;

select * from products;

commit transaction;



begin transaction;

insert into products (name, producer, count, price) VALUES ('смарт-часы', 'Apple Watch', 20, 42000);

savepoint savepoint_two;

update products set count = count + 20 where name = 'глюкометр' and name = 'термометр';

select * from products;


															
savepoint savepoint_three;

insert into products (name, producer, count, price) VALUES ('глобус', 'Earth', 24, 2500);

update products set count = count + 20 where name = 'робот-пылесос';

select * from products;

															
rollback to savepoint_two;

select * from products;
																											

															
savepoint savepoint_four;														

insert into products (name, producer, count, price) VALUES ('вентилятор', 'TheWind', 20, 4800);
insert into products (name, producer, count, price) VALUES ('обогреватель', 'TheWind', 20, 2800);

update products set count = count - 10 where name = 'робот-пылесос';
delete from products where name = 'глобус';

select * from products;														
															
	
															
savepoint savepoint_five;														

delete from products;
drop table products;														


rollback to savepoint_four;

select * from products;

commit transaction;





