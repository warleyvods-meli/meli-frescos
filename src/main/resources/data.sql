INSERT INTO `country_houses` (`id`, `country`, `name`) VALUES ('1', 'Argentina', 'Casa central de Argentina');
INSERT INTO `country_houses` (`id`, `country`, `name`) VALUES ('2', 'Chile', 'Casa central de Chile');
INSERT INTO `country_houses` (`id`, `country`, `name`) VALUES ('3', 'Uruguay', 'Casa central de Uruguay');
INSERT INTO `country_houses` (`id`, `country`, `name`) VALUES ('4', 'Colombia', 'Casa central de Colombia');


INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `id_country_house_fk`) VALUES ('1', 'contra123', '1', 'user_one', '1');
INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `id_country_house_fk`) VALUES ('2', 'contra123', '1', 'user_two', '2');
INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `id_country_house_fk`) VALUES ('3', 'contra123', '1', 'user_three', '3');
INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `id_country_house_fk`) VALUES ('4', 'contra123', '1', 'user_four', '4');

--======================================================
--                   MOCK DATA
--======================================================

--AGENT
INSERT INTO agent (id, email, name, password, user_type) VALUES (1, 'email@email.com', 'Marcos', '123', 'String');
INSERT INTO agent (id, email, name, password, user_type) VALUES (2, 'email@email.com', 'Antonio', '123', 'String');
INSERT INTO agent (id, email, name, password, user_type) VALUES (3, 'email@email.com', 'Jose', '123', 'String');
INSERT INTO agent (id, email, name, password, user_type) VALUES (4, 'email@email.com', 'Pedro', '123', 'String');

--SELLER
INSERT INTO seller(id, email, name, password, user_type) VALUES (1, 'seller@seller', 'Maicao', '123', 'String');
INSERT INTO seller(id, email, name, password, user_type) VALUES (2, 'seller@seller', 'Perigo', '123', 'String');

--PRODUCT
INSERT INTO product(id, category, product_name, seller_id) VALUES(1, 'Frios', 'Carne', 1);
INSERT INTO product(id, category, product_name, seller_id) VALUES(2, 'Frios', 'Legumes', 2);
INSERT INTO product(id, category, product_name, seller_id) VALUES(3, 'Frescos', 'Frutas', 2);

--WAREHOUSE
INSERT INTO warehouse(id, name) VALUES(1, 'Cajamar Fullfilment');
INSERT INTO warehouse(id, name) VALUES(2, 'Bahia Fullfilment');

--SECTION
INSERT INTO `section`(id, section_name, warehouse_id)VALUES(1, 'Rua 1', 1);
INSERT INTO `section`(id, section_name, warehouse_id)VALUES(2, 'Rua 2', 1);
INSERT INTO `section`(id, section_name, warehouse_id)VALUES(3, 'Rua 3', 1);
INSERT INTO `section`(id, section_name, warehouse_id)VALUES(4, 'Rua 4', 2);
INSERT INTO `section`(id, section_name, warehouse_id)VALUES(5, 'Rua 5', 2);
INSERT INTO `section`(id, section_name, warehouse_id)VALUES(6, 'Rua 6', 2);

--BATCH-STOCK
INSERT INTO batch_stock(id) VALUES (1);
INSERT INTO batch_stock(id) VALUES (2);

--ORDER IN
INSERT INTO order_in (id, order_date, batch_stock_id, id_section_fk) VALUES(1, '2021-01-01 00:00:00', 1, 1);
INSERT INTO order_in (id, order_date, batch_stock_id, id_section_fk) VALUES(2, '2021-01-01 00:00:00', 2, 2);

--STOCK
INSERT INTO stock(id, current_quantity, current_temperature, due_date, initial_quantity, manufacturing_date, manufacturing_time, minimum_temperature, batchstock_id) VALUES(1, 20, 25.0, '2021-01-01', 50, '2021-01-01', '2021-01-01 00:00:00', 10.0, 1);
INSERT INTO stock(id, current_quantity, current_temperature, due_date, initial_quantity, manufacturing_date, manufacturing_time, minimum_temperature, batchstock_id) VALUES(2, 20, 25.0, '2021-01-01', 50, '2021-01-01', '2021-01-01 00:00:00', 10.0, 2);
