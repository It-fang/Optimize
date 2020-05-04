package com.itfang.www.bbl.servic;

import com.itfang.www.dal.dao.*;
import com.itfang.www.dal.po.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * @author it-fang
 * @Description 教师用户的所有相关操作
 * @date 2020-4-27
 */
public class TeacherServiceImpl implements TeacherService {
    ResultInfo resultInfo = new ResultInfo();
    TeacherUserDao teacherUserDao = new TeacherUserDaoImpl();

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     * @throws SQLException
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
        boolean status = teacherUserDao.isExit(condition);
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
     * 注册教师账号
     * @param teacher
     * @param teacherUser
     * @return
     * @throws SQLException
     */
    @Override
    public Object register(Teacher teacher, TeacherUser teacherUser) throws SQLException {
        if ("".equals(teacherUser.getUsername()) || "".equals(teacherUser.getPassword()) || "".equals(teacher.getName()) || "".equals(teacher.getCollege()) || "".equals(teacher.getMajor()) || "".equals(teacher.getClas())){
            resultInfo.setStatus(false);
            resultInfo.setMessage("请将注册信息填完整！");
            return resultInfo;
        }
        HashMap conditionNumber = new HashMap(1);
        conditionNumber.put("number",teacher.getNumber());
        boolean status = teacherUserDao.isExit(conditionNumber);
        if (status){
            resultInfo.setStatus(false);
            resultInfo.setMessage("该工号已经被注册！");
            return resultInfo;
        }
        HashMap conditionUsername = new HashMap(1);
        conditionUsername.put("username",teacherUser.getUsername());
        status = teacherUserDao.isExit(conditionUsername);
        if (status){
            resultInfo.setStatus(false);
            resultInfo.setMessage("该用户名已经被注册！");
            return resultInfo;
        }
        //将教师对象放入数据库中并获取其ID
        teacherUserDao.saveTeacher(teacher);
        int teacherId = teacherUserDao.getId(teacher.getNumber());
        teacherUser.setTeacherId(teacherId);
        status = teacherUserDao.saveTeacherUser(teacherUser);
        resultInfo.setStatus(status);
        resultInfo.setMessage("注册成功，是否跳转到登陆页面？");
        return resultInfo;
    }

    /**
     * 教师用户登陆
     * @param teacherUser
     * @return
     * @throws SQLException
     */
    @Override
    public Object login(TeacherUser teacherUser) throws SQLException {
        if ("".equals(teacherUser.getUsername())){
            resultInfo.setStatus(false);
            resultInfo.setMessage("用户名不能为空!");
            return resultInfo;
        }else if ("".equals(teacherUser.getPassword())){
            resultInfo.setStatus(false);
            resultInfo.setMessage("密码不能为空!");
            return resultInfo;
        }
        HashMap conditionUsername = new HashMap(1);
        conditionUsername.put("username",teacherUser.getUsername());
        boolean status = teacherUserDao.isExit(conditionUsername);
        if (status){
            status = teacherUserDao.checkPassword(teacherUser);
            if (status){
                resultInfo.setStatus(true);
                resultInfo.setMessage("欢迎使用," + teacherUser.getUsername());
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
     * 查询预约请求
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public ResultInfo queryApplication(int teacherId) throws SQLException {
        ApplicationDao applicationDao = new ApplicationDaoImpl();
        List<Application> applications = applicationDao.listApplication(teacherId);
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
     * 根据学生Id获取学生对象信息
     * @param studentId
     * @return
     * @throws SQLException
     */
    @Override
    public ResultInfo getStudent(int studentId) throws SQLException {
        StudentUserDao studentUserDao = new StudentUserDaoImpl();
        Student student = studentUserDao.getStudent(studentId);
        resultInfo.setData(student);
        return resultInfo;
    }

    /**
     * 教师审批预约请求
     * @param studentId
     * @param teacherId
     * @param ifAgree
     * @return
     * @throws SQLException
     */
    @Override
    public Object agree(int studentId, int teacherId, String ifAgree) throws SQLException {
        if ("".equals(ifAgree) || ifAgree == null){
            resultInfo.setStatus(false);
            resultInfo.setMessage("请填写处理结果！");
            return resultInfo;
        }
        ApplicationDao applicationDao = new ApplicationDaoImpl();
        boolean status = applicationDao.saveAgree(studentId,teacherId,ifAgree);
        if (status){
            resultInfo.setStatus(true);
            resultInfo.setMessage("提交成功");
        }else {
            resultInfo.setStatus(false);
            resultInfo.setMessage("提交失败");
        }
        return resultInfo;
    }

    @Override
    public Object deleteApplication(String _studentId, String _teacherId) throws SQLException {
        if ("".equals(_studentId) || "".equals(_teacherId)){
            resultInfo.setStatus(false);
            resultInfo.setMessage("删除失败");
            return resultInfo;
        }
        int studentId = Integer.parseInt(_studentId);
        int teacherId = Integer.parseInt(_teacherId);
        ApplicationDao applicationDao = new ApplicationDaoImpl();
        boolean status = applicationDao.deleteApplication(studentId,teacherId);
        if (status){
            resultInfo.setStatus(true);
            resultInfo.setMessage("删除成功");
        }else {
            resultInfo.setStatus(false);
            resultInfo.setMessage("删除失败");
        }
        return resultInfo;
    }
}
