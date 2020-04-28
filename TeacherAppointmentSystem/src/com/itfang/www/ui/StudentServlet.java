package com.itfang.www.ui;

import com.itfang.www.bbl.servic.StudentService;
import com.itfang.www.bbl.servic.StudentServiceImpl;
import com.itfang.www.dal.po.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet("/StudentUser/*")
/**
 * @author it-fang
 * 接收学生用户相关操作请求
 */
public class StudentServlet extends BaseServlet {
    private StudentService studentService = new StudentServiceImpl();

    /**
     * 检查用户名是否存在
     * @param request
     * @param response
     * @return resultInfo
     * @throws ServletException,IOException,SQLException
     */
    public Object checkUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        String username = request.getParameter("username");
        //3,传入参数
        Object resultInfo = studentService.checkUsername(username);
        return resultInfo;
    }

}
