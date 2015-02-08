drop table if exists sys_users;
create table SYS_USERS  
(  
  USER_ID       VARCHAR(32) not null,  
  USER_ACCOUNT  VARCHAR(30),  
  USER_NAME     VARCHAR(40),  
  USER_PASSWORD VARCHAR(100),  
  USER_DESC     VARCHAR(100),  
  ENABLED       int,  
  ISSYS         int,  
  USER_DEPT     VARCHAR(20),  
  USER_DUTY     VARCHAR(10),  
  SUB_SYSTEM    VARCHAR(30)  
 );  
alter table SYS_USERS add constraint PK_PUB_USERS primary key (USER_ID);

drop table if exists sys_roles;
create table SYS_ROLES  
(  
  ROLE_ID   VARCHAR(32) not null,  
  ROLE_NAME VARCHAR(40),  
  ROLE_DESC VARCHAR(100),  
  ENABLED   int,  
  ISSYS     int,  
  MODULE    VARCHAR(4)  
);  
alter table SYS_ROLES add constraint PK_PUB_ROLES primary key (ROLE_ID);

drop table if exists SYS_AUTHORITIES;
create table SYS_AUTHORITIES  
(  
  AUTHORITY_ID   VARCHAR(32) not null,  
  AUTHORITY_NAME VARCHAR(40),  
  AUTHORITY_DESC VARCHAR(100),  
  ENABLED        int,  
  ISSYS          int,  
  MODULE         VARCHAR(4)  
);  
alter table SYS_AUTHORITIES add constraint PK_PUB_AUTHORITIES primary key (AUTHORITY_ID); 

drop table if exists SYS_RESOURCES;
create table SYS_RESOURCES  
(  
  RESOURCE_ID     VARCHAR(32) not null,  
  RESOURCE_NAME   VARCHAR(100),  
  RESOURCE_DESC   VARCHAR(100),  
  RESOURCE_TYPE   VARCHAR(40),  
  RESOURCE_STRING VARCHAR(200),  
  PRIORITY        int,  
  ENABLED         int,  
  ISSYS           int,  
  MODULE          VARCHAR(4)  
);  
alter table SYS_RESOURCES add constraint PK_PUB_RESOURCES primary key (RESOURCE_ID); 

drop table if exists SYS_USERS_ROLES;
create table SYS_USERS_ROLES  
(  
  ID      int not null,  
  USER_ID VARCHAR(32),  
  ROLE_ID VARCHAR(32),  
  ENABLED int  
);  
-- Create/Recreate primary, unique and foreign key constraints   
alter table SYS_USERS_ROLES  add constraint PK_PUB_USERS_ROLES primary key (ID);  
  
alter table SYS_USERS_ROLES  add constraint FK_USERS_ROLES_ROLES foreign key (ROLE_ID)  references SYS_ROLES (ROLE_ID);  
alter table SYS_USERS_ROLES  add constraint FK_USERS_ROLES_USERS foreign key (USER_ID)  references SYS_USERS (USER_ID);

drop table if exists SYS_ROLES_AUTHORITIES;
create table SYS_ROLES_AUTHORITIES  
(  
  ID           int not null,  
  ROLE_ID      VARCHAR(32),  
  AUTHORITY_ID VARCHAR(32),  
  ENABLED      int  
);  
-- Create/Recreate primary, unique and foreign key constraints   
alter table SYS_ROLES_AUTHORITIES  add constraint PK_PUB_ROLES_AUTHORITY primary key (ID);  
alter table SYS_ROLES_AUTHORITIES  add constraint FK_PUB_ROLES_AUTHORITIES_AU foreign key (AUTHORITY_ID)  references SYS_AUTHORITIES (AUTHORITY_ID);  
alter table SYS_ROLES_AUTHORITIES  add constraint FK_PUB_ROLES_AUTHORITIES_ROLES foreign key (ROLE_ID)  references SYS_ROLES (ROLE_ID); 


drop table if exists SYS_AUTHORITIES_RESOURCES;
create table SYS_AUTHORITIES_RESOURCES  
(  
  ID           int not null,  
  AUTHORITY_ID VARCHAR(32),  
  RESOURCE_ID  VARCHAR(32),  
  ENABLED      int  
);  
-- Create/Recreate primary, unique and foreign key constraints   
alter table SYS_AUTHORITIES_RESOURCES  add constraint PK_PUB_AUTHORITIES_RE primary key (ID);  
        
alter table SYS_AUTHORITIES_RESOURCES  add constraint FK_PUB_AUTHORITIES_RE_AU foreign key (AUTHORITY_ID)  references SYS_AUTHORITIES (AUTHORITY_ID);  
alter table SYS_AUTHORITIES_RESOURCES  add constraint FK_PUB_AUTHORITIES_RE_RE foreign key (RESOURCE_ID)  references SYS_RESOURCES (RESOURCE_ID);  