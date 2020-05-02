package com.itfang.www.ui;

import com.itfang.www.bbl.servic.StudentService;
import com.itfang.www.bbl.servic.StudentServiceImpl;
import com.itfang.www.bbl.servic.TeacherService;
import com.itfang.www.bbl.servic.TeacherServiceImpl;
import com.itfang.www.dal.po.Student;
import com.itfang.www.dal.po.StudentUser;
import com.itfang.www.dal.po.Teacher;
import com.itfang.www.dal.po.TeacherUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author it-fang
 * 接收教师用户对象相关操作的请求
 */
@WebServlet("/TeacherUser/*")
public class TeacherUserServlet extends BaseServlet {
    private TeacherService teacherService = new TeacherServiceImpl();

    /**
     * 检查用户名是否存在
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public Object checkUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        String username = request.getParameter("username");
        //3,传入参数
        Object resultInfo = teacherService.checkUsername(username);
        return resultInfo;
    }

    public Object register(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String number = request.getParameter("number");
        String college = request.getParameter("college");
        String major = request.getParameter("major");
        String clas = request.getParameter("clas");
        //3,封装Teacher对象
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setNumber(number);
        teacher.setCollege(college);
        teacher.setMajor(major);
        teacher.setClas(clas);
        //4,封装TeacherUser对象
        TeacherUser teacherUser = new TeacherUser();
        teacherUser.setUsername(username);
        teacherUser.setPassword(password);
        //4,传入参数
        Object resultInfo = teacherService.register(teacher,teacherUser);
        return resultInfo;
    }
}
