drop table role_button;
create table role_button(
id int not null primary key,
role_id int,
button_id int
);

COMMENT ON TABLE menu_button IS '按钮表';
COMMENT ON menu_button (
menu_id is '菜单id',
button_id is '按钮id');

drop table menu_button;
create table menu_button(
id int not null primary key,
menu_id int, 
button_id int
);

COMMENT ON TABLE menu_button IS '按钮表';
COMMENT ON menu_button (
menu_id is '菜单id',
button_id is '按钮id');

drop table button;
create table button(
id int not null primary key,
name varchar(200)
);

COMMENT ON TABLE button IS '按钮表';
COMMENT ON button (name IS '名称');

drop table user_role;
create table user_role(
id int not null primary key,
role_id int,
user_info_id int
);

COMMENT ON TABLE user_role IS '用户角色表';
COMMENT ON user_role (
role_id IS '角色id',
user_info_id is '用户id');

drop table role_menu;
create table role_menu(
id int not null primary key,
role_id int,
menu_id int
);

COMMENT ON TABLE role_menu IS '角色菜单表';
COMMENT ON role_menu (
role_id IS '角色id',
menu_id is '菜单id');

drop table role;
create table role(
id int not null primary key,
name varchar(100),
remark varchar(300),
delete_flag int default 0 not null,
create_time timestamp default current timestamp not null,
update_time timestamp default current timestamp not null
);

COMMENT ON TABLE role IS '角色表';
COMMENT ON role (
name IS '角色名称',
remark is '注释');

drop table menu;
create table menu(
id int not null primary key,
name varchar(100),
url varchar(200),
parent_id int,
remark varchar(300),
delete_flag int default 0 not null,
create_time timestamp default current timestamp not null,
update_time timestamp default current timestamp not null
);

COMMENT ON TABLE menu IS '菜单表';
COMMENT ON menu (
name IS '姓名',
url is '链接',
parent_id is '父菜单id',
remark is '注释');

drop table user_info;
create table user_info(
 id int not null primary key,
 name varchar(200),
 nick_name varchar(200),
 first_name varchar(200),
 last_name varchar(200),
 name_pinyin varchar(200),
 birthday date,
 mobile varchar(20),
 address varchar(1000),
 telephone varchar(20),
 password varchar(20),
 delete_flag int default 0 not null,
 create_time timestamp default current timestamp not null,
 update_time timestamp default current timestamp not null
);

COMMENT ON TABLE user_info IS '用户信息表';
COMMENT ON COLUMN user_info.name IS '姓名';
COMMENT ON COLUMN user_info.nick_name IS '昵称';
COMMENT ON COLUMN user_info.first_name IS '名';
COMMENT ON COLUMN user_info.last_name IS '姓';
COMMENT ON COLUMN user_info.name_pinyin IS '姓名拼音';
COMMENT ON COLUMN user_info.birthday IS '生日';
COMMENT ON COLUMN user_info.address IS '地址';
COMMENT ON COLUMN user_info.mobile IS '手机';
COMMENT ON COLUMN user_info.telphone IS '电话';
COMMENT ON COLUMN user_info.password IS '密码';
COMMENT ON COLUMN user_info.delete_flag IS '删除标记：0：正常　1：已删除';
COMMENT ON COLUMN user_info.create_time IS '创建时间';
COMMENT ON COLUMN user_info.update_time IS '更新时间';

drop sequence seq_user_info;
create sequence seq_user_info as bigint start with 1 increment by 1;

DROP INDEX ind_user_info_name;
CREATE UNIQUE INDEX ind_user_info_name on user_info(name);

drop table customer_info;
create table customer_info(
 id int not null primary key,
 name varchar(200),
 name_pinyin varchar(200),
 birthday date,
 address varchar(1000),
 telphone varchar(20),
 mobile varchar(30),
 delete_flag int default 0 not null,
 create_time timestamp default current timestamp not null,
 update_time timestamp default current timestamp not null
);

COMMENT ON TABLE customer_info IS '用户信息表';
COMMENT ON COLUMN customer_info.name IS '姓名';
COMMENT ON COLUMN customer_info.name_pinyin IS '姓名拼音';
COMMENT ON COLUMN customer_info.birthday IS '生日';
COMMENT ON COLUMN customer_info.address IS '地址';
COMMENT ON COLUMN customer_info.telphone IS '电话';
COMMENT ON COLUMN customer_info.mobile IS '手机';
COMMENT ON COLUMN customer_info.delete_flag IS '删除标记：0：正常　1：已删除';
COMMENT ON COLUMN customer_info.create_time IS '创建时间';
COMMENT ON COLUMN customer_info.update_time IS '更新时间';

drop sequence seq_customer_info;
create sequence seq_customer_info as bigint start with 1 increment by 1;

DROP INDEX ind_user_info_name;
CREATE UNIQUE INDEX ind_user_info_name on user_info(name);

drop table company;
create table company(
id int not null primary key,
name varchar(1000),
reg_addr varchar(1000),
biz_addr varchar(1000),
biz_license varchar(40),
org_code varchar(40),
contact_person varchar(100),
contact_phone varchar(20),
office_phone varchar(20),
fax varchar(20),
delete_flag int default 0 not null,
 create_time timestamp default current timestamp not null,
 update_time timestamp default current timestamp not null
);

COMMENT ON TABLE company IS '公司信息表';
COMMENT ON COLUMN company.name IS '公司名称';
COMMENT ON COLUMN company.reg_addr IS '注册地址';
COMMENT ON COLUMN company.biz_addr IS '营业地址';
COMMENT ON COLUMN company.biz_license IS '营业执照';
COMMENT ON COLUMN company.org_code IS '组织机构代码';
COMMENT ON COLUMN company.contact_person IS '联系人';
COMMENT ON COLUMN company.contact_phone IS '联系电话';
COMMENT ON COLUMN company.office_phone IS '办公电话';
COMMENT ON COLUMN company.fax IS '传真';
COMMENT ON COLUMN company.delete_flag IS '删除标记：0：正常　1：已删除';
COMMENT ON COLUMN company.create_time IS '创建时间';
COMMENT ON COLUMN company.update_time IS '更新时间';

drop sequence seq_company;
create sequence seq_company as bigint start with 1 increment by 1;

drop table project;
create table project(
id int not null primary key,
name varchar(1000),
customer_info_id int,
company_id int,
addr varchar(1000),
delete_flag int default 0 not null,
 create_time timestamp default current timestamp not null,
 update_time timestamp default current timestamp not null
);

COMMENT ON TABLE project IS '项目信息表';
COMMENT ON COLUMN project.name IS '项目名称';
COMMENT ON COLUMN project.customer_info_id IS '项目经理';
comment on column project.company_id is '公司';
COMMENT ON COLUMN project.addr IS '项目地址';
COMMENT ON COLUMN project.delete_flag IS '删除标记：0：正常　1：已删除';
COMMENT ON COLUMN project.create_time IS '创建时间';
COMMENT ON COLUMN project.update_time IS '更新时间';

drop sequence seq_project;
create sequence seq_project as bigint start with 1 increment by 1;

drop table brand;
create table brand(
id int not null primary key,
name varchar(1000),
delete_flag int default 0 not null,
 create_time timestamp default current timestamp not null,
 update_time timestamp default current timestamp not null
);

COMMENT ON TABLE brand IS '品牌信息表';
COMMENT ON COLUMN brand.name IS '品牌名称';
COMMENT ON COLUMN brand.delete_flag IS '删除标记：0：正常　1：已删除';
COMMENT ON COLUMN brand.create_time IS '创建时间';
COMMENT ON COLUMN brand.update_time IS '更新时间';

drop sequence seq_brand;
create sequence seq_brand as bigint start with 1 increment by 1;

DROP INDEX ind_brand_name;
CREATE UNIQUE INDEX ind_brand_name on brand(name);

drop table product;
create table product(
id int not null primary key,
name varchar(1000),
pro_type varchar(1000),
delete_flag int default 0 not null,
 create_time timestamp default current timestamp not null,
 update_time timestamp default current timestamp not null
);

DROP INDEX ind_product_name;
CREATE UNIQUE INDEX ind_product_name on product(name);

COMMENT ON TABLE product IS '产品信息表';
COMMENT ON COLUMN product.name IS '产品名称';
COMMENT ON COLUMN product.pro_type IS '产品规格型号';
COMMENT ON COLUMN product.delete_flag IS '删除标记：0：正常　1：已删除';
COMMENT ON COLUMN product.create_time IS '创建时间';
COMMENT ON COLUMN product.update_time IS '更新时间';

drop sequence seq_product;
create sequence seq_product as bigint start with 1 increment by 1;

drop table contract;
create table contract(
id int not null primary key,
code varchar(1000),
name varchar(1000),
project_id int,
delete_flag int default 0 not null,
 create_time timestamp default current timestamp not null,
 update_time timestamp default current timestamp not null
);

COMMENT ON TABLE contract IS '合同信息表';
COMMENT ON COLUMN contract.code IS '合同编号';
COMMENT ON COLUMN contract.name IS '合同名称';
COMMENT ON COLUMN contract.project_id IS '所属项目';
COMMENT ON COLUMN contract.delete_flag IS '删除标记：0：正常　1：已删除';
COMMENT ON COLUMN contract.create_time IS '创建时间';
COMMENT ON COLUMN contract.update_time IS '更新时间';

drop sequence seq_contract;
create sequence seq_contract as bigint start with 1 increment by 1;

DROP INDEX ind_contract_name;
CREATE UNIQUE INDEX ind_contract_name on contract(name);

drop table contract_item;
create table contract_item(
id int not null primary key,
contract_id int,
product_id int,
brand_id int,
price double,
standard varchar(1000),
product_model varchar(1000),
unit varchar(100),
remark varchar(3000),
criterion varchar(100),
delete_flag int default 0 not null,
 create_time timestamp default current timestamp not null,
 update_time timestamp default current timestamp not null
);

COMMENT ON TABLE contract_item IS '合同产品价格信息表';
COMMENT ON COLUMN contract_item.contract_id IS '合同id';
COMMENT ON COLUMN contract_item.product_id IS '产品id';
COMMENT ON COLUMN contract_item.brand_id IS '品牌id';
COMMENT ON COLUMN contract_item.price IS '合同价';
COMMENT ON COLUMN contract_item.standard IS '规格';
COMMENT ON COLUMN contract_item.product_model IS '型号';
COMMENT ON COLUMN contract_item.unit IS '单位';
COMMENT ON COLUMN contract_item.remark IS '备注';
COMMENT ON COLUMN contract_item.criterion IS '质量标准';
COMMENT ON COLUMN contract_item.delete_flag IS '删除标记：0：正常　1：已删除';
COMMENT ON COLUMN contract_item.create_time IS '创建时间';
COMMENT ON COLUMN contract_item.update_time IS '更新时间';

drop sequence seq_contract_item;
create sequence seq_contract_item as bigint start with 1 increment by 1;

drop table sale_order;
create table sale_order(
id int not null primary key,
contract_id int,
sale_date date,
freight double not null default 0,
delete_flag int default 0 not null,
 create_time timestamp default current timestamp not null,
 update_time timestamp default current timestamp not null
);

COMMENT ON TABLE sale_order IS '销售订单信息表';
COMMENT ON COLUMN sale_order.contract_id IS '合同id';
COMMENT ON COLUMN sale_order.sale_date IS '销售日期';
COMMENT ON COLUMN sale_order.freight IS '运费';
COMMENT ON COLUMN sale_order.delete_flag IS '删除标记：0：正常　1：已删除';
COMMENT ON COLUMN sale_order.create_time IS '创建时间';
COMMENT ON COLUMN sale_order.update_time IS '更新时间';

drop sequence seq_sale_order;
create sequence seq_sale_order as bigint start with 1 increment by 1;

drop table sale_order_item;
create table sale_order_item(
id int not null primary key,
sale_order_id int,
contract_item_id int,
sale_price double,
quantity int,
delete_flag int default 0 not null,
 create_time timestamp default current timestamp not null,
 update_time timestamp default current timestamp not null
);

COMMENT ON TABLE sale_order_item IS '销售详细信息表';
COMMENT ON COLUMN sale_order_item.sale_order_id IS '销售订单id';
COMMENT ON COLUMN sale_order_item.contract_item_id IS '合同条目id';
COMMENT ON COLUMN sale_order_item.sale_price IS '销售价';
COMMENT ON COLUMN sale_order_item.quantity IS '销售数量';
COMMENT ON COLUMN sale_order_item.delete_flag IS '删除标记：0：正常　1：已删除';
COMMENT ON COLUMN sale_order_item.create_time IS '创建时间';
COMMENT ON COLUMN sale_order_item.update_time IS '更新时间';

drop sequence seq_sale_order_item;
create sequence seq_sale_order_item as bigint start with 1 increment by 1;

drop table purchase_order;
create table purchase_order(
id int not null primary key,
contract_id int,
purchase_date date,
delete_flag int default 0 not null,
 create_time timestamp default current timestamp not null,
 update_time timestamp default current timestamp not null
);

COMMENT ON TABLE purchase_order IS '采购订单信息表';
COMMENT ON COLUMN purchase_order.contract_id IS '合同id';
COMMENT ON COLUMN purchase_order.purchase_date IS '采购日期';
COMMENT ON COLUMN purchase_order.delete_flag IS '删除标记：0：正常　1：已删除';
COMMENT ON COLUMN purchase_order.create_time IS '创建时间';
COMMENT ON COLUMN purchase_order.update_time IS '更新时间';

drop sequence seq_purchase_order;
create sequence seq_purchase_order as bigint start with 1 increment by 1;

drop table purchase_order_item;
create table purchase_order_item(
id int not null primary key,
purchase_order_id int,
contract_item_id int,
purchase_price double,
quantity int,
delete_flag int default 0 not null,
 create_time timestamp default current timestamp not null,
 update_time timestamp default current timestamp not null
);

COMMENT ON TABLE purchase_order_item IS '采购订单详细信息表';
COMMENT ON COLUMN purchase_order_item.purchase_order_id IS '采购订单id';
COMMENT ON COLUMN purchase_order_item.contract_item_id IS '合同条目id';
COMMENT ON COLUMN purchase_order_item.purchase_price IS '采购价';
COMMENT ON COLUMN purchase_order_item.quantity IS '采购数量';
COMMENT ON COLUMN purchase_order_item.delete_flag IS '删除标记：0：正常　1：已删除';
COMMENT ON COLUMN purchase_order_item.create_time IS '创建时间';
COMMENT ON COLUMN purchase_order_item.update_time IS '更新时间';

drop sequence seq_purchase_order_item;
create sequence seq_purchase_order_item as bigint start with 1 increment by 1;

drop table delivery;
create table delivery(
id int not null primary key,
project_id int,
contract_id int,
freight double not null default 0,
delivery_date date,
delete_flag int default 0 not null,
create_time timestamp default current timestamp not null,
update_time timestamp default current timestamp not null
);

COMMENT ON TABLE delivery IS '送货信息表';
COMMENT ON COLUMN delivery.project_id IS '项目id';
COMMENT ON COLUMN delivery.contract_id IS '合同id';
COMMENT ON COLUMN delivery.freight IS '运费';
COMMENT ON COLUMN delivery.delivery_date IS '送货日期';
COMMENT ON COLUMN delivery.delete_flag IS '删除标记：0：正常　1：已删除';
COMMENT ON COLUMN delivery.create_time IS '创建时间';
COMMENT ON COLUMN delivery.update_time IS '更新时间';

drop sequence seq_delivery;
create sequence seq_delivery as bigint start with 1 increment by 1;

drop table delivery_item;
create table delivery_item(
id int not null primary key,
delivery_id int,
product_id int,
brand_id int,
purchase_price double,
sale_price double,
standard varchar(1000),
product_model varchar(1000),
unit varchar(100),
remark varchar(3000),
criterion varchar(100),
freight double not null default 0,
delete_flag int default 0 not null,
create_time timestamp default current timestamp not null,
update_time timestamp default current timestamp not null
);

COMMENT ON TABLE delivery_item IS '送货明细信息表';
COMMENT ON COLUMN delivery_item.product_id IS '产品id';
COMMENT ON COLUMN delivery_item.brand_id IS '品牌id';
COMMENT ON COLUMN delivery_item.purchase_price IS '采购价格';
COMMENT ON COLUMN delivery_item.sale_price IS '销售价格';
COMMENT ON COLUMN delivery_item.standard IS '规格';
COMMENT ON COLUMN delivery_item.product_model IS '型号';
COMMENT ON COLUMN delivery_item.unit IS '单位';
COMMENT ON COLUMN delivery_item.remark IS '备注';
COMMENT ON COLUMN delivery_item.criterion IS '质量标准';
COMMENT ON COLUMN delivery_item.delete_flag IS '删除标记：0：正常　1：已删除';
COMMENT ON COLUMN delivery_item.create_time IS '创建时间';
COMMENT ON COLUMN delivery_item.update_time IS '更新时间';

drop sequence seq_delivery_item;
create sequence seq_delivery_item as bigint start with 1 increment by 1;