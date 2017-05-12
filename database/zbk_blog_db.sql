/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/12 23:39:41                           */
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
   id                   bigint(10) not null comment '����id',
   name                 varchar(20) binary comment '��������',
   create_time          bigint(10) comment '���ഴ��ʱ���ʱ���',
   primary key (id)
);

alter table classify comment '���·����';

/*==============================================================*/
/* Table: comment                                               */
/*==============================================================*/
create table comment
(
   id                   bigint(10) comment '����id',
   content              varchar(500) binary comment '��������',
   create_time          bigint(10) comment '���۷���ʱ���ʱ���',
   favor_number         bigint(8) comment '���۵�������',
   oppose_number        bigint(8) comment '���۷�������'
);

alter table comment comment '�������۱�';

/*==============================================================*/
/* Table: doc                                                   */
/*==============================================================*/
create table doc
(
   id                   bigint(10) not null comment '����id',
   title                varchar(50) binary comment '���±���',
   doc_md               text comment '���µ�mk��ʽ��makedown����д��',
   classify_id          bigint(5) comment '���·���id',
   tag_id               bigint(5) comment '���±�ǩid',
   update_time          bigint(10) comment '������ʱ���ʱ���',
   favor_number         bigint(8) comment '�޳����������޵�����',
   oppose_number        bigint(8) comment '������������ȵ�����',
   primary key (id)
);

alter table doc comment '��������������Ϣ';

/*==============================================================*/
/* Table: tag                                                   */
/*==============================================================*/
create table tag
(
   id                   bigint(10) not null comment '��ǩid',
   name                 varchar(20) binary comment '��ǩ����',
   create_time          bigint(10) comment '��ǩ����ʱ���ʱ���',
   primary key (id)
);

alter table tag comment '���±�ǩ��';

alter table comment add constraint FK_comment_doc foreign key (id)
      references doc (id) on delete restrict on update restrict;

alter table doc add constraint FK_doc_class foreign key (classify_id)
      references classify (id) on delete restrict on update restrict;

alter table doc add constraint FK_doc_tag foreign key (tag_id)
      references tag (id) on delete restrict on update restrict;

