package com.itfang.www.ui;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author it-fang
 * 对教师用户相关资源的请求进行拦截
 */
@WebFilter("/*")
public class TeacherUserFilter implements Filter {


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
