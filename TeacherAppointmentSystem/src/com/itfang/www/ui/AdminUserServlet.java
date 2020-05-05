package com.itfang.www.ui;

import com.itfang.www.bbl.servic.AdminService;
import com.itfang.www.bbl.servic.AdminServiceImpl;
import com.itfang.www.dal.po.AdminUser;
import com.itfang.www.dal.po.Application;
import com.itfang.www.dal.po.ResultInfo;
import com.itfang.www.dal.po.StudentUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author it-fang
 * 管理员用户相关操作请求
 */
@WebServlet("/AdminUser/*")
public class AdminUserServlet extends BaseServlet {
    AdminService adminService = new AdminServiceImpl();

    /**
     * 登陆管理员账号
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
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

    /**
     * 查询注册申请并存入request域中
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public Object queryRegister(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        ResultInfo resultInfo = adminService.queryRegister();
        request.setAttribute("studentUsers",((List<StudentUser>)resultInfo.getData()));
        return resultInfo;
    }

    /**
     * 同意注册申请
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public Object agreeRegister(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String _studentId = request.getParameter("studentId");
        int studentId = Integer.parseInt(_studentId);
        //3,封装studentUser对象
        StudentUser studentUser = new StudentUser();
        studentUser.setUsername(username);
        studentUser.setPassword(password);
        studentUser.setStudentId(studentId);
        //4,传入参数
        Object resultInfo = adminService.agreeRegister(studentUser);
        return resultInfo;
    }

    public Object refuseRegister(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        String _studentId = request.getParameter("studentId");
        int studentId = Integer.parseInt(_studentId);
        //3,传入参数
        Object resultInfo = adminService.refuseRegister(studentId);
        return resultInfo;
    }
}
