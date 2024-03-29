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
insert into products (name, producer, count, price) VALUES ('робот-пылесос', 'Takira', 14, 87000);

create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();
		
	
create or replace function tax()
    returns trigger as
$$
    BEGIN
        
        new.price = new.price + new.price * 0.2;
        return new;
		
    END;
$$
LANGUAGE 'plpgsql';

create trigger t 
before insert 
on products
for each row
execute procedure tax();


create or replace function add()
    returns trigger as
$$
    BEGIN
	
        insert into history_of_price(name, price, date)
        values(new.name, new.price, current_date);
		
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create trigger t2 
after insert 
on products
for each row
execute procedure add();

insert into products (name, producer, count, price) VALUES ('триммер', 'J&J', 80, 2100);
insert into products (name, producer, count, price) VALUES ('электробритва', 'C', 20, 1500);

select * from history_of_price;

select * from products;

