import com.xuan.forum.ForumApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/6/1
 * @描述： 定时器测试
 */
@SpringBootTest(classes = ForumApplication.class)
public class ScheduleTest {

    @Test
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        showLog("每隔五秒钟执行一次： " + new Date().toLocaleString());
    }


    private void showLog(String value){
        System.out.println(value);
    }
}
