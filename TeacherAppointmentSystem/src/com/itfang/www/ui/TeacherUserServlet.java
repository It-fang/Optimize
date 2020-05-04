package com.itfang.www.ui;

import com.itfang.www.bbl.servic.StudentService;
import com.itfang.www.bbl.servic.StudentServiceImpl;
import com.itfang.www.bbl.servic.TeacherService;
import com.itfang.www.bbl.servic.TeacherServiceImpl;
import com.itfang.www.dal.po.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    /**
     * 注册教师账号
     * @param request
     * @param response
     * @return
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

    /**
     * 教师用户登陆
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
        //3,封装studentUser对象
        TeacherUser teacherUser = new TeacherUser();
        teacherUser.setUsername(username);
        teacherUser.setPassword(password);
        //4,传入参数
        Object resultInfo = teacherService.login(teacherUser);
        //5,将用户存入Session域中
        HttpSession session = request.getSession();
        session.setAttribute("teacherUser",teacherUser);
        return resultInfo;
    }

    /**
     * 查询预约请求
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public Object queryApplication(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        TeacherUser teacherUser = (TeacherUser) request.getSession().getAttribute("teacherUser");
        int teacherId = teacherUser.getTeacherId();
        //3,传入参数
        ResultInfo resultInfo = teacherService.queryApplication(teacherId);
        request.setAttribute("applications",((List<Application>)resultInfo.getData()));
        return resultInfo;
    }

    /**
     * 将请求转发到agree.jsp页面
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public Object toAgree(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        String _studentId = request.getParameter("studentId");
        int studentId = Integer.parseInt(_studentId);
        //3,传入参数
        ResultInfo resultInfo = teacherService.getStudent(studentId);
        //4,将resultInfo的数据存入request域中
        request.setAttribute("student",(Student)resultInfo.getData());
        return resultInfo;
    }

    /**
     * 教师审批预约请求
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public Object agree(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String _studentId = request.getParameter("studentId");
        int studentId = Integer.parseInt(_studentId);
        String ifAgree = request.getParameter("ifAgree");
        TeacherUser teacherUser = (TeacherUser) request.getSession().getAttribute("teacherUser");
        int teacherId = teacherUser.getTeacherId();
        //3,传入参数
        Object resultInfo = teacherService.agree(studentId,teacherId,ifAgree);
        return resultInfo;
    }

    public Object deleteApplication(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String studentId = request.getParameter("studentId");
        String teacherId = request.getParameter("teacherId");
        //3,传入参数
        Object resultInfo = teacherService.deleteApplication(studentId,teacherId);
        return resultInfo;
    }
}
