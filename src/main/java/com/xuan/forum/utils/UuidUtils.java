package com.xuan.forum.utils;

import java.util.UUID;

/**
 * @Author: 轩轩
 * @Date: 2020/3/15 21:58
 * @description:
 */
public class UuidUtils {


    public static String getUuid() {
        String uuid = UUID.randomUUID().toString();  //转化为String对象
        System.out.println(uuid);  //打印UUID
        uuid = uuid.replace("-", "");    //因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可
        return uuid;
    }
}
