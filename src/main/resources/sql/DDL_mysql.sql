use mysql

drop table if exists hibernate_sequences;
create table hibernate_sequences(
	sequence_name varchar(200) comment '表名',
	sequence_next_hi_value int comment '序列值'
) comment 'hibernate模拟序列号生成表主键';

-- 资源
drop table if exists t_resc;
create table t_resc(
    id int AUTO_INCREMENT primary key comment '主键',
    name varchar(50) comment '资源名称',
    resc_type varchar(50) comment '资源类型',
    resc_string varchar(200) comment '资源',
    priority int comment '资源优先级',
    descn varchar(200) comment '资源描述',
    version int not null comment '版本',
	delete_flag int default 1 not null comment '删除标记：0：已删除　1：正常',
	create_time timestamp default current_timestamp not null comment '创建时间',
	update_time timestamp default current_timestamp not null comment '更新时间'
) comment '资源表';
-- alter table t_resc add constraint pk_resc primary key(id);

-- 角色
drop table if exists t_role;
create table t_role(
    id int AUTO_INCREMENT primary key,
    name varchar(50),
    descn varchar(200),
    version int not null comment '版本',
	delete_flag int default 1 not null comment '删除标记：0：已删除　1：正常',
	create_time timestamp default current_timestamp not null comment '创建时间',
	update_time timestamp default current_timestamp not null comment '更新时间'
) comment '角色表';
-- alter table t_role add constraint pk_role primary key(id);

-- 用户
drop table if exists t_user;
create table t_user(
 id int not null AUTO_INCREMENT primary key comment '主键',
 username varchar(200) comment '用户名',
 nick_name varchar(200) CHARACTER SET utf8 comment '昵称',
 first_name varchar(200) comment '名',
 last_name varchar(200) comment '姓',
 name_pinyin varchar(200) comment '姓名拼音',
 birthday date comment '生日',
 mobile varchar(20) comment '手机',
 address varchar(1000) comment '地址',
 telephone varchar(20) comment '电话',
 password varchar(32) comment '密码',
 salt char(36) comment '盐值：由java.util.UUID.randomUUID()生成',
 descn varchar(200) comment '描述',
 version int not null comment '版本',
 delete_flag int default 1 not null comment '删除标记：0：已删除　1：正常',
 create_time timestamp default current_timestamp not null comment '创建时间',
 update_time timestamp default current_timestamp not null comment '更新时间'
) comment '用户信息表';

-- DROP INDEX idx_t_user_name on t_user;
CREATE UNIQUE INDEX idx_t_user_name on t_user(username);

-- 资源角色映射表
drop table if exists t_resc_role;
create table t_resc_role(
    resc_id int comment '资源id',
    role_id int comment '角色id'
) comment '资源角色映射表';
alter table t_resc_role add constraint pk_t_resc_role primary key(resc_id,role_id);

-- 用户角色映射表
drop table if exists t_user_role;
create table t_user_role(
    user_id int comment '用户id',
    role_id int comment '角色id'
) comment '用户角色映射表';
alter table t_user_role add constraint pk_t_user_role primary key(user_id,role_id);
-- alter table t_user_role add FOREIGN KEY fk_t_user_role_user_id (user_id) references t_user(id);
-- alter table t_user_role add FOREIGN KEY fk_t_user_role_role_id (role_id) references t_role(id);

drop table if exists t_logs;
create table t_logs(
id int not null AUTO_INCREMENT primary key,
user_id int comment '操作员',
class_name varchar(300) comment '类名',
method_name varchar(100) comment '方法名称',
version int not null comment '版本',
delete_flag int default 1 not null comment '删除标记：0：已删除　1：正常',
create_time timestamp default current_timestamp not null comment '创建时间',
update_time timestamp default current_timestamp not null comment '更新时间'
) comment '操作日志';
