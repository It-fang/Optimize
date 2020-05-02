package com.itfang.www.bbl.servic;

import com.itfang.www.dal.dao.*;
import com.itfang.www.dal.po.*;

import java.sql.SQLException;
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
     * @param application
     * @return resultInfo
     * @throws SQLException
     */
    @Override
    public Object apply(Application application) throws SQLException {
        if ("".equals(application.getTeacherId()) || "".equals(application.getTeacherName()) || "".equals(application.getStudentName()) || "".equals(application.getStudentNumber()) || "".equals(application.getStudentId()) || "".equals(application.getApplyTime())){
            resultInfo.setStatus(false);
            resultInfo.setMessage("请将预约信息填完整！");
            return resultInfo;
        }
        ApplicationDao applicationDao = new ApplicationDaoImpl();
        boolean status = applicationDao.saveApplication(application);
        resultInfo.setStatus(status);
        resultInfo.setMessage("预约请求发送成功,等待教师处理!");
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
            resultInfo.setMessage("没有任务预约申请!");
        }else {
            resultInfo.setStatus(true);
            resultInfo.setData(applications);
        }
        return resultInfo;
    }
}
