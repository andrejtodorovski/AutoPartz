insert into project.users_table (username,email,name_user,password_user) values
('andrejtodorovski5','andrejtodorovski5@gmail.com','Andrej Todorovski','andrejpassword'),
('stefanmileski4','stefanmileski4@gmail.com','Stefan Mileski','stefanpassword'),
('hristijansazdovski3','hristijansazdovski3@gmail.com','Hristijan Sazdovski','hristijanpassword'),
('client1','bojantrpeski2@gmail.com','Bojan Trpeski','bojanpassword'),
('client2','darkosasanski1@gmail.com','Darko Sasanski','darkopassword'),
('client3','denicakjorvezir@gmail.com','Denica Kjorvezir','denicapassword'),
('deliveryman1','deliveryman1@gmail.com','Petko Petkovski','petko123'),
('deliveryman2','deliveryman2@gmail.com','Marko Markovski','marko123'),
('deliveryman3','deliveryman3@gmail.com','Petar Petrovski','petar123'),
('warehouseman1','warehouseman1@gmail.com','Viktor Petrovski','viktor123'),
('warehouseman2','warehouseman2@gmail.com','Vlatko Petrovski','vlatko123'),
('warehouseman3','warehouseman3@gmail.com','Vido Petrovski','vido123'),
('client4','client4@gmail.com','Client Four','client4'),
('client5','client5@gmail.com','Client Five','client5'),
('client6','client6@gmail.com','Client Six','client6'),
('client7','client7@gmail.com','Client Seven','client7'),
('client8','client8@gmail.com','Client Eight','client8'),
('client9','client9@gmail.com','Client Nine','client9'),
('client10','client10@gmail.com','Client Ten','client10'),
('client11','client11@gmail.com','Client Eleven','client11'),
('client12','client12@gmail.com','Client Twelve','client12'),
('client13','client13@gmail.com','Client Thirteen','client13'),
('client14','client14@gmail.com','Client Fourteen','client14'),
('client15','client15@gmail.com','Client Fifteen','client15');

--insert into project.users_table (username,email,name_user,password_user,phone_number) values
--('stefanmileski5','stefanmileski5@gmail.com','Stefan Mileski','stefanklient','070 123 456');

insert into project.administrator (id_user,authorized_from, authorized_till) values (1,'2022-01-01','2024-01-01'),(2,'2022-01-01','2025-01-01'),(3,'2022-01-01','2026-01-01');

insert into project.client (id_user) values (4),(5),(6),(13),(14),(15),(16),(17),(18),(19),(20),(21),(22),(23),(24);

insert into project.delivery_man (id_user,employed_from) values (7,now()),(8,now()),(9,now());

insert into project.warehouse (warehouse_location) values ('Radishani'),('Kozle'),('Vizbegovo');

insert into project.warehouseman (id_user,employed_from,id_warehouse) values (10,now(),1),(11,now(),2),(12,now(),3);

insert into project.part_manufacturer (pm_name,pm_location) values
('Bosch','Germany'),
('Continental','Belgium'),
('Mahle','Netherlands'),
('Sachs','Germany'),
('Brembo','Italy');

insert into project.car_manufacturer (cm_name,cm_country) values
('Volkswagen','Germany'),
('Fiat','Italy'),
('Ford','USA'),
('Toyota','Japan'),
('Hyndai','Korea');

insert into project.repair_shop (rs_name,rs_location,rs_phone_number) values
('Volkswagen Service','Ilinenska','075 876 543'),
('Fiat Service','Vodnjanska','078 555 666'),
('Ford Service','Teodosij Gologanov','071 333 444'),
('Toyota Service','Partizanska','070 123 456'),
('Hyndai Service','Boris Trajkovski','075 500 000');

insert into project.car(in_production_since,in_production_till,car_type,id_car_manufacturer) values
(2001,2004,'Golf 4',1),
(2004,2006,'Golf 5',1),
(2001,2003,'Punto',2),
(2004,2008,'Punto Evo',2),
(2009,2012,'Grande Punto',2),
(2007,2014,'Doblo',2),
(2000,2008,'Fiesta',3),
(2005,2015,'Focus',3),
(2003,2006,'Aygo',4),
(2007,2015,'Yaris',4),
(2015,2022,'Corollaa',4),
(1995,2005,'Coupe',5);

insert into project.car_sample(vin,year_of_production, engine_power, displacement, fuel_type, km_driven, ID_user, ID_car) values
(4444,2003,65,1200,'Diesel',120000,4,1),
(5555,2005,80,1400,'Diesel',150000,5,2),
(6666,2002,65,1200,'Petrol/Gas',65000,6,3),
(1313,2006,140,2000,'Diesel',185000,13,4),
(1414,2006,120,1900,'Diesel',240000,14,5),
(1515,2006,95,1600,'Petrol',85000,15,6),
(1616,2006,80,1400,'Petrol/Gas',125000,16,7),
(1717,2006,69,1200,'Petrol',113000,17,8),
(1818,2006,65,1200,'Petrol/Gas',150000,18,9),
(1919,2006,140,1600,'Diesel',140000,19,10),
(2020,2017,200,2400,'Diesel',20000,20,11),
(2121,1996,200,2000,'Petrol/Gas',320000,21,12),
(2222,2004,120,1600,'Diesel',115000,22,3),
(2323,2003,140,2000,'Petrol/Gas',105000,23,3),
(2424,2000,120,1900,'Diesel',150000,24,1);

insert into project.part(part_name, id_part_manufacturer) values
('Engine G4GF',1),
('Wheel 20CM',2),
('Shift 6GEARS',3),
('Radio with AUX',1),
('Rear Bumper FOR VW GOLF 4',2),
('Headlight FOR PUNTO',3),
('Air Conditioning',4),
('Hand Brake',5),
('Siren',4),
('Electrical window buttons',5),
('Sport filter',4),
('Air filter',5),
('Fuel filter',4),
('Oil filter',5);

insert into project.category(category_name) values ('Brakes'),('Cooling System'),('Electrical'),('Engine components'),('Exterior'),('Filters'),('Interior'),('Shift');

insert into project.order_table(order_status,order_date,id_user) values 
('created',now()-interval'12months',4),
('created',now()-interval'6months',4),
('created',now()-interval'7months',4),
('created',now()-interval'1month',4),
('created',now()-interval'11months',5),
('created',now()-interval'10months',6),
('created',now()-interval'2months',6),
('created',now()-interval'9months',13),
('created',now()-interval'8months',14),
('created',now()-interval'7months',15),
('created',now()-interval'6months',16),
('created',now()-interval'9months',17),
('created',now()-interval'5months',17),
('created',now()-interval'4months',18),
('created',now()-interval'3months',19),
('created',now()-interval'2months',20),
('created',now()-interval'1month',21),
('created',now()-interval'12months',22),
('created',now(),23),
('created',now()-interval'3months',24),
('created',now(),24);


insert into project.service_book (vin) values (4444),(5555),(6666),(1313),(1414),(1515),(1616),(1717),(1818),(1919),(2020),(2121),(2222),(2323),(2424);

insert into project.delivery (delivery_status, delivery_address,id_user,id_order) values 
('finished','Pintija',8,1),
('finished','Kisela Voda',9,2),
('finished','Aerodrom',7,3),
('in progress','Kozle',8,4),
('finished', 'Radisani',7,5),
('finished','Dracevo',8,6),
('in progress','Gjorce',9,7),
('finished','Novo lisice',7,8),
('finished','Lisice',8,9),
('finished', 'Butel',7,10),
('finished','Pintija',8,11),
('finished','Kisela Voda',9,12),
('finished','Radisani',7,13),
('finished','Nerezi',8,14),
('finished', 'Aerodrom',7,15),
('finished', 'Aerodrom',9,16),
('in progress', 'Novo Lisice',9,17),
('finished', 'Butel',7,18),
('in progress', 'Kozle',8,19),
('finished', 'Aerodrom',9,20),
('in progress', 'Gjorce',8,21);



insert into project.price (amount, price_from, id_part) values 
(3000,now(),1),
(1000,now(),2),
(2000,now(),3),
(1500,now(),4),
(3500,now(),5),
(300,now(),6),
(4300,now(),7),
(900,now(),8),
(500,now(),9),
(1300,now(),10),
(3500,now(),11),
(3600,now(),12),
(4500,now(),13),
(4100,now(),14);


insert into project.repair (vin, id_repair_shop, id_service_book) values 
(4444,1,1),
(5555,1,2),
(6666,2,3),
(1313,2,4),
(1414,2,5),
(1515,2,6),
(1616,3,7),
(1717,3,8),
(1818,4,9),
(1919,4,10),
(2020,4,11),
(2121,5,12),
(2222,2,13),
(2323,2,14),
(2424,1,15);


insert into project.part_is_from_category (id_part, id_category) values (1,4),(2,7),(3,8),(4,3),(5,5),(6,5),(7,2),(8,1),(9,3),(10,3),(11,6),(12,6),(13,6),(14,6);

insert into project.repair_shop_is_authorized_for_car_make (id_repair_shop, id_car_manufacturer) values (1,1),(2,2),(3,3),(4,4),(5,5);


insert into project.review (review_rating, id_repair, id_user) values (10,1,4),(6,2,5),(7,3,6),
(8,4,13),(9,5,14),(10,6,15),(5,7,16),(3,8,17),(4,9,18),(10,10,19),(9,11,20),(7,12,21),(8,13,22),(9,14,23),(10,15,24);


insert into project.part_is_in_stock_in_warehouse (id_part,id_warehouse,quantity_warehouse) values (1,2,100), (2,1,150), (3,1,50), (4,3,75),(5,1,30),(6,2,70),
(7,3,10),(8,2,20),(9,3,50),(10,3,25),(11,1,16),(12,2,24),(13,1,20),(14,2,70);


insert into project.part_is_appropriate_for_car (id_part, id_car) values 
(1,1),(1,2),(2,3),(2,4),(2,5),(2,6),(3,8),(3,11),(3,12),(4,1),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7),(4,8),(5,1),(6,3),(6,4),(7,9),(7,10),
(7,11),(8,12),(9,1),(9,2),(9,7),(9,8),(10,1),(10,2),(10,12),(11,3),(11,4),(11,5),(12,7),(12,8),(13,6),(14,1),(14,2);



insert into project.order_contains_part (id_order, id_part, quantity_order) values 
(1,1,2),(1,4,1),(2,14,1),(3,10,2),(3,5,2),(4,4,3),(4,1,1),(5,1,2),(5,4,1),(5,14,1),(6,11,2),(7,2,2),(7,6,1),(7,11,1),
(8,2,1),(9,11,2),(10,13,1),(11,9,1),(11,12,1),(12,3,1),(12,4,1),(13,9,2),(14,7,1),(15,7,1),(16,7,1),(17,10,1),(17,8,1),
(18,2,1),(19,4,1),(19,6,2),(19,11,2),(20,1,2),(21,9,1);



-- Important Use Cases
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

-- извештај за сите сервиси и за сите број на reviews и avg рејтинг
drop view project.repair_shop_reviews_summary;

create or replace view project.repair_shop_reviews_summary as
select rs.id_repair_shop as rs_id , rs.rs_name as rs_name , count(r2.review_rating) as review_count ,avg(r2.review_rating)::float as review_average
from project.repair_shop rs 
left join project.repair r on r.id_repair_shop = rs.id_repair_shop 
left join project.review r2 on r2.id_repair = r.id_repair 
group by rs.id_repair_shop 

select * from project.repair_shop_reviews_summary

--извештај за сите доставувачи, која е најдобра нарачка што ја направил и кој производ е најскап во таа нарачка
drop view project.deliveryman_summary

create or replace view project.deliveryman_summary as
	select nar.deliverer, nar.deliverer_username,
	max(nar.nar_suma) as najdobra_naracka, max(par.par_suma) as najskap_proizvod 
	from project.delivery_man d 
	left join 
	(
		select d.id_user as deliverer, ut.username as deliverer_username, d2.id_delivery as delivery_num
		,sum(p2.amount*ocp.quantity_order) as nar_suma
		from project.delivery_man d
		left join project.users_table ut on ut.id_user = d.id_user 
		left join project.delivery d2 on d2.id_user = d.id_user 
		left join project.order_contains_part ocp on ocp.id_order = d2.id_order 
		left join project.part p on p.id_part = ocp.id_part 
		left join project.price p2 on p2.id_part = p.id_part
		group by (d.id_user,ut.username,delivery_num)
	) as nar on d.id_user = nar.deliverer
	left join 
	(
		select d.id_user as deliverer, ocp.id_order as order_num,d2.id_delivery as delivery_num, p.part_name as part_name,
		sum(p2.amount*ocp.quantity_order) as par_suma
		from project.delivery_man d
		left join project.users_table ut on ut.id_user = d.id_user 
		left join project.delivery d2 on d2.id_user = d.id_user 
		left join project.order_contains_part ocp on ocp.id_order = d2.id_order 
		left join project.part p on p.id_part = ocp.id_part 
		left join project.price p2 on p2.id_part = p.id_part
		group by (d.id_user,ocp.id_order,d2.id_delivery,p.part_name)
	) as par on nar.deliverer = par.deliverer and nar.delivery_num = par.delivery_num
	group by nar.deliverer,nar.deliverer_username 
	order by najdobra_naracka desc

select * from project.deliveryman_summary

--извештај за сите доставувачи, број на нарачки, вкупна вредност на сите нарачки што ги направил
drop view project.deliveryman_count_sum

create or replace view project.deliveryman_count_sum as
	select nar.deliverer, nar.deliverer_username, count(distinct(nar.delivery_num)) as vkupno_dostavi,
	sum(nar.nar_suma) as vkupna_suma
	from project.delivery_man d 
	left join 
	(
		select d.id_user as deliverer, ut.username as deliverer_username, d2.id_delivery as delivery_num
		,sum(p2.amount*ocp.quantity_order) as nar_suma
		from project.delivery_man d
		left join project.users_table ut on ut.id_user = d.id_user 
		left join project.delivery d2 on d2.id_user = d.id_user 
		left join project.order_contains_part ocp on ocp.id_order = d2.id_order 
		left join project.part p on p.id_part = ocp.id_part 
		left join project.price p2 on p2.id_part = p.id_part
		group by (d.id_user,ut.username,delivery_num)
	) as nar on d.id_user = nar.deliverer
	group by nar.deliverer,nar.deliverer_username 
	order by vkupna_suma desc

select * from project.deliveryman_count_sum

-- извештај за сите категории и за кој прозиводител на коли биле купени највеќе делови од таа категорија
select c.id_category, c.category_name, cm.id_car_manufacturer , cm.cm_name ,
count(ocp.id_part) as category_by_car
from project.category as c
left join project.part_is_from_category pifc on pifc.id_category = c.id_category 
left join project.part p on p.id_part = pifc.id_part 
left join project.order_contains_part ocp on ocp.id_part = p.id_part 
left join project.order_table ot on ot.id_order = ocp.id_order 
left join project.users_table ut on ut.id_user = ot.id_user 
left join project.car_sample cs on cs.id_user = ut.id_user 
left join project.car c2 on c2.id_car = cs.id_car 
left join project.car_manufacturer cm on cm.id_car_manufacturer = c2.id_car_manufacturer 
group by c.id_category,cm.id_car_manufacturer  
order by category_by_car desc


-- Извештај за најкупуван артикл, во која количина и од која категорија е истиот
select c.category_name , p.part_name , coalesce(sum(ocp2.quantity_order),0) as maxkol from project.part p
        left join project.part_is_from_category pifc on pifc.id_part = p.id_part 
        left join project.category c on c.id_category = pifc.id_category  
        left join project.order_contains_part ocp2 on ocp2.id_part = p.id_part 
        group by c.id_category , p.id_part  
        having coalesce(sum(ocp2.quantity_order), 0)=(
                select max(maxkol) from
                (
                        select coalesce(sum(ocp2.quantity_order),0) as maxkol from project.part p
                        left join project.part_is_from_category pifc on pifc.id_part = p.id_part 
                        left join project.category c on c.id_category = pifc.id_category  
                        left join project.order_contains_part ocp2 on ocp2.id_part = p.id_part 
                        group by c.id_category , p.id_part 
                ) q1
 		)

 		
--Извештај за сите делови, со нивни категории и производители, и количината која била нарачана од овие делови во секој од последните 3 месеци. 
select p.id_part, p.part_name, cat.category_name, pm.pm_name, f.narachani_vo_ovoj_mesec as narachani_ovoj_mesec, s.narachani_vo_prethoden_mesec as narachani_prethoden_mesec, t.narachani_vo_predprethoden_mesec as narachani_predprethoden_mesec
from project.part as p
join project.part_manufacturer as pm on p.id_part_manufacturer = pm.id_part_manufacturer
join project.part_is_from_category as pifc on p.id_part = pifc.id_part
join project.category as cat on pifc.id_category = cat.id_category
left join project.order_contains_part as ocp on p.id_part = ocp.id_part
left join project.order_table as o on ocp.id_order = o.id_order
left join (
select p.id_part, p.part_name, cat.category_name, pm.pm_name,
       count(o.id_order) as narachani_vo_ovoj_mesec
from project.part as p
join project.part_manufacturer as pm on p.id_part_manufacturer = pm.id_part_manufacturer
join project.part_is_from_category as pifc on p.id_part = pifc.id_part
join project.category as cat on pifc.id_category = cat.id_category
left join project.order_contains_part as ocp on p.id_part = ocp.id_part
left join project.order_table as o on ocp.id_order = o.id_order
                                  and
                              extract(month from o.order_date) = extract(month from now()) and extract(year from o.order_date) = extract(year from now())
group by p.id_part, p.part_name, cat.category_name, pm.pm_name
) as f on p.id_part = f.id_part and pm.pm_name = f.pm_name
left join (
select p.id_part, p.part_name, cat.category_name, pm.pm_name,
       count(o.id_order) as narachani_vo_prethoden_mesec
from project.part as p
join project.part_manufacturer as pm on p.id_part_manufacturer = pm.id_part_manufacturer
join project.part_is_from_category as pifc on p.id_part = pifc.id_part
join project.category as cat on pifc.id_category = cat.id_category
left join project.order_contains_part as ocp on p.id_part = ocp.id_part
left join project.order_table as o on ocp.id_order = o.id_order
                                  and
                              extract(month from o.order_date) = extract(month from now()-interval'1 month') and extract(year from o.order_date) = extract(year from now()-interval'1 month')
group by p.id_part, p.part_name, cat.category_name, pm.pm_name
) as s on p.id_part = s.id_part and pm.pm_name = s.pm_name
left join (
select p.id_part, p.part_name, cat.category_name, pm.pm_name,
       count(o.id_order) as narachani_vo_predprethoden_mesec
from project.part as p
join project.part_manufacturer as pm on p.id_part_manufacturer = pm.id_part_manufacturer
join project.part_is_from_category as pifc on p.id_part = pifc.id_part
join project.category as cat on pifc.id_category = cat.id_category
left join project.order_contains_part as ocp on p.id_part = ocp.id_part
left join project.order_table as o on ocp.id_order = o.id_order
                                  and
                              extract(month from o.order_date) = extract(month from now()-interval'2 month') and extract(year from o.order_date) = extract(year from now()-interval'2 month')
group by p.id_part, p.part_name, cat.category_name, pm.pm_name
) as t on p.id_part = t.id_part and pm.pm_name = t.pm_name
group by p.id_part, p.part_name, cat.category_name, pm.pm_name, f.narachani_vo_ovoj_mesec, s.narachani_vo_prethoden_mesec, t.narachani_vo_predprethoden_mesec


