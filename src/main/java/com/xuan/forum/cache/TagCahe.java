package com.xuan.forum.cache;

import com.alibaba.fastjson.JSON;
import com.xuan.forum.dto.TagDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/5/19
 * @描述： 标签缓存
 */
public class TagCahe {
    private static Logger logger = LoggerFactory.getLogger(TagCahe.class);

    public static List<TagDto> getTag(){
        ArrayList<TagDto> tagDtos = new ArrayList<>();


        tagDtos.add(
                new TagDto("tag-yuyan","开发语言",
                        Arrays.asList("js","php","css","html","java","node","python")
                )
        );

        tagDtos.add(
                new TagDto("tag-pingtai","平台框架",
                        Arrays.asList("Spring","Spring Boot","Spring Mvc","Mybatis")
                )
        );

        tagDtos.add(
                new TagDto("tag-fuwuqi","服务器",
                        Arrays.asList("Linux","nginx","docker","aoache","tomcat")
                )
        );

        tagDtos.add(
                new TagDto("tag-fuwuqi","数据库",
                        Arrays.asList("Mysql","oracle","sqlserver","redis","mongdb")
                )
        );
        tagDtos.add(
                new TagDto("tag-gongju","开发工具",
                        Arrays.asList("intellij idea","eclipse","git","maven","vim")
                )
        );
        logger.info("返回的标签是："+ JSON.toJSONString(tagDtos));
        return tagDtos;
    }
}
