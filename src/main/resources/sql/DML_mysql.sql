-- delete from t_user where id = 0;
-- insert into t_user(id, name,nick_name, first_name, last_name, password, birthday, version, enabled, delete_flag, create_time, update_time) 
--          values (0, 'admin', '管理员', '三', '张', '111111', '1975-01-01', 1, true, 0, current_timestamp, current_timestamp);
          
delete from t_user;
insert into t_user(id,username,password,status,descn, version) values(1,'admin','21232f297a57a5a743894a0e4a801fc3',1,'管理员',1); -- password: admin
insert into t_user(id,username,password,status,descn, version) values(2,'user','ee11cbb19052e40b07aac0ca060c23ee',1,'普通用户',1);   -- password: user
insert into t_user(id,username,password,status,descn, version) values(3,'anonymous','294de3557d9d00b3d2d8a1e6aab028cf',1,'匿名用户',1);   -- password: user

delete from t_role;
insert into t_role(id,name,descn,version) values(1,'ROLE_ADMIN','管理员角色',1);
insert into t_role(id,name,descn,version) values(2,'ROLE_USER','用户角色',1);
insert into t_role(id,name,descn,version) values(3,'ROLE_ANONYMOUS','匿名角色',1);

delete from t_resc;
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(1,'登录','URL','/login',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(2,'索引','URL','/index.jsp',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(3,'用户','URL','/user.jsp',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(4,'登录','URL','/spring_security_login',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(5,'','URL','/j_spring_security_check',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(6,'所有','URL','/**',2,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(7,'进入登录页','URL','/toLoginPage',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(8,'欢迎','URL','/welcome.jsp',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(9,'退出','URL','/layout.jsp',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(10,'左边','URL','/left.jsp',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(11,'上部','URL','/top.jsp',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(12,'退出','URL','/logout',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(13,'欢迎','URL','/welcome',1,'',1);
insert into t_resc(id,name,resc_type,resc_string,priority,descn,version) values(14,'用户信息','URL','/getUserInfo',1,'',1);

delete from t_resc_role;
insert into t_resc_role(resc_id,role_id) values(1,3);
insert into t_resc_role(resc_id,role_id) values(2,2);
insert into t_resc_role(resc_id,role_id) values(2,3);
insert into t_resc_role(resc_id,role_id) values(3,3);
insert into t_resc_role(resc_id,role_id) values(4,3);
insert into t_resc_role(resc_id,role_id) values(5,3);
insert into t_resc_role(resc_id,role_id) values(6,1);
insert into t_resc_role(resc_id,role_id) values(6,2);
insert into t_resc_role(resc_id,role_id) values(6,3);
insert into t_resc_role(resc_id,role_id) values(7,3);
insert into t_resc_role(resc_id,role_id) values(8,3);
insert into t_resc_role(resc_id,role_id) values(9,3);
insert into t_resc_role(resc_id,role_id) values(10,3);
insert into t_resc_role(resc_id,role_id) values(11,3);
insert into t_resc_role(resc_id,role_id) values(12,2);
insert into t_resc_role(resc_id,role_id) values(12,3);
insert into t_resc_role(resc_id,role_id) values(13,2);
insert into t_resc_role(resc_id,role_id) values(14,2);

delete from t_user_role;
insert into t_user_role(user_id,role_id) values(1,1);
insert into t_user_role(user_id,role_id) values(2,2);
insert into t_user_role(user_id,role_id) values(3,3);
          