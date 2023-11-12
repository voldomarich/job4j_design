CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

INSERT INTO customers VALUES (1, 'Ренату', 'Ибейру', 20, 'Бразилия'),
                                  (2, 'Адам', 'Смит', 30, 'США'),
                                  (3, 'Акира', 'Куросава', 24, 'Япония'),
                                  (4, 'Иван', 'Иванов', 18, 'Россия');

SELECT * FROM customers
WHERE age < (SELECT AVG(age) FROM customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);


INSERT INTO orders VALUES (2, 87, 2),
                            (3, 111, 3),
                            (4, 100, 1);
							
SELECT * FROM customers
WHERE customers.id NOT IN (SELECT orders.id FROM orders);	
