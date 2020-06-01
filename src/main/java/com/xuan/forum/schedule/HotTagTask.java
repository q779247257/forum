package com.xuan.forum.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/6/1
 * @描述： 热门标签定时任务
 */
@Component
public class HotTagTask {
    private static Logger logger = LoggerFactory.getLogger(HotTagTask.class);

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        logger.info("每隔五秒钟执行一次： " + new Date().toLocaleString());
    }

}
