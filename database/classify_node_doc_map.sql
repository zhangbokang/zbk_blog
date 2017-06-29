drop table if exists classify_node_doc_map;

/*==============================================================*/
/* Table: classify_node_doc_map                                 */
/*==============================================================*/
create table classify_node_doc_map
(
   id                   bigint(13) comment '分类NodeId',
   doc_id               bigint(13) comment '文章id'
);

alter table classify_node_doc_map add constraint FK_Reference_5 foreign key (id)
references classify_node (id) on delete restrict on update restrict;

alter table classify_node_doc_map add constraint FK_Reference_6 foreign key (doc_id)
references doc (doc_id) on delete restrict on update restrict;