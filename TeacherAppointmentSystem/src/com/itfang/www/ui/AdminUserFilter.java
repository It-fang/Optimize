package com.itfang.www.ui;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author it-fang
 * 对请求进行拦截判断是否处于登陆状态
 */
@WebFilter(urlPatterns = {"/AdminUser/*","/queryregister.jsp"})
public class AdminUserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1,强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        //2,获取资源路径
        String uri = request.getRequestURI();
        if (uri.contains("/adminlogin.jsp") || uri.contains("AdminUser/login")) {
            chain.doFilter(req, resp);
        }else {
            Object adminUser = request.getSession().getAttribute("adminUser");
            if (adminUser != null){
                chain.doFilter(req, resp);
            }else {
                request.setAttribute("msg","您还没有登陆,请先登陆!");
                request.getRequestDispatcher("/adminlogin.jsp").forward(request,resp);
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
