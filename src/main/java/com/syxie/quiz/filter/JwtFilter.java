package com.syxie.quiz.filter;

import com.alibaba.fastjson.JSONObject;
import com.syxie.quiz.model.Result;
import com.syxie.quiz.utils.JwtUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class JwtFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("JwtFilter init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        System.out.println("JwtFilter拦截到请求: " + method + " " + url);

        // 1) 放行 CORS 预检请求（OPTIONS）
        if ("OPTIONS".equalsIgnoreCase(method)) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
            return;
        }

        // 2) 登录与注册接口放行
        if (url.contains("/login") || url.contains("/register")) {
            System.out.println("登录/注册请求，放行");
            chain.doFilter(request, response);
            return;
        }

        // 3) 其他接口需要校验 token
        String token = request.getHeader("token");
        System.out.println("获取到的Token: " + token);

        if (!StringUtils.hasLength(token)) {
            System.out.println("Token不存在");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            Result result = Result.error("NOT_LOGIN");
            String noLogin = JSONObject.toJSONString(result);
            response.setContentType("application/json;charset=UTF-8");
            res.getWriter().write(noLogin);
            return;
        }

        try {
            String username = JwtUtil.parseToken(token);
            System.out.println("Token解析成功，用户名: " + username);
        } catch (Exception e) {
            System.out.println("Token解析失败: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            Result result = Result.error("NOT_LOGIN");
            String noLogin = JSONObject.toJSONString(result);
            response.setContentType("application/json;charset=UTF-8");
            res.getWriter().write(noLogin);
            return;
        }

        // 4) 放行
        System.out.println("Token验证通过，放行请求");
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        System.out.println("JwtFilter destroy");
    }
}