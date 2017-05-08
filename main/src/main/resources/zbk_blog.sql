CREATE DATABASE `myblog` /*!40100 COLLATE 'utf8_general_ci' */

CREATE TABLE `myblog`.`blogdoc`(
  `blogid` BIGINT(20) NOT NULL COMMENT '博客文章ID',
  `blogtitle` VARCHAR(100) NOT NULL COMMENT '博客标题',
  `blogclass` VARCHAR(100) COMMENT '博客分类',
  `blogtag` VARCHAR(100) COMMENT '博客标签',
  `blogmd` TEXT COMMENT '博客文章md',
  `updatatime` BIGINT(30) COMMENT '博客最后更新时间',
  `updatatimestr` VARCHAR(19) COMMENT '最后更新时间的字符串形式',
  `opennumber` BIGINT(10) COMMENT '浏览量',
  `supportnumber` BIGINT(10) COMMENT '支持量',
  PRIMARY KEY (`blogid`)
);