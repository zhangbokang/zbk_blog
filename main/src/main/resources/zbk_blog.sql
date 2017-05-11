CREATE DATABASE `myblog` /*!40100 COLLATE 'utf8_general_ci' */

CREATE TABLE `myblog`.`blogdoc`(
  `blogid` BIGINT(20) NOT NULL COMMENT '博客文章ID',
  `blogtitle` VARCHAR(100) NOT NULL COMMENT '博客标题',
  `blogclassid` BIGINT(5) COMMENT '博客分类id',
  `blogtagid` BIGINT(5) COMMENT '博客标签id',
  `blogmd` TEXT COMMENT '博客文章md',
  `updatatime` BIGINT(30) COMMENT '博客最后更新时间',
  `updatatimestr` VARCHAR(19) COMMENT '最后更新时间的字符串形式',
  `opennumber` BIGINT(10) COMMENT '浏览量',
  `supportnumber` BIGINT(10) COMMENT '支持量',
  PRIMARY KEY (`blogid`)
);
COMMENT='文章表';

/*创建文章分类表和文章标签表*/
CREATE TABLE `myblog`.`blogclass`(
  `blogClassId` BIGINT(5) NOT NULL AUTO_INCREMENT COMMENT '文章分类ID',
  `blogClassName` VARCHAR(20) COMMENT '分类名称',
  PRIMARY KEY (`blogClassId`)
)
COMMENT='文章分类表';
CREATE TABLE `myblog`.`blogtag`(
  `blogTagId` BIGINT(5) NOT NULL AUTO_INCREMENT COMMENT '文章标签ID',
  `blogTagName` VARCHAR(30) COMMENT '标签名称',
  PRIMARY KEY (`blogTagId`)
)
COMMENT='文章标签表';

/*添加外键约束*/
ALTER TABLE `myblog`.`blogdoc`
  ADD CONSTRAINT `bclass` FOREIGN KEY (`blogclassid`) REFERENCES `myblog`.`blogclass`(`blogClassId`),
  ADD CONSTRAINT `btag` FOREIGN KEY (`blogtagid`) REFERENCES `myblog`.`blogtag`(`blogTagId`);
