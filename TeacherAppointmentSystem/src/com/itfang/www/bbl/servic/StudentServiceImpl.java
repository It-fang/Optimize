package com.itfang.www.bbl.servic;

import com.itfang.www.dal.dao.StudentUserDao;
import com.itfang.www.dal.dao.StudentUserDaoImpl;
import com.itfang.www.dal.po.ResultInfo;
import com.itfang.www.dal.po.Student;
import com.itfang.www.dal.po.StudentUser;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        HashMap<String,String > condition = new HashMap(1);
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
        HashMap condition_number = new HashMap(1);
        condition_number.put("number",student.getNumber());
        boolean status = studentUserDao.isExit(condition_number);
        if (status){
            resultInfo.setStatus(false);
            resultInfo.setMessage("该学号已经被注册！");
            return resultInfo;
        }
        HashMap condition_username = new HashMap(1);
        condition_username.put("username",studentUser.getUsername());
        status = studentUserDao.isExit(condition_username);
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
}
