insert into project.users_table (username,email,name_user,password_user) values
('andrejtodorovski5','andrejtodorovski5@gmail.com','Andrej Todorovski','andrejpassword'),
('stefanmileski4','stefanmileski4@gmail.com','Stefan Mileski','stefanpassword'),
('hristijansazdovski3','hristijansazdovski3@gmail.com','Hristijan Sazdovski','hristijanpassword'),
('bojantrpeski2','bojantrpeski2@gmail.com','Bojan Trpeski','bojanpassword'),
('darkosasanski1','darkosasanski1@gmail.com','Darko Sasanski','darkopassword'),
('denicakjorvezir','denicakjorvezir@gmail.com','Denica Kjorvezir','denicapassword'),
('deliveryman1','deliveryman1@gmail.com','Petko Petkovski','petko123'),
('deliveryman2','deliveryman2@gmail.com','Marko Markovski','marko123'),
('deliveryman3','deliveryman3@gmail.com','Petar Petrovski','petar123'),
('warehouseman1','warehouseman1@gmail.com','Viktor Petrovski','viktor123'),
('warehouseman2','warehouseman2@gmail.com','Vlatko Petrovski','vlatko123'),
('warehouseman3','warehouseman3@gmail.com','Vido Petrovski','vido123');

insert into project.users_table (username,email,name_user,password_user,phone_number) values
('stefanmileski5','stefanmileski5@gmail.com','Stefan Mileski','stefanklient','070 123 456');

insert into project.administrator (id_user,authorized_from, authorized_till) values (1,'2022-01-01','2024-01-01'),(2,'2022-01-01','2025-01-01'),(3,'2022-01-01','2026-01-01');

insert into project.client (id_user) values (13),(4),(5),(6);

insert into project.delivery_man (id_user,employed_from) values (7,now()),(8,now()),(9,now());

insert into project.warehouse (warehouse_location) values ('Radishani'),('Kozle'),('Vizbegovo');

insert into project.warehouseman (id_user,employed_from,id_warehouse) values (10,now(),1),(11,now(),2),(12,now(),3);

insert into project.part_manufacturer (pm_name,pm_location) values
('Bosch','Germany'),
('Continental','Belgium'),
('Mahle','Netherlands');

insert into project.car_manufacturer (cm_name,cm_country) values
('Volkswagen','Germany'),
('Fiat','Italy'),
('Ford','USA'),
('Toyota','Japan'),
('Hyndai','Korea');

insert into project.repair_shop (rs_name,rs_location,rs_phone_number) values
('Toyota Service','Partizanska','070 123 456'),
('Volkswagen Service','Ilinenska','075 876 543'),
('Fiat Service','Vodnjanska','078 555 666'),
('Hyndai Service','Boris Trajkovski','075 500 000');

insert into project.car(in_production_since,in_production_till,car_type,id_car_manufacturer) values
(2001,2004,'Golf 4',1),
(2006,2009,'Punto',2),
(2004,2006,'Golf 5',1),
(2000,2008,'Fiesta',3),
(1995,2005,'Coupe',5);

insert into project.car_sample(vin,year_of_production, engine_power, displacement, fuel_type, km_driven, ID_user, ID_car) values
(1111,2008,65,1200,'Diesel',120000,4,4),
(2222,2010,80,1400,'Diesel',150000,5,4),
(3333,2016,65,1200,'Petrol/Gas',65000,6,2),
(4444,1997,140,2000,'Petrol/Gas',185000,13,5);

insert into project.part(part_name, id_part_manufacturer) values
('Engine G4GF',1),
('Wheel 20CM',2),
('Shift 6GEARS',3),
('Radio WITH AUX',1),
('Rear Bumper FOR VW GOLF 4',2),
('Headlight FOR PUNTO',3);

insert into project.category(category_name) values ('Brakes'),('Cooling System'),('Electrical'),('Engine components'),('Exterior'),('Filters');

insert into project.order_table(order_status,order_date,id_user) values 
('created',now(),4),
('created',now(),5),
('created',now(),6),
('created',now(),13);

insert into project.service_book (vin) values (1111),(2222),(3333),(4444);

insert into project.delivery (delivery_status, delivery_address,id_user,id_order) values 
('in progress','Aerodrom',7,3),
('finished','Pintija',8,1),
('in progress','Kisela Voda',9,2),
('in progress','Kozle',8,4);

insert into project.price (amount, price_from, id_part) values 
(1000,now(),2),
(2000,now(),3),
(3000,now(),1),
(1500,now(),4),
(3500,now(),5),
(300,now(),6);

insert into project.repair (vin, id_repair_shop, id_service_book) values (1111,3,1),(2222,2,2),(3333,1,3),(4444, 2, 4);
insert into project.repair (vin, id_repair_shop, id_service_book) values (1111,2,1)

insert into project.review (review_rating, id_repair, id_user) values (10, 1, 4), (6, 2, 5), (8, 3, 6), (9, 4, 13);
insert into project.review (review_rating, id_repair, id_user) values (7, 5, 4)

insert into project.part_is_from_category (id_part, id_category) values (5,5), (1,4), (6,5);

insert into project.repair_shop_is_authorized_for_car_make (id_repair_shop, id_car_manufacturer) values (1,4),(2,1),(3,2),(4,5);

insert into project.order_contains_part (id_order, id_part, quantity_order) values (1,3,1),(2,4,2),(3,1,2),(4,6,1);

insert into project.part_is_in_stock_in_warehouse (id_part,id_warehouse,quantity_warehouse) values (1,2,100), (2,1,150), (3,1,50), (4,3,75),(5,1,30),(6,2,70);

insert into project.part_is_appropriate_for_car (id_part, id_car) values (1,1),(1,2),(1,3),(2,1),(2,4),(2,5),(5,1),(4,1),(4,2),(4,3),(4,4),(4,5),(3,5),(6,2);



select p.part_name, project.repair.vin,id_repair_shop  from project.repair
join project.car_sample cs on cs.vin = project.repair.vin
join project.order_table ot on ot.id_user = cs.id_user 
join project.order_contains_part ocp on ocp.id_order = ot.id_order 
join project.part p on p.id_part = ocp.id_part 
where cs.id_user = 4

select p.part_name, c.car_type from project.part p 
join project.part_is_appropriate_for_car piafc on piafc.id_part = p.id_part 
join project.car c on c.id_car = piafc.id_car 
join project.part_is_from_category pifc on pifc.id_part = p.id_part
join project.category c2 on c2.id_category = pifc.id_category 
where c.car_type = 'Punto'
and 
c2.category_name = 'Exterior'

select ot.order_date , p.part_name , p2.amount , ocp.quantity_order , ot.order_status  from project.order_table ot 
join project.order_contains_part ocp on ocp.id_order = ot.id_order 
join project.part p on p.id_part = ocp.id_part 
join project.price p2 on p2.id_part = p.id_part 
where ot.id_user = 4

select r.review_rating, r.review_comment, rs.rs_name, rs.rs_location  from project.review r 
join project.repair r2 on r2.id_repair = r.id_repair 
join project.repair_shop rs on rs.id_repair_shop = r2.id_repair_shop 
where r.id_user = 4

select part_name from project.part

drop view project.repair_shop_reviews_summary

create or replace view project.repair_shop_reviews_summary as
select rs.id_repair_shop as rsid , rs.rs_name as rsname , count(r2.review_rating) as reviewcount ,avg(r2.review_rating)::float as reviewaverage
from project.repair_shop rs 
left join project.repair r on r.id_repair_shop = rs.id_repair_shop 
left join project.review r2 on r2.id_repair = r.id_repair 
group by rs.id_repair_shop 

select * from project.repair_shop_reviews_summary

