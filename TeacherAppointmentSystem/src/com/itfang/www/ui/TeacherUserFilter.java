package com.itfang.www.ui;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author it-fang
 * 对教师用户相关资源的请求进行拦截
 */
@WebFilter(urlPatterns = {"/TeacherUser/*","/teacherregister.html","/queryapplication.jsp","/modify.jsp","/agree.jsp"})
public class TeacherUserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1,强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        //2,获取资源路径
        String uri = request.getRequestURI();
        //3,判断是否跟教师用户登陆和注册相关的资源
        if (uri.contains("/teacherlogin.jsp")|| uri.contains("TeacherUser/checkUsername")
                || uri.contains("TeacherUser/login")) {
            chain.doFilter(req, resp);
        } else if (uri.contains("/teacherregister.html") || uri.contains("TeacherUser/register")) {
            //判断是否登陆超级管理员账号
            Object adminUser = request.getSession().getAttribute("adminUser");
            if (adminUser != null){
                chain.doFilter(req, resp);
            }else {
                request.setAttribute("msg","您还没有登陆管理员账号,请先登陆!");
                request.getRequestDispatcher("/adminlogin.jsp").forward(request,resp);
            }
        }else {
            //判断是否登陆教师账号
            Object teacherUser = request.getSession().getAttribute("teacherUser");
            if (teacherUser != null){
                chain.doFilter(req, resp);
            }else {
                request.setAttribute("msg","您还没有登陆,请先登陆!");
                request.getRequestDispatcher("/teacherlogin.jsp").forward(request,resp);
            }
        }
    }
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
