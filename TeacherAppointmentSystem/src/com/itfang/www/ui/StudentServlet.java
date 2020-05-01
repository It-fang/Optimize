package com.itfang.www.ui;

import com.itfang.www.bbl.servic.StudentService;
import com.itfang.www.bbl.servic.StudentServiceImpl;
import com.itfang.www.dal.po.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Map;

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

    /**
     * 登陆学生账号
     * @param request
     * @param response
     * @return resultInfo
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
        //3,封装studentUser对象
        StudentUser studentUser = new StudentUser();
        studentUser.setUsername(username);
        studentUser.setPassword(password);
        //4,传入参数
        Object resultInfo = studentService.login(studentUser);
        //5,将用户存入Session域中
        HttpSession session = request.getSession();
        session.setAttribute("studentUser",studentUser);
        return resultInfo;
    }

    /**
     * 分页查询所有教师信息
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public Object queryTeacher(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        if(currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "5";
        }
        //获取查询条件参数
        Map<String, String[]> condition = request.getParameterMap();
        Page<Teacher> page = null;
        page = studentService.queryTeacher(currentPage,rows,condition);
        //3,将获取结果存入request域中
        request.setAttribute("page",page);
        request.setAttribute("condition",condition);
        ResultInfo resultInfo = new ResultInfo();
        return resultInfo;
    }
}
