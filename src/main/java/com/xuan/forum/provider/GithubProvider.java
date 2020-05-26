package com.xuan.forum.provider;

import com.alibaba.fastjson.JSON;
import com.xuan.forum.commonException.MyRedirectException;
import com.xuan.forum.dto.GithubAccessTokenDto;
import com.xuan.forum.dto.GithubUser;
import com.xuan.forum.enums.ResultEnum;
import okhttp3.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/9
 * @描述：
 */
@Component
public class GithubProvider {
    private static Logger logger = LoggerFactory.getLogger(GithubProvider.class);

    /**
     * 获取Github回调的参数 access_token
     * @param accessTokenDto
     * @return access_token
     */
    public String getAccessToken(GithubAccessTokenDto accessTokenDto){
        MediaType   mediaType = MediaType.get("application/json; charset=utf-8");
        //使用okhttp发送post请求
        OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
//               返回的格式 accessTokenString：access_token=408e94489e51eea7fbdbb9892fbcf743442e6b87&scope=user&token_type=bearer
                String accessTokenString = response.body().string();
                //拆分获取accesstoken
                String tokenStr = accessTokenString.split("&")[0];
                //获取access_token
                String token = tokenStr.split("=")[1];
                return token;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }


    /**
     * 根据accessToken 获取GitHub的账户资料
     * @param accessToken token
     * @return
     */
    public  GithubUser getUserByAccessToken(String accessToken) {
        //请求地址
        String url = "https://api.github.com/user";
        String resultStr = sendGet(url, "access_token=" + accessToken);
        GithubUser githubUser = JSON.parseObject(resultStr, GithubUser.class);
        return githubUser;
    }



    /**
     * 向指定URL发送GET方法的请求
     * @param url  发送请求的URL
     * @param param   请求参数，格式：name1=value1&name2=value2
     * @return String 响应结果
     */
    public  String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            logger.info("github获取资料的连接是："+urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                logger.info(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("获取github的资料时异常:"+e);
            throw new MyRedirectException(ResultEnum.GITHUB_LOGIN_ERROR);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

}
