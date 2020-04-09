## 轩轩社区

## 资料
[项目码云地址:](https://gitee.com/xing_xuanxuan/forum)

[需求参考：ES中文社区](https://elasticsearch.cn/)
## 工具
 
## 说明
[git登录官方文档](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
## 所用框架
##### 前端框架
[BootStrap](https://www.bootcss.com/)



[jquery 2.1.1](https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js)
##### 后端框架

[Spring Boot](https://spring.io/projects/spring-boot)

[OkHttp](https://square.github.io/okhttp/)

[FastJson](https://github.com/alibaba/fastjson)

[H2数据库](http://www.h2database.com/html/main.html)

H2数据库对应的sql脚本
```h2
create table USER
(
    ID           INTEGER default NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_33DF8013_B1FB_4F67_86A0_3DE6271AE67E" auto_increment,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(50),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GME_MODIFIED BIGINT,
    constraint USER_PK
        primary key (ID)
);
comment on column USER.ID is '用户id';
comment on column USER.ACCOUNT_ID is 'github 账户id';
comment on column USER.NAME is '名字';
comment on column USER.TOKEN is 'Cookie作为token  使用uuid作为token';
comment on column USER.GMT_CREATE is 'bigint 对应 long类型 存储时间戳';
comment on column USER.GME_MODIFIED is '修改时间，存储的为时间戳';
```