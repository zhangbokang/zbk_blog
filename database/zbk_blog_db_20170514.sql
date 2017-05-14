/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/14 23:22:38                           */
/*==============================================================*/


drop table if exists classify;

drop table if exists comment;

drop table if exists doc;

drop table if exists tag;

/*==============================================================*/
/* Table: classify                                              */
/*==============================================================*/
create table classify
(
   classify_id          bigint(10) not null comment '分类id',
   name                 varchar(20) binary comment '分类名称',
   create_time          bigint(10) comment '分类创建时间的时间戳',
   primary key (classify_id)
);

alter table classify comment '文章分类表';

/*==============================================================*/
/* Table: comment                                               */
/*==============================================================*/
create table comment
(
   comment_id           bigint(10) not null,
   doc_id               bigint(10) comment '评论id',
   content              varchar(500) binary comment '评论内容',
   create_time          bigint(10) comment '评论发表时间的时间戳',
   favor_number         bigint(8) comment '评论点赞数量',
   oppose_number        bigint(8) comment '评论反对数量',
   primary key (comment_id)
);

alter table comment comment '文章评论表';

/*==============================================================*/
/* Table: doc                                                   */
/*==============================================================*/
create table doc
(
   doc_id               bigint(10) not null comment '文章id',
   title                varchar(50) binary comment '文章标题',
   doc_md               text comment '文章的mk形式，makedown语言写的',
   classify_id          bigint(5) comment '文章分类id',
   tag_id               bigint(5) comment '文章标签id',
   update_time          bigint(10) comment '最后更新时间的时间戳',
   favor_number         bigint(8) comment '赞成数量，点赞的数量',
   open_number          bigint(8) comment '打开次数',
   primary key (doc_id)
);

alter table doc comment '保存文章主题信息';

/*==============================================================*/
/* Table: tag                                                   */
/*==============================================================*/
create table tag
(
   tag_id               bigint(10) not null comment '标签id',
   name                 varchar(20) binary comment '标签名称',
   create_time          bigint(10) comment '标签创建时间的时间戳',
   primary key (tag_id)
);

alter table tag comment '文章标签表';

alter table comment add constraint FK_comment_doc foreign key (doc_id)
      references doc (doc_id) on delete restrict on update restrict;

alter table doc add constraint FK_doc_class foreign key (classify_id)
      references classify (classify_id) on delete restrict on update restrict;

alter table doc add constraint FK_doc_tag foreign key (tag_id)
      references tag (tag_id) on delete restrict on update restrict;

