

drop table if exists t_user;

create table t_user (
    id int primary key auto_increment,
    username varchar(255),
    password varchar(255)
);


insert into t_user(username,password) values('admin','123');
insert into t_user(username, password) values ('zhangsan','123');

commit ;

select * from t_user;

