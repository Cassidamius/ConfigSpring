delete from t_user;
insert into t_user(id,username,password,delete_flag,descn,version,salt) values(1,'admin','ae206bc02e8c6955cb578e151d5b956e',1,'管理员',1,'0e4c627b-aab2-4c8f-9b68-cfd386bd0a27'); -- password: admin

delete from t_role;
insert into t_role(id,name,descn,version,delete_flag) values(1,'ROLE_ADMIN','管理员角色',1,1);
insert into t_role(id,name,descn,version,delete_flag) values(2,'ROLE_ANONYMOUS','匿名角色',1,1);

delete from t_resc;
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(1,'所有','URL','/**',2,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(2,'用户列表','MENU','/toUserListPage',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(3,'角色列表','MENU','/toRoleListPage',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(4,'资源列表','MENU','/toResourceListPage',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(5,'首页','URL','/index.jsp',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(6,'前往登录页面','URL','/toLoginPage',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(7,'登录','URL','/login',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(8,'删除用户','URL','/deleteUser',1,'',1);

delete from t_resc_role;
insert into t_resc_role(resc_id,role_id) values(1,1);
insert into t_resc_role(resc_id,role_id) values(2,1);
insert into t_resc_role(resc_id,role_id) values(3,1);
insert into t_resc_role(resc_id,role_id) values(4,1);
insert into t_resc_role(resc_id,role_id) values(5,2);
insert into t_resc_role(resc_id,role_id) values(6,2);
insert into t_resc_role(resc_id,role_id) values(7,2);
insert into t_resc_role(resc_id,role_id) values(8,1);

delete from t_user_role;
insert into t_user_role(user_id,role_id) values(1,1);

delete from t_dict;
insert into t_dict(id, dict_key,dict_key_desc,code,descn,version,delete_flag) values(1, 'rescType', '资源类型', 'MENU', '菜单',0,1);
insert into t_dict(id, dict_key,dict_key_desc,code,descn,version,delete_flag) values(2, 'rescType', '资源类型', 'URL', '链接',0,1);
