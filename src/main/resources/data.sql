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


--SELLER
INSERT INTO seller(id, email, name, password, user_type) VALUES (1, 'seller1@email.com', 'Maicao', '123', 'SELLER');
INSERT INTO seller(id, email, name, password, user_type) VALUES (2, 'seller2@email.com', 'Perigo', '123', 'SELLER');

--BUYER
INSERT INTO buyer(id, email, name, password, user_type) VALUES (1, 'buyer1@email.com', 'Neide', '123', 'BUYER');
INSERT INTO buyer(id, email, name, password, user_type) VALUES (2, 'buyer2@email.com', 'Valdo', '123', 'BUYER');

--PRODUCT
INSERT INTO product(id, category, product_name, seller_id) VALUES(1, 'FROZEN', 'Carne', 1);
INSERT INTO product(id, category, product_name, seller_id) VALUES(2, 'AIRY', 'Legumes', 2);
INSERT INTO product(id, category, product_name, seller_id) VALUES(3, 'CHILLED', 'Frutas', 2);

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
INSERT INTO `section`(id, section_name, capacity, warehouse_id) VALUES(3, 'AIRY', 100, 1);
INSERT INTO `section`(id, section_name, capacity, warehouse_id) VALUES(4, 'CHILLED', 100, 2);
INSERT INTO `section`(id, section_name, capacity, warehouse_id) VALUES(5, 'FROZEN', 100, 2);
INSERT INTO `section`(id, section_name, capacity, warehouse_id) VALUES(6, 'AIRY', 100, 2);


--ORDER IN
INSERT INTO inbound_order (id, order_date, order_number, agent_id, id_section_fk) VALUES(1, '2021-01-01 00:00:00', 123, 1, 1);
INSERT INTO inbound_order (id, order_date, order_number, agent_id, id_section_fk) VALUES(2, '2021-01-01 00:00:00', 321, 1, 2);

--STOCK
INSERT INTO stock (id, batch_number, current_quantity, current_temperature, due_date, initial_quantity, manufacturing_date, manufacturing_time, minimum_temperature, order_number) VALUES(1, 666, 20, 25.0, '2021-01-01', 50, '2021-01-01', '2021-01-01 00:00:00', 10.0, 123);
INSERT INTO stock (id, batch_number, current_quantity, current_temperature, due_date, initial_quantity, manufacturing_date, manufacturing_time, minimum_temperature, order_number) VALUES(2, 777, 20, 25.0, '2021-01-01', 50, '2021-01-01', '2021-01-01 00:00:00', 10.0, 321);
