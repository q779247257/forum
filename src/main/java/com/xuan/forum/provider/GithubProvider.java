package com.xuan.forum.provider;

import com.alibaba.fastjson.JSON;
import com.xuan.forum.dto.GithubAccessTokenDto;
import com.xuan.forum.dto.GithubUser;
import okhttp3.*;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketException;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/9
 * @描述：
 */
@Component
public class GithubProvider {
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
     * H2Http 发送 get请求  暂时弃用 有时候会包 SocketException
     * 根据accessToken  获取github用户的资料
     * @param accessToken
     * @return GithubUser 存放 git账户相关信息
     */
    public GithubUser getUser(String accessToken ){
        String getUrl = "https://api.github.com/user?access_token="+accessToken;
        System.out.println("获取github账户资料的链接为："+getUrl);
        //使用okhttp发送get请求
        OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(getUrl)
                    .build();

        try (Response response = client.newCall(request).execute()){
            //获取git官方返回的json
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            //拿到github的账户资料后再关闭 防止报文异常关闭
            return githubUser;
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
        String getUrl = "https://api.github.com/user?access_token="+accessToken;
        // 获取http客户端
        String resultStr = null;
        CloseableHttpClient client = HttpClients.createDefault();
        // 通过httpget方式来实现我们的get请求
        HttpGet httpGet = new HttpGet(getUrl);
//        httpGet.setProtocolVersion(HttpVersion.HTTP_1_0);
//        httpGet.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);

        CloseableHttpResponse response= null;
        try {
            // 通过client调用execute方法，得到我们的执行结果就是一个response，所有的数据都封装在response里面了
            response = client.execute(httpGet);
            // HttpEntity
            // 是一个中间的桥梁，在httpClient里面，是连接我们的请求与响应的一个中间桥梁，所有的请求参数都是通过HttpEntity携带过去的
            // 所有的响应的数据，也全部都是封装在HttpEntity里面
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils 来将我们的数据转换成字符串
            resultStr = EntityUtils.toString(entity, "UTF-8");
            response.close();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            httpGet.abort();
        }
        System.out.println("获取github账户资料的链接为："+getUrl);
        GithubUser githubUser = JSON.parseObject(resultStr, GithubUser.class);
        return githubUser;
    }

}
