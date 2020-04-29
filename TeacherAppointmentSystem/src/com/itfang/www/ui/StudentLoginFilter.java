package com.itfang.www.ui;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author it-fang
 * 对请求进行拦截判断是否处于登陆状态
 */
@WebFilter("/*")
public class StudentLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1,强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        //2,获取资源路径
        String uri = request.getRequestURI();
        //3,判断是否跟学生用户登陆和注册相关的资源
        if (uri.contains("/studentlogin.jsp") || uri.contains("/studentregister.html") ||uri.contains("/css/*")
                || uri.contains("/js/*") || uri.contains("/img/*") ||uri.contains("StudentUser/register")
                || uri.contains("StudentUser/checkUsername")){
            chain.doFilter(req, resp);
        }else {
            //判断学生用户是否登陆
            Object user = request.getSession().getAttribute("user");
            if (user != null){
                chain.doFilter(req, resp);
            }else {
                request.setAttribute("msg","您还没有登陆,请先登陆!");
                request.getRequestDispatcher("/studentlogin.jsp").forward(request,resp);
            }
        }
    }
    @Override
    public void init(FilterConfig config) throws ServletException {

    }
    @Override
    public void destroy() {
    }
}
