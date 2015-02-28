delete from t_user;
insert into t_user(id,username,password,status,descn, version) values(1,'admin','21232f297a57a5a743894a0e4a801fc3',1,'管理员',1); -- password: admin
insert into t_user(id,username,password,status,descn, version) values(2,'user','ee11cbb19052e40b07aac0ca060c23ee',1,'普通用户',1);   -- password: user
insert into t_user(id,username,password,status,descn, version) values(3,'anonymous','294de3557d9d00b3d2d8a1e6aab028cf',1,'匿名用户',1);   -- password: user
insert into t_user(id,username,password,status,descn, version) values(12,'user2','ee11cbb19052e40b07aac0ca060c23ee',1,'普通用户',1);   -- password: user

delete from t_role;
insert into t_role(id,name,descn,version) values(1,'ROLE_ADMIN','管理员角色',1);
insert into t_role(id,name,descn,version) values(2,'ROLE_USER','用户角色',1);
insert into t_role(id,name,descn,version) values(3,'ROLE_ANONYMOUS','匿名角色',1);

delete from t_resc;
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(1,'所有','URL','/**',2,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(2,'用户列表','MENU','/toUserListPage',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(3,'角色列表','MENU','/toRoleListPage',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(4,'资源列表','MENU','/toResourceListPage',1,'',1);

insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(5,'进入登录页','URL','/toLoginPage',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(6,'欢迎','URL','/welcome.jsp',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(7,'退出','URL','/layout.jsp',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(8,'左边','URL','/left.jsp',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(9,'上部','URL','/top.jsp',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(10,'退出','URL','/logout',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(11,'欢迎','URL','/welcome',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(12,'用户信息','URL','/getUserInfo',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(15,'查询用户列表','URL','/findUserList',1,'',1);

delete from t_resc_role;
insert into t_resc_role(resc_id,role_id) values(1,1);
insert into t_resc_role(resc_id,role_id) values(2,1);
insert into t_resc_role(resc_id,role_id) values(3,1);
insert into t_resc_role(resc_id,role_id) values(4,1);

insert into t_resc_role(resc_id,role_id) values(4,3);
insert into t_resc_role(resc_id,role_id) values(5,3);
-- insert into t_resc_role(resc_id,role_id) values(6,1);
insert into t_resc_role(resc_id,role_id) values(6,2);
-- insert into t_resc_role(resc_id,role_id) values(6,3);
insert into t_resc_role(resc_id,role_id) values(7,3);
insert into t_resc_role(resc_id,role_id) values(8,3);
insert into t_resc_role(resc_id,role_id) values(9,3);
insert into t_resc_role(resc_id,role_id) values(10,3);
insert into t_resc_role(resc_id,role_id) values(11,3);
insert into t_resc_role(resc_id,role_id) values(12,2);
insert into t_resc_role(resc_id,role_id) values(12,3);
insert into t_resc_role(resc_id,role_id) values(13,2);
insert into t_resc_role(resc_id,role_id) values(14,2);
insert into t_resc_role(resc_id,role_id) values(15,2);
insert into t_resc_role(resc_id,role_id) values(16,2);

delete from t_user_role;
insert into t_user_role(user_id,role_id) values(1,1);

insert into t_user_role(user_id,role_id) values(2,2);
insert into t_user_role(user_id,role_id) values(3,3);
insert into t_user_role(user_id,role_id) values(12,2);
          