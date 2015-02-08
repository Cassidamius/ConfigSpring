delete from user_info where id = 0;
insert into user_info(id, name,nick_name, first_name, last_name, password, birthday, delete_flag, create_time, update_time) 
          values (0, 'admin', '', '', '', '111111', '1975-01-01', 0, current timestamp, current timestamp);