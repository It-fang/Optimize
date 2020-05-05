package com.itfang.www.ui;

import com.itfang.www.bbl.servic.AdminService;
import com.itfang.www.bbl.servic.AdminServiceImpl;
import com.itfang.www.dal.po.AdminUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author it-fang
 * 管理员用户相关操作请求
 */
@WebServlet("/AdminUser/*")
public class AdminUserServlet extends BaseServlet {
    AdminService adminService = new AdminServiceImpl();

    public Object login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //3,封装管理员用户对象
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(username);
        adminUser.setPassword(password);
        //4,传入参数
        Object resultInfo = adminService.login(adminUser);
        //5,将管理员用户存入Session域中
        request.getSession().setAttribute("adminUser",adminUser);
        return resultInfo;
    }
}
