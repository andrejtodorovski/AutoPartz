drop table if exists project.users_table cascade;
drop table if exists project.client cascade;
drop table if exists project.delivery_man cascade;
drop table if exists project.warehouseman cascade;
drop table if exists project.administrator cascade;
drop table if exists project.review cascade;
drop table if exists project.repair cascade;
drop table if exists project.repair_shop cascade;
drop table if exists project.car_manufacturer cascade;
drop table if exists project.car cascade;
drop table if exists project.car_sample cascade;
drop table if exists project.service_book cascade;
drop table if exists project.order_table cascade;
drop table if exists project.part_manufacturer cascade;
drop table if exists project.part cascade;
drop table if exists project.delivery cascade;
drop table if exists project.warehouse cascade;
drop table if exists project.price cascade;
drop table if exists project.category cascade;
drop table if exists project.part_is_from_category cascade;
drop table if exists project.repair_shop_is_authorized_for_car_make cascade;
drop table if exists project.order_contains_part cascade;
drop table if exists project.part_is_in_stock_in_warehouse cascade;
drop table if exists project.part_is_appropriate_for_car cascade;

create table project.users_table(
	ID_user serial primary key,
	username varchar(100) unique not null,
	email varchar(100) unique not null,
	name_user varchar(100) not null,
	password_user varchar(100) not null,
	user_created_on timestamp default now(),
	phone_number varchar(100),
	ID_administrator integer
	-- constraint for fk_user_by_administrator added later
);

create table project.client(
	ID_user integer primary key,
	constraint fk_client_is_user foreign key (ID_user) references project.users_table(ID_user)
);

create table project.delivery_man(
	ID_user integer primary key,
	employed_from date not null,
	constraint fk_delivery_man_is_user foreign key (ID_user) references project.users_table(ID_user)
);

create table project.warehouseman(
	ID_user integer primary key,
	employed_from date not null,
	ID_warehouse integer not null,
	constraint fk_warehouseman_is_user foreign key (ID_user) references project.users_table(ID_user)
	-- contraint for fk_warehouseman_warehouse added later
);

create table project.administrator(
	ID_user integer primary key,
	authorized_from date not null,
	authorized_till date not null,
	constraint fk_administrator_is_user foreign key (ID_user) references project.users_table(ID_user)
);

alter table project.users_table add constraint fk_user_by_administrator foreign key (ID_administrator)
    references project.administrator(ID_user);
   
create table project.warehouse(
	ID_warehouse serial primary key,
	warehouse_location varchar(100) not null
);

alter table project.warehouseman add constraint fk_warehouseman_warehouse foreign key (ID_warehouse)
    references project.warehouse(ID_warehouse);
    
create table project.car_manufacturer(
	ID_car_manufacturer serial primary key,
	cm_name varchar(100) not null,
	cm_country varchar(100)
);

create table project.car(
	ID_car serial primary key, 
	in_production_since integer not null, 
	in_production_till integer not null, 
	car_type varchar(100) not null, 
	ID_car_manufacturer integer not null,
	constraint fk_car_car_manufacturer foreign key (ID_car_manufacturer) references project.car_manufacturer(ID_car_manufacturer)
);

create table project.car_sample(
	vin integer primary key,
	year_of_production integer not null, 
	engine_power integer not null, 
	displacement integer not null, 
	fuel_type varchar(100) not null, 
	km_driven integer not null, 
	ID_user integer not null, 
	ID_car integer not null,
	constraint fk_car_sample_user foreign key (ID_user) references project.client(ID_user),
	constraint fk_car_sample_car foreign key (ID_car) references project.car(ID_car)
);

create table project.service_book(
	ID_service_book serial primary key,
	sb_created_on timestamp default now(),
	vin integer not null,
	constraint fk_service_book_car_sample foreign key (vin) references project.car_sample(vin)
);

create table project.repair_shop(
	ID_repair_shop serial primary key, 
	rs_name varchar(100) not null, 
	rs_location varchar(100) not null, 
	rs_phone_number varchar(100) not null
);

create table project.repair(
	ID_repair serial primary key,
	vin integer not null,
	ID_repair_shop integer not null,
	ID_service_book integer,
	constraint fk_repair_car_sample foreign key (vin) references project.car_sample(vin),
	constraint fk_repair_repair_shop foreign key (ID_repair_shop) references project.repair_shop(ID_repair_shop),
	constraint fk_repair_service_book foreign key (ID_service_book) references project.service_book(ID_service_book)
);

create table project.review(
	ID_review serial primary key,
	review_rating integer not null,
	review_comment varchar(300),
	ID_repair integer not null,
	ID_user integer not null,
	constraint fk_review_repair foreign key (ID_repair) references project.repair(ID_repair),
	constraint fk_review_user foreign key (ID_user) references project.client(ID_user)
);

create table project.order_table(
	ID_order serial primary key,
	order_status varchar(100) not null,
	order_date timestamp default now(),
	ID_user integer not null,
	constraint fk_order_user foreign key (ID_user) references project.client(ID_user)
);

create table project.part_manufacturer(
	ID_part_manufacturer serial primary key,
	pm_name varchar(100) not null,
	pm_location varchar(100)
);

create table project.part(
	ID_part serial primary key,
	part_name varchar(100) not null,
	part_description varchar(300),
	ID_part_manufacturer integer not null,
	constraint fk_part_part_manufacturer foreign key(ID_part_manufacturer) references project.part_manufacturer(ID_part_manufacturer)
);

create table project.delivery(
	ID_delivery serial primary key,
	delivery_status varchar(100) not null,
	delivery_address varchar(100) not null,
	ID_user integer not null,
	ID_order integer not null,
	constraint fk_delivered_by_deliver_man foreign key(ID_user) references project.delivery_man(ID_user),
	constraint fk_delivery_for_order foreign key(ID_order) references project.order_table(ID_order)
);

create table project.price(
	ID_price serial primary key,
	amount integer not null,
	price_from date not null,
	price_till date,
	ID_part integer not null,
	constraint fk_price_for_part foreign key(ID_part) references project.part(ID_part),
	constraint ck_amount_gt_0 check (amount>0)
);

create table project.category(
	ID_category serial primary key,
	category_name varchar(100) not null,
	ID_parent_category integer,
	constraint fk_category_parentcategory foreign key (ID_parent_category) references project.category(ID_category)
);

create table project.part_is_from_category(
	ID_part integer not null,
	ID_category integer not null,
	constraint fk_part_is_from_category_part foreign key (ID_part) references project.part(ID_part),
	constraint fk_part_is_from_category_category foreign key (ID_category) references project.category(ID_category)

);

create table project.repair_shop_is_authorized_for_car_make(
	ID_repair_shop integer not null,
	ID_car_manufacturer integer not null,
	constraint fk_repair_shop_is_authorized_for_car_make_repair_shop foreign key (ID_repair_shop) references project.repair_shop(ID_repair_shop),
	constraint fk_repair_shop_is_authorized_for_car_make_car_manufacturer foreign key (ID_car_manufacturer) references project.car_manufacturer(ID_car_manufacturer)
);

create table project.order_contains_part(
	ID_part integer not null,
	ID_order integer not null,
	quantity_order integer not null,
	constraint fk_order_contains_part_part foreign key (ID_part) references project.part(ID_part),
	constraint fk_order_contains_part_order foreign key (ID_order) references project.order_table(ID_order),
	constraint ck_quantity_order check (quantity_order>0)
);

create table project.part_is_in_stock_in_warehouse(
	ID_part integer not null,
	ID_warehouse integer not null,
	quantity_warehouse integer not null,
	constraint fk_part_is_in_stock_in_warehouse_part foreign key (ID_part) references project.part(ID_part),
	constraint fk_part_is_in_stock_in_warehouse_warehouse foreign key (ID_warehouse) references project.warehouse(ID_warehouse),
	constraint ck_quantity_warehouse check (quantity_warehouse>0)
);

create table project.part_is_appropriate_for_car(
	ID_part integer not null,
	ID_car integer not null,
	constraint fk_part_is_appropriate_for_car_part foreign key (ID_part) references project.part(ID_part),
	constraint fk_part_is_appropriate_for_car_car foreign key (ID_car) references project.car(ID_car)
);
