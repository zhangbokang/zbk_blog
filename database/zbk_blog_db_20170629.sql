/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/29 15:56:50                           */
/*==============================================================*/


drop table if exists blog_user;

drop table if exists classify_node;

drop table if exists classify_node_doc_map;

drop table if exists comment;

drop table if exists doc;

drop table if exists tag;

/*==============================================================*/
/* Table: blog_user                                             */
/*==============================================================*/
create table blog_user
(
   user_id              bigint(20) not null comment '用户ID',
   user_name            varchar(20) binary not null comment '用户名',
   password             varchar(20) binary comment '用户密码',
   status               varchar(1) not null comment '用户状态：0 禁用，1 启用',
   primary key (user_id)
);

alter table blog_user comment '可以编辑系统的表';

/*==============================================================*/
/* Table: classify_node                                         */
/*==============================================================*/
create table classify_node
(
   id                   bigint(20) not null comment '分类NodeId',
   text                 varchar(50) not null comment '分类Node名称',
   children             boolean comment '子节点',
   parent_id            bigint(20) comment '父节点Id',
   update_time          bigint(13),
   primary key (id)
);

alter table classify_node comment '分类Node表';

/*==============================================================*/
/* Table: classify_node_doc_map                                 */
/*==============================================================*/
create table classify_node_doc_map
(
   id                   bigint(20) comment '分类NodeId',
   doc_id               bigint(20) comment '文章id'
);

/*==============================================================*/
/* Table: comment                                               */
/*==============================================================*/
create table comment
(
   comment_id           bigint(20) not null,
   doc_id               bigint(20) comment '评论id',
   content              varchar(500) binary comment '评论内容',
   create_time          bigint(13) comment '评论发表时间的时间戳',
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
   doc_id               bigint(20) not null comment '文章id',
   title                varchar(50) binary comment '文章标题',
   doc_md               text comment '文章的mk形式，makedown语言写的',
   tag_id               bigint(20) comment '文章标签id',
   update_time          bigint(13) comment '最后更新时间的时间戳',
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
   tag_id               bigint(20) not null comment '标签id',
   name                 varchar(20) binary comment '标签名称',
   create_time          bigint(13) comment '标签创建时间的时间戳',
   primary key (tag_id)
);

alter table tag comment '文章标签表';

alter table classify_node_doc_map add constraint FK_classify_node_id_fk foreign key (id)
      references classify_node (id) on delete restrict on update restrict;

alter table classify_node_doc_map add constraint FK_doc_docid_fk foreign key (doc_id)
      references doc (doc_id) on delete restrict on update restrict;

alter table comment add constraint FK_comment_doc foreign key (doc_id)
      references doc (doc_id) on delete restrict on update restrict;

alter table doc add constraint FK_doc_tag foreign key (tag_id)
      references tag (tag_id) on delete restrict on update restrict;

/* 改变是否有子分类字段名称*/
ALTER TABLE zbk_blog.classify_node CHANGE children children_byte TINYINT(1) COMMENT '子节点';

/* 添加默认管理用户*/
insert  into `blog_user`(`user_id`,`user_name`,`password`,`status`) values (10000000000001,'zbk','648727186f3dc12ac987605722e47d09','1');
