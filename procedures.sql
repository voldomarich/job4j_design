create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;

call insert_data('обогреватель', 'TheWind', 15, 2800);
call insert_data('глюкометр', 'J&J', 83, 600);
call insert_data('термометр', 'C', 20, 280);
call insert_data('напольные весы', 'BJ', 28, 4200);
call insert_data('робот-пылесос', 'Takira', 14, 87000);

select * from products;


create or replace procedure delete_data(u_id integer)
language 'plpgsql'
as $$
    BEGIN
          delete from products where products.count < 16;
    END;
$$;


call delete_data(5);
call delete_data(2);

select * from products;


delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;




create or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;


select f_insert_data('обогреватель', 'TheWind', 15, 2800);
select f_insert_data('глюкометр', 'J&J', 83, 600);
select f_insert_data('термометр', 'C', 20, 280);
select f_insert_data('напольные весы', 'BJ', 28, 4200);
select f_insert_data('робот-пылесос', 'Takira', 14, 87000);

select * from products;


create or replace function f_delete_data(u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
          select into result sum(price) from products;
		  delete from products where i_count < 16;
        return result;
    end;
$$;

select f_delete_data(4);
select f_delete_data(2);
select f_delete_data(1);
select f_delete_data(5);

select * from products;

