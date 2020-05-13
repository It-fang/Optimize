package com.itfang.www.bbl.servic;

import com.itfang.www.dal.dao.*;
import com.itfang.www.dal.po.*;
import org.apache.commons.fileupload.FileItem;

import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author it-fang
 * @Description 学生用户的所有相关操作
 * @date 2020-4-27
 */
public class StudentServiceImpl implements StudentService {
    ResultInfo resultInfo = new ResultInfo();
    StudentUserDao studentUserDao = new StudentUserDaoImpl();

    /**
     * 检查用户名是否存在
     * @param username
     * @throws SQLException
     * @return resultInfo
     */
    @Override
    public Object checkUsername(String username) throws SQLException {
        //判断提交的用户名是否为空
        if ("".equals(username)){
            resultInfo.setStatus(true);
            resultInfo.setMessage("用户名不能为空");
            return resultInfo;
        }
        HashMap condition = new HashMap(1);
        condition.put("username",username);
        boolean status = studentUserDao.isExit(condition);
        if (status){
            resultInfo.setStatus(true);
            resultInfo.setMessage("用户名已经存在");
        }else {
            resultInfo.setStatus(false);
            resultInfo.setMessage("用户名可以使用");
        }
        return resultInfo;
    }

    /**
     * 注册学生账号,并将数据存入
     * @param student
     * @param studentUser
     * @return resultInfo
     * @throws SQLException
     */
    @Override
    public Object register(Student student, StudentUser studentUser) throws SQLException {
        if ("".equals(studentUser.getUsername()) || "".equals(studentUser.getPassword()) || "".equals(student.getName()) || "".equals(student.getSex()) || "".equals(student.getNumber()) || "".equals(student.getCollege()) || "".equals(student.getMajor()) || "".equals(student.getClas())){
            resultInfo.setStatus(false);
            resultInfo.setMessage("请将注册信息填完整！");
            return resultInfo;
        }
        if (studentUser.getUsername() == student.getNumber()){
            resultInfo.setStatus(false);
            resultInfo.setMessage("用户名和学号不能相等！");
            return resultInfo;
        }
        HashMap conditionNumber = new HashMap(1);
        conditionNumber.put("number",student.getNumber());
        boolean status = studentUserDao.isExit(conditionNumber);
        if (status){
            resultInfo.setStatus(false);
            resultInfo.setMessage("该学号已经被注册！");
            return resultInfo;
        }
        HashMap conditionUsername = new HashMap(1);
        conditionUsername.put("username",studentUser.getUsername());
        status = studentUserDao.isExit(conditionUsername);
        if (status){
            resultInfo.setStatus(false);
            resultInfo.setMessage("该用户名已经被注册！");
            return resultInfo;
        }
        //将学生对象放入数据库中并获取其ID
        studentUserDao.saveStudent(student);
        int studentId = studentUserDao.getId(student.getNumber());
        studentUser.setStudentId(studentId);
        status = studentUserDao.saveStudentUser(studentUser);
        resultInfo.setStatus(status);
        resultInfo.setMessage("注册成功，是否跳转到登陆页面？");
        return resultInfo;
    }

    /**
     * 登陆学生账号
     * @param studentUser
     * @return resultInfo
     * @throws SQLException
     */
    @Override
    public Object login(StudentUser studentUser) throws SQLException {
        if ("".equals(studentUser.getUsername())){
            resultInfo.setStatus(false);
            resultInfo.setMessage("用户名不能为空!");
            return resultInfo;
        }else if ("".equals(studentUser.getPassword())){
            resultInfo.setStatus(false);
            resultInfo.setMessage("密码不能为空!");
            return resultInfo;
        }
        HashMap conditionUsername = new HashMap(1);
        conditionUsername.put("username",studentUser.getUsername());
        boolean status = studentUserDao.isExit(conditionUsername);
        if (status){
            status = studentUserDao.checkPassword(studentUser);
            if (status){
                resultInfo.setStatus(true);
                resultInfo.setMessage("欢迎使用," + studentUser.getUsername());
            }else {
                resultInfo.setStatus(false);
                resultInfo.setMessage("密码错误，请重新输入!");
            }
        }else {
            resultInfo.setStatus(false);
            resultInfo.setMessage("该用户不存在!");
        }
        return resultInfo;
    }

    /**
     * 查询所有老师信息
     * @param _currentPage
     * @param _rows
     * @param condition
     * @return page
     * @throws SQLException
     */
    @Override
    public Page<Teacher> queryTeacher(String _currentPage, String _rows, Map<String, String[]> condition) throws SQLException {
        //1,创建Page对象
        Page<Teacher> page = new Page<>();
        //2,设置参数
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        if(currentPage <= 0){
            currentPage = 1;
        }
        //3,调用Dao查询totalCount
        PageDao pageDao = new PageDaoImpl();
        int totalCount = pageDao.findTotalCount(condition);
        page.setTotalCount(totalCount);
        //4,计算总页码
        int totalPage = totalCount % rows == 0 ? totalCount/rows : totalCount/rows + 1;
        if (currentPage >= totalPage){
            currentPage = totalPage;
        }
        page.setCurrentPage(currentPage);
        page.setRows(rows);
        page.setTotalPage(totalPage);
        //5,调用Dao查询List集合
        int start = (currentPage - 1)*rows;
        List<Teacher> list =  pageDao.listTeacherByPage(start,rows,condition);
        page.setList(list);
        return page;
    }

    /**
     * 判断预约请求信息是否完整,并存入数据库中
     * @param fileItems
     * @param realPath
     * @param studentUser
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws ParseException
     */
    @Override
    public Object apply(List<FileItem> fileItems,String realPath,StudentUser studentUser) throws SQLException, IOException, ParseException {
        //存放普通字段的参数
        Map<String,String> params = new HashMap<String, String>(10);
        //存到服务器的路径
        String filePath = null;
        //存到服务器中的图片名
        String fileName = null;
        File file = new File(realPath);
        //判断存储位置的目录是否存在,不存在则创建
        if(!file.exists()&&!file.isDirectory()){
            file.mkdir();
        }
        //完成文件上传内容
        for (FileItem fileItem : fileItems){
            //如果是表单的普通字段,则存入params集合中
            if (fileItem.isFormField()){
                String key = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8");
                if (value == null || "".equals(value)){
                    resultInfo.setMessage("请将预约信息填写完整!");
                    resultInfo.setStatus(false);
                    return resultInfo;
                }
                params.put(key,value);
            }else{
                fileName = fileItem.getName();
                if (fileName == null || "".equals(fileName)){
                    resultInfo.setMessage("请将预约信息填写完整!");
                    resultInfo.setStatus(false);
                    return resultInfo;
                }
                if (fileName.contains(":")){
                    fileName = fileName.substring(fileName.indexOf("\\"+1));
                }
                if (fileName == null || "".equals(fileName.trim())){
                    continue;
                }
                //拼接保存到服务器的路径,存放路径+文件名
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                String ms = df.format(new Date());
                fileName = ms + fileName;
                filePath = realPath + "\\" + fileName;
                //构建输入输出流
                InputStream in = fileItem.getInputStream();
                OutputStream out = new FileOutputStream(filePath);
                byte[] b = new byte[10*1024*1024];
                int len = -1;
                while ((len = in.read(b)) != -1){
                    out.write(b,0,len);
                }
                out.close();
                in.close();
                fileItem.delete();
            }
        }

        //封装application对象
        Application application = new Application();
        application.setTeacherId(Integer.parseInt(params.get("teacherId")));
        application.setTeacherName(params.get("teacherName"));
        application.setStudentId(studentUser.getStudentId());
        application.setStudentName(params.get("name"));
        application.setStudentNumber(params.get("number"));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        application.setApplyTime(df.parse(params.get("time")));

        //调用Dao层向数据库存放申请表数据
        ApplicationDao applicationDao = new ApplicationDaoImpl();
        boolean status = applicationDao.saveApplication(application);
        if (status != true){
            resultInfo.setStatus(false);
            resultInfo.setMessage("提交失败");
            return resultInfo;
        }

        //封装Upload对象
        Upload upload = new Upload();
        upload.setFileName(fileName);
        upload.setStudentId(studentUser.getStudentId());

        //调用Dao层向数据库存放文件存储信息
        UploadDao uploadDao = new UploadDaoImpl();
        status = uploadDao.saveUpload(upload);
        if (status != true){
            resultInfo.setStatus(false);
            resultInfo.setMessage("提交失败");
            return resultInfo;
        }
        resultInfo.setStatus(true);
        resultInfo.setMessage("提交成功,请等待教师处理...");
        return resultInfo;
    }

    /**
     * 查询预约结果
     * @param studentId
     * @return resultInfo
     * @throws SQLException
     */
    @Override
    public Object queryResult(int studentId) throws SQLException {
        ApplicationDao applicationDao = new ApplicationDaoImpl();
        List<Application> applications = applicationDao.listResult(studentId);
        if (applications == null){
            resultInfo.setStatus(false);
            resultInfo.setMessage("没有任何预约申请!");
        }else {
            resultInfo.setStatus(true);
            resultInfo.setData(applications);
        }
        return resultInfo;
    }

    /**
     * 判断用户输入的验证码是否正确
     * @param code
     * @param realCode
     * @return
     */
    @Override
    public Object checkCode(String code, String realCode) {
        if (code == null || "".equals(code)){
            resultInfo.setStatus(false);
            resultInfo.setMessage("请输入验证码");
            return resultInfo;
        }
        if (!realCode.equalsIgnoreCase(code)){
            resultInfo.setStatus(false);
            resultInfo.setMessage("验证码错误");
            return resultInfo;
        }
        resultInfo.setStatus(true);
        return resultInfo;
    }

    /**
     * 获取公告表该用户未读的信息
     * @param studentUser
     * @return
     * @throws SQLException
     */
    @Override
    public Object queryInformationAuto(StudentUser studentUser) throws SQLException {
        //获取该用户未读的消息
        NotificationDao notificationDao = new NotificationDaoImpl();
        Notification notification = notificationDao.getStudentInformation(studentUser.getStudentId(),0);
        if (notification.getMessage() == null || "".equals(notification.getMessage())){
            resultInfo.setStatus(false);
            return resultInfo;
        }
        //将未读的消息设置成已读
        notificationDao.setStudentAlready(studentUser.getStudentId());
        resultInfo.setStatus(true);
        resultInfo.setMessage("                           公告" + "\r" + "内容:      " + notification.getMessage() + "\r" + "日期:      " + notification.getDate());
        return resultInfo;
    }

    /**
     * 获取公告表该用户已读的信息
     * @param studentUser
     * @return
     * @throws SQLException
     */
    @Override
    public Object queryInformation(StudentUser studentUser) throws SQLException {
        //获得该用户已读的消息
        NotificationDao notificationDao = new NotificationDaoImpl();
        Notification notification = notificationDao.getStudentInformation(studentUser.getStudentId(),1);
        if (notification.getMessage() == null || "".equals(notification.getMessage())){
            resultInfo.setStatus(false);
            resultInfo.setMessage("当前没有任何公告");
            return resultInfo;
        }
        resultInfo.setStatus(true);
        resultInfo.setMessage("                           公告" + "\r" + "内容:      " + notification.getMessage() + "\r" + "日期:      " + notification.getDate());
        return resultInfo;
    }
}
