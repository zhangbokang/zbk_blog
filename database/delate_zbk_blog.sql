ALTER TABLE `comment`
	DROP FOREIGN KEY `FK_comment_doc`;

ALTER TABLE `doc`
	DROP FOREIGN KEY `FK_doc_class`,
	DROP FOREIGN KEY `FK_doc_tag`;

