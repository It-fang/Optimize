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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**
     * 删除预约请求
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
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

    /**
     * 将teacher对象存入request域中并跳转到修改自身信息页面
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public Object toModify(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        TeacherUser teacherUser = (TeacherUser) request.getSession().getAttribute("teacherUser");
        //3,传入参数
        ResultInfo resultInfo = teacherService.getTeacher(teacherUser.getTeacherId());
        //4,将teacher存入request域中
        request.setAttribute("teacher",(Teacher)resultInfo.getData());
        return resultInfo;
    }

    /**
     * 修改教师用户自身信息
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws ParseException
     */
    public Object modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String name = request.getParameter("name");
        String number = request.getParameter("number");
        String college = request.getParameter("college");
        String major = request.getParameter("major");
        String clas = request.getParameter("clas");
        String _freeTime = request.getParameter("time");
        Date freeTime = null;
        if (!(_freeTime == null || "".equals(_freeTime))){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            freeTime = simpleDateFormat.parse(_freeTime);
        }
        TeacherUser teacherUser = (TeacherUser) request.getSession().getAttribute("teacherUser");
        int teacherId = teacherUser.getTeacherId();
        //3,封装teacher对象
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setNumber(number);
        teacher.setCollege(college);
        teacher.setMajor(major);
        teacher.setClas(clas);
        teacher.setFreeTime(freeTime);
        //4,传入参数
        Object resultInfo = teacherService.updateTeacher(teacher,teacherId);
        return resultInfo;
    }

    /**
     * 教师同意所选预约请求
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws ParseException
     */
    public Object agreeSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException{
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String[] studentIds = request.getParameterValues("studentIds");
        TeacherUser teacherUser = (TeacherUser) request.getSession().getAttribute("teacherUser");
        int teacherId = teacherUser.getTeacherId();
        //3,传入参数
        Object resultInfo = teacherService.agreeSelect(teacherId,studentIds);
        return resultInfo;
    }

    /**
     * 教师用户进入聊天室进行聊天操作
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public Object teacherEnterChatRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        TeacherUser teacherUser = (TeacherUser) request.getSession().getAttribute("teacherUser");
        String username = teacherUser.getUsername();
        //3,将username存入Session中
        request.getSession().setAttribute("username",username);
        return null;
    }
}
