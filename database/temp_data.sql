INSERT INTO `zbk_blog`.`tag` (`tag_id`, `name`, `create_time`) VALUES ('1495084153930', 'ORM', '1495084153930');
SELECT `tag_id`, `name`, `create_time` FROM `zbk_blog`.`tag` WHERE  `tag_id`=1495084153930;

INSERT INTO `zbk_blog`.`classify` (`classify_id`, `name`, `create_time`) VALUES ('1495084153960', 'Hibernate', '1495084153960');
SELECT `classify_id`, `name`, `create_time` FROM `zbk_blog`.`classify` WHERE  `classify_id`=1495084153960;

/*初始用户
    用户zbk
    密码Zhang123
*/
INSERT INTO `zbk_blog`.`blog_user` (`user_id`, `user_name`, `password`, `status`) VALUES ('1000000000001', 'zbk', '648727186f3dc12ac987605722e47d09', '1');

