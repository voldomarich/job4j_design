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

call insert_data('product_2', 'producer_2', 15, 32);
call insert_data('глюкометр', 'J&J', 83, 600);
call insert_data('термометр', 'C', 20, 280);
call insert_data('напольные весы', 'BJ', 28, 4200);
call insert_data('робот-пылесос', 'Takira', 14, 87000);


create or replace procedure delete_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count = 0 THEN
            update products set count = 0;
        end if;
        if u_id = 4 THEN
            update products set count = 0;
        end if;
    END;
$$;


call delete_data(0, 0, 1);
call delete_data(10, 0, 4);






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


select f_insert_data('product_1', 'producer_1', 25, 120);
select f_insert_data('product_2', 'producer_1', 35, 30);
select f_insert_data('product_3', 'producer_1', 48, 80);




create or replace function f_delete_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count = 0 THEN
            update products set count = 0;
            select into result count from products where id = u_id;
        end if;
        if u_id = 2 THEN
            update products set count = 0;
            select into result sum(count) from products where id = u_id;
        end if;
        return result;
    end;
$$;

select f_delete_data(0, 0, 1);

select f_insert_data('product_2', 'producer_2', 101, 280);
select f_insert_data('product_3', 'producer_3', 14, 250);

select f_update_data(8, 0, 2);



