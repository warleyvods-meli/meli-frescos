-- INSERT INTO `country_houses` (`id`, `country`, `name`) VALUES ('1', 'Argentina', 'Casa central de Argentina');
-- INSERT INTO `country_houses` (`id`, `country`, `name`) VALUES ('2', 'Chile', 'Casa central de Chile');
-- INSERT INTO `country_houses` (`id`, `country`, `name`) VALUES ('3', 'Uruguay', 'Casa central de Uruguay');
-- INSERT INTO `country_houses` (`id`, `country`, `name`) VALUES ('4', 'Colombia', 'Casa central de Colombia');


-- INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `admin`) VALUES ('1', 'contra123', '1', 'user_one',  true);
-- INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `admin`) VALUES ('2', 'contra123', '1', 'user_two',  true);
-- INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `admin`) VALUES ('3', 'contra123', '1', 'user_three', true);
-- INSERT INTO `accounts` (`id`, `password`, `rol`, `username`, `admin`) VALUES ('4', 'contra123', '1', 'user_four',  true);

--======================================================
--                   MOCK DATA
--======================================================


--SELLER
INSERT INTO seller(id, email, name, password, user_type) VALUES (1, 'seller1@email.com', 'Maicao', '123', 'SELLER');
INSERT INTO seller(id, email, name, password, user_type) VALUES (2, 'seller2@email.com', 'Perigo', '123', 'SELLER');

--BUYER
INSERT INTO buyer(id, email, name, password, user_type) VALUES (1, 'buyer1@email.com', 'Neide', '123', 'BUYER');
INSERT INTO buyer(id, email, name, password, user_type) VALUES (2, 'buyer2@email.com', 'Valdo', '123', 'BUYER');

--PRODUCT
INSERT INTO product(id, category, product_name, seller_id, price) VALUES(1, 'FROZEN', 'Carne File Mignon', 1, 10);
INSERT INTO product(id, category, product_name, seller_id, price) VALUES(2, 'AIRY', 'Repolho do mato', 2, 15);
INSERT INTO product(id, category, product_name, seller_id, price) VALUES(3, 'CHILLED', 'Frutas', 2, 10);
INSERT INTO product(id, category, product_name, seller_id, price) VALUES(4, 'FROZEN', 'Peixe Bacalhau', 1, 15.5);
INSERT INTO product(id, category, product_name, seller_id, price) VALUES(5, 'FROZEN', 'Peixe Bacalhau', 1, 15.5);

--WAREHOUSE
INSERT INTO warehouse(id, name) VALUES(1, 'Cajamar Fullfilment');
INSERT INTO warehouse(id, name) VALUES(2, 'Bahia Fullfilment');

--AGENT
INSERT INTO agent (id, email, name, password, user_type, warehouse_id) VALUES (1, 'marcos@email.com', 'Marcos', '123', 'AGENT', 1);
INSERT INTO agent (id, email, name, password, user_type, warehouse_id) VALUES (2, 'antonio@email.com', 'Antonio', '123', 'AGENT', 1);
INSERT INTO agent (id, email, name, password, user_type, warehouse_id) VALUES (3, 'jose@email.com', 'Jose', '123', 'AGENT', 2);
INSERT INTO agent (id, email, name, password, user_type, warehouse_id) VALUES (4, 'pedro@email.com', 'Pedro', '123', 'AGENT', 2);

--SECTION
INSERT INTO `section`(id, section_name, capacity, warehouse_id) VALUES(1, 'CHILLED', 100, 1);
INSERT INTO `section`(id, section_name, capacity, warehouse_id) VALUES(2, 'FROZEN', 100, 1);
INSERT INTO `section`(id, section_name, capacity, warehouse_id) VALUES(3, 'AIRY', 2, 1);
INSERT INTO `section`(id, section_name, capacity, warehouse_id) VALUES(4, 'CHILLED', 100, 2);
INSERT INTO `section`(id, section_name, capacity, warehouse_id) VALUES(5, 'FROZEN', 100, 2);
INSERT INTO `section`(id, section_name, capacity, warehouse_id) VALUES(6, 'AIRY', 100, 2);


--ORDER IN
INSERT INTO inbound_order (id, order_date, order_number, agent_id, id_section_fk) VALUES(1, CURRENT_TIMESTAMP(), 123, 1, 1);
INSERT INTO inbound_order (id, order_date, order_number, agent_id, id_section_fk) VALUES(2, CURRENT_TIMESTAMP(), 321, 1, 2);

--STOCK
INSERT INTO stock (id, batch_number, current_quantity, current_temperature, due_date, initial_quantity, manufacturing_date, manufacturing_time, minimum_temperature, order_number, product_id, section_id) VALUES(1, 666, 20, 25.0, '2021-07-07', 50, '2021-07-07', '2021-01-07 00:00:00', 10.0, 123, 1, 1);
INSERT INTO stock (id, batch_number, current_quantity, current_temperature, due_date, initial_quantity, manufacturing_date, manufacturing_time, minimum_temperature, order_number, product_id, section_id) VALUES(2, 777, 10, 25.0, '2021-07-01', 50, '2021-06-07', '2021-01-07 00:00:00', 10.0, 321, 2, 2);
INSERT INTO stock (id, batch_number, current_quantity, current_temperature, due_date, initial_quantity, manufacturing_date, manufacturing_time, minimum_temperature, order_number, product_id, section_id) VALUES(3, 666, 19, 25.0, '2021-01-02', 50, '2021-01-07', '2021-01-01 00:00:00', 10.0, 123, 3, 1);
INSERT INTO stock (id, batch_number, current_quantity, current_temperature, due_date, initial_quantity, manufacturing_date, manufacturing_time, minimum_temperature, order_number, product_id, section_id) VALUES(4, 666, 20, 25.0, '2021-01-06', 50, '2021-01-07', '2021-01-01 00:00:00', 10.0, 123, 4, 6);
INSERT INTO stock (id, batch_number, current_quantity, current_temperature, due_date, initial_quantity, manufacturing_date, manufacturing_time, minimum_temperature, order_number, product_id, section_id) VALUES(5, 666, 20, 25.0, '2021-01-06', 50, '2021-01-07', '2021-01-01 00:00:00', 10.0, 123, 5, 6);
