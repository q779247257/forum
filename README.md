## PCF社区
PCF社区是一个95后的新手码农IT技术交流平台，全称 (Programmer Communication forum)。
旨为打造更好的技术交流平台,相互学习，相互进步.
## 资料
[项目码云地址](https://gitee.com/xing_xuanxuan/forum)

[需求参考：ES中文社区](https://elasticsearch.cn/)
## 开发环境
[Maven](https://maven.apache.org/)

[Jdk 1.8](https://www.oracle.com/java/technologies/javase-downloads.html)

 
## 说明
[git登录官方文档](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
## 所用框架
##### 前端框架
[BootStrap](https://www.bootcss.com/)

[jquery 2.1.1](https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js)
##### 后端框架

[Spring Boot](https://spring.io/projects/spring-boot)

[Spring Boot Thymeleaf（模板引擎）](https://www.thymeleaf.org/index.html)

[OkHttp（java网络请求的框架）](https://square.github.io/okhttp/)

[FastJson](https://github.com/alibaba/fastjson)

[H2数据库 （已替换为mysql）](http://www.h2database.com/html/main.html)

[Mysql](https://www.mysql.com/)

Mysql数据库对应的sql脚本
```mysql
/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : forum

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 10/04/2020 11:27:50
*/

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

```
[Mybatis](https://blog.mybatis.org/)
