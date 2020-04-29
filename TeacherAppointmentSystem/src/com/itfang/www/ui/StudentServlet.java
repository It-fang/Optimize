package com.itfang.www.ui;

import com.itfang.www.bbl.servic.StudentService;
import com.itfang.www.bbl.servic.StudentServiceImpl;
import com.itfang.www.dal.po.Student;
import com.itfang.www.dal.po.StudentUser;

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

    /**
     * 注册学生账号
     * @param request
     * @param response
     * @return resultInfo
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public Object register(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String number = request.getParameter("number");
        String college = request.getParameter("college");
        String major = request.getParameter("major");
        String clas = request.getParameter("clas");
        //3,封装Student对象
        Student student = new Student();
        student.setName(name);
        student.setSex(sex);
        student.setNumber(number);
        student.setCollege(college);
        student.setMajor(major);
        student.setClas(clas);
        //4,封装StudentUser对象
        StudentUser studentUser = new StudentUser();
        studentUser.setUsername(username);
        studentUser.setPassword(password);
        //4,传入参数
        Object resultInfo = studentService.register(student,studentUser);
        return resultInfo;
    }
}
