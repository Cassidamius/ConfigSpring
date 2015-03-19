delete from t_user;
insert into t_user(id,username,password,status,descn, version) values(1,'admin','21232f297a57a5a743894a0e4a801fc3',1,'管理员',1); -- password: admin

delete from t_role;
insert into t_role(id,name,descn,version) values(1,'ROLE_ADMIN','管理员角色',1);
insert into t_role(id,name,descn,version) values(2,'ROLE_ANONYMOUS','匿名角色',1);

delete from t_resc;
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(1,'所有','URL','/**',2,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(2,'用户列表','MENU','/toUserListPage',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(3,'角色列表','MENU','/toRoleListPage',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(4,'资源列表','MENU','/toResourceListPage',1,'',1);

delete from t_resc_role;
insert into t_resc_role(resc_id,role_id) values(1,1);
insert into t_resc_role(resc_id,role_id) values(2,1);
insert into t_resc_role(resc_id,role_id) values(3,1);
insert into t_resc_role(resc_id,role_id) values(4,1);
insert into t_resc_role(resc_id,role_id) values(1,2);


delete from t_user_role;
insert into t_user_role(user_id,role_id) values(1,1);
          