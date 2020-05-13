package com.itfang.www.ui;

import com.google.gson.Gson;
import com.itfang.www.bbl.servic.StudentService;
import com.itfang.www.bbl.servic.StudentServiceImpl;
import com.itfang.www.dal.po.*;
import com.itfang.www.util.VerifyCodeUntil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/StudentUser/*")
/**
 * @author it-fang
 * 接收学生用户相关操作请求
 */
public class StudentUserServlet extends BaseServlet {
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
        String code = request.getParameter("code");
        String realCode = (String) request.getSession().getAttribute("realCode");
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
        ResultInfo resultInfo = (ResultInfo)studentService.checkCode(code,realCode);
        if (resultInfo.isStatus()){
            resultInfo = (ResultInfo)studentService.register(student,studentUser);
        }
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

    /**
     * 跳转到申请表页面
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public Object toApply(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String teacherId = request.getParameter("id");
        String teacherName = request.getParameter("name");
        //3,将参数存入session域中
        request.getSession().setAttribute("teacherId",teacherId);
        request.getSession().setAttribute("teacherName",teacherName);
        return null;
    }

    /**
     * 接收预约请求并上传图片
     * @param request
     * @param response
     * @return resultInfo
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws ParseException
     */
    public Object apply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException, FileUploadException {
        //1,获取项目部署的真实路径
        String realPath = this.getServletContext().getRealPath("upload");
        StudentUser studentUser = (StudentUser) request.getSession().getAttribute("studentUser");
        //1,创建DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2,创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //3,解决上传文件名乱码问题
        upload.setHeaderEncoding("UTF-8");
        //4,判断enctype是否为Multipart类型的表单数据
        if (!ServletFileUpload.isMultipartContent(request)){
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setStatus(false);
            resultInfo.setMessage("提交失败！");
            return resultInfo;
        }
        //5,获取表单输入项集合List<fileItem>
        List<FileItem> fileItems = upload.parseRequest(request);
        //6,将表单输入项集合,项目部署的真实路径,学生用户对象传入到服务层
        Object resultInfo = studentService.apply(fileItems,realPath,studentUser);
        return resultInfo;
    }

    /**
     * 查询预约结果
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws ParseException
     */
    public Object queryResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException{
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        StudentUser studentUser = (StudentUser) request.getSession().getAttribute("studentUser");
        int studentId = studentUser.getStudentId();
        //3,传入参数
        ResultInfo resultInfo = (ResultInfo) studentService.queryResult(studentId);
        request.getSession().setAttribute("applications",(List<Application>)resultInfo.getData());
        return resultInfo;
    }

    /**
     * 学生用户进入聊天室进行聊天操作
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws ParseException
     */
    public Object studentEnterChatRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException{
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        StudentUser studentUser = (StudentUser) request.getSession().getAttribute("studentUser");
        String username = studentUser.getUsername();
        //3,将username存入Session中
        request.getSession().setAttribute("username",username);
        return null;
    }

    /**
     * 生成验证码图片
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public Object createVerifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //调用验证码工具生成验证码
        VerifyCodeUntil until = new VerifyCodeUntil();
        BufferedImage image = until.getImage();
        //获取验证码中的字符串并存入session域中
        request.getSession().setAttribute("realCode",until.getText());
        //将图片发送到页面上
        ImageIO.write(image,"jpg",response.getOutputStream());
        return null;
    }

    /**
     * 获取公告表该用户未读的信息
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public Object queryInformationAuto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        StudentUser studentUser = (StudentUser) request.getSession().getAttribute("studentUser");
        //3,传入参数
        Object resultInfo = studentService.queryInformationAuto(studentUser);
        return resultInfo;
    }

    /**
     * 获取公告表该用户已读的信息
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public Object queryInformation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        StudentUser studentUser = (StudentUser) request.getSession().getAttribute("studentUser");
        //3,传入参数
        Object resultInfo = studentService.queryInformation(studentUser);
        return resultInfo;
    }
}
