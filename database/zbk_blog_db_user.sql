/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/3 20:18:22                            */
/*==============================================================*/


drop table if exists blog_user;

/*==============================================================*/
/* Table: blog_user                                             */
/*==============================================================*/
create table blog_user
(
   user_id              bigint(13) not null comment '用户ID',
   user_name            varchar(20) binary not null comment '用户名',
   password             varchar(32) binary comment '用户密码',
   status               varchar(1) not null comment '用户状态：0 禁用，1 启用',
   primary key (user_id)
);

alter table blog_user comment '可以编辑系统的表';

