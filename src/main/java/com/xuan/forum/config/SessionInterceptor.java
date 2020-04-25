package com.xuan.forum.config;

import com.xuan.forum.dto.PaginationDto;
import com.xuan.forum.mapper.UserMapper;
import com.xuan.forum.model.User;
import com.xuan.forum.provider.GithubProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @创建人： xuanxuan
 * @创建时间： 2020/4/22
 * @描述： 拦截器拦截逻辑
 */

@Component
public class SessionInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Autowired
    private UserMapper userMapper;

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("进入拦截器，拦截路径"+request.getRequestURI());
        //获取全部cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            //遍历全部Cookie
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    //获取token
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    //如果数据库中有token的话，代表已经登录
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        logger.info("拦截器通过成功，用户放入Session的信息为" + user);
                        return true;
                    }
                }
            }
        }
        logger.info("拦截器不通过 返回首页！");
        //拦截验证不通过 返回首页
        response.sendRedirect("/");
        return false;
    }


    ////该方法在action执行后，生成视图前执行。在这里，我们有机会修改视图层数据。
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //最后执行，通常用于释放资源，处理异常。我们可以根据ex是否为空，来进行相关的异常处理。
    //因为我们在平时处理异常时，都是从底层向上抛出异常，最后到了spring框架从而到了这个方法中。
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
