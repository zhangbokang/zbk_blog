/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/13 6:40:19                            */
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
   classify_id          bigint(10) not null comment '����id',
   name                 varchar(20) binary comment '��������',
   create_time          bigint(10) comment '���ഴ��ʱ���ʱ���',
   primary key (classify_id)
);

alter table classify comment '���·����';

/*==============================================================*/
/* Table: comment                                               */
/*==============================================================*/
create table comment
(
   comment_id           bigint(10) not null,
   doc_id               bigint(10) comment '����id',
   content              varchar(500) binary comment '��������',
   create_time          bigint(10) comment '���۷���ʱ���ʱ���',
   favor_number         bigint(8) comment '���۵�������',
   oppose_number        bigint(8) comment '���۷�������',
   primary key (comment_id)
);

alter table comment comment '�������۱�';

/*==============================================================*/
/* Table: doc                                                   */
/*==============================================================*/
create table doc
(
   doc_id               bigint(10) not null comment '����id',
   title                varchar(50) binary comment '���±���',
   doc_md               text comment '���µ�mk��ʽ��makedown����д��',
   classify_id          bigint(5) comment '���·���id',
   tag_id               bigint(5) comment '���±�ǩid',
   update_time          bigint(10) comment '������ʱ���ʱ���',
   favor_number         bigint(8) comment '�޳����������޵�����',
   open_number          bigint(8) comment '�򿪴���',
   primary key (doc_id)
);

alter table doc comment '��������������Ϣ';

/*==============================================================*/
/* Table: tag                                                   */
/*==============================================================*/
create table tag
(
   tag_id               bigint(10) not null comment '��ǩid',
   name                 varchar(20) binary comment '��ǩ����',
   create_time          bigint(10) comment '��ǩ����ʱ���ʱ���',
   primary key (tag_id)
);

alter table tag comment '���±�ǩ��';

alter table comment add constraint FK_comment_doc foreign key (doc_id)
      references doc (doc_id) on delete restrict on update restrict;

alter table doc add constraint FK_doc_class foreign key (classify_id)
      references classify (classify_id) on delete restrict on update restrict;

alter table doc add constraint FK_doc_tag foreign key (tag_id)
      references tag (tag_id) on delete restrict on update restrict;

