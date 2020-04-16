## PCF社区
PCF社区是一个95后的新手码农搭建的IT技术交流平台，全称 (Programmer Communication forum)。
旨为打造更好的技术交流平台,相互学习，相互进步.
## 资料
[项目码云地址](https://gitee.com/xing_xuanxuan/forum)

[需求参考：ES中文社区](https://elasticsearch.cn/)

[JDK 1.8 中文文档](http://www.matools.com/api/java8)
## 开发环境
[Maven](https://maven.apache.org/)

[Jdk 1.8](https://www.oracle.com/java/technologies/javase-downloads.html)

[IntelliJ IDEA 2019.1.4 x64]( https://www.jetbrains.com/ )

window10 专业版


## 说明
[git登录官方文档](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
## 所用技术
##### 前端所用技术
[BootStrap](https://www.bootcss.com/)

[jquery 2.1.1](https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js)
##### 后端所用技术

[Spring Boot](https://spring.io/projects/spring-boot)

[Spring Boot Thymeleaf（模板引擎）](https://www.thymeleaf.org/index.html)

[OkHttp（java网络请求的框架）](https://square.github.io/okhttp/)

[FastJson](https://github.com/alibaba/fastjson)

[H2数据库 （已替换为mysql）](http://www.h2database.com/html/main.html)

[Mysql](https://www.mysql.com/)

[lombok](https://projectlombok.org/)

####  Mysql数据库对应的sql脚本

user表sql脚本
```mysql
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id键位',
  `account_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'github 账户id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字对应github login账户',
  `token` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用uuid作为token',
  `gmt_create` bigint(0) NULL DEFAULT NULL COMMENT '对应long类型 存储新增数据的时间戳',
  `gmt_modified` bigint(0) NULL DEFAULT NULL COMMENT '对应long类型 存储修改数据数据的时间戳',
  `bio` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应github的 个人描述',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'github头像地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


```

question表sql脚本
```mysql

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章名称',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容',
  `gmt_create` bigint(0) NULL DEFAULT NULL COMMENT '新增时间',
  `gmt_modified` bigint(0) NULL DEFAULT NULL COMMENT '更新时间',
  `creator` int(0) NULL DEFAULT 0 COMMENT '创建人id',
  `comment_count` int(0) NULL DEFAULT 0 COMMENT '评论数',
  `view_cout` int(0) NULL DEFAULT 0 COMMENT '阅读数',
  ` like_count` int(0) NULL DEFAULT 0 COMMENT '点赞数',
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


```
[Mybatis](https://blog.mybatis.org/)

[Http Client](http://hc.apache.org/)
