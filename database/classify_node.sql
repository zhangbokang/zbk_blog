drop table if exists classify_node;

/*==============================================================*/
/* Table: classify_node                                         */
/*==============================================================*/
create table classify_node
(
  id                   bigint(13) not null comment '分类NodeId',
  text                 varchar(50) not null comment '分类Node名称',
  children             boolean comment '子节点',
  parent_id            bigint(13) comment '父节点Id',
  update_time          bigint(13),
  primary key (id)
);

alter table classify_node comment '分类Node表';
