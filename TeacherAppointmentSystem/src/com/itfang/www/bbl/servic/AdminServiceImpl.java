package com.itfang.www.bbl.servic;

import com.itfang.www.dal.dao.*;
import com.itfang.www.dal.po.AdminUser;
import com.itfang.www.dal.po.ResultInfo;
import com.itfang.www.dal.po.StudentUser;

import java.sql.SQLException;
import java.util.List;


/**
 * @author it-fang
 * @Description 学生用户的所有相关操作
 * @date 2020-4-27
 */
public class AdminServiceImpl implements AdminService {
    AdminUserDao adminUserDao = new AdminUserDaoImpl();
    RegisterApplicationDao registerApplicationDao = new RegisterApplicationDaoImpl();
    ResultInfo resultInfo = new ResultInfo();

    /**
     * 登陆管理员账号
     * @param adminUser
     * @return
     * @throws SQLException
     */
    @Override
    public Object login(AdminUser adminUser) throws SQLException {
        if ("".equals(adminUser.getUsername())){
            resultInfo.setStatus(false);
            resultInfo.setMessage("用户名不能为空！");
            return resultInfo;
        }
        if ("".equals(adminUser.getPassword())){
            resultInfo.setStatus(false);
            resultInfo.setMessage("密码不能为空！");
            return resultInfo;
        }
        boolean status = adminUserDao.isExit(adminUser.getUsername());
        if (status){
            status = adminUserDao.checkPassword(adminUser);
            if (status){
                resultInfo.setStatus(true);
                resultInfo.setMessage("欢迎使用," + adminUser.getUsername());
            }else {
                resultInfo.setStatus(false);
                resultInfo.setMessage("密码不正确！");
            }
        }else {
            resultInfo.setStatus(false);
            resultInfo.setMessage("用户不存在！");
        }
        return resultInfo;
    }

    /**
     * 查询所有注册申请
     * @return
     * @throws SQLException
     */
    @Override
    public ResultInfo queryRegister() throws SQLException {
        List<StudentUser> studentUsers = registerApplicationDao.listRegisterApplication();
        if (studentUsers == null){
            resultInfo.setStatus(false);
            resultInfo.setMessage("没有任何注册申请!");
        }else {
            resultInfo.setStatus(true);
            resultInfo.setData(studentUsers);
        }
        return resultInfo;
    }

    /**
     * 同意注册申请
     * @param studentUser
     * @return
     * @throws SQLException
     */
    @Override
    public Object agreeRegister(StudentUser studentUser) throws SQLException {
        boolean status = registerApplicationDao.saveStudentUser(studentUser);
        if (status){
            status = registerApplicationDao.deleteRegisterApplication(studentUser.getStudentId());
            if (status){
                resultInfo.setStatus(true);
                resultInfo.setMessage("成功同意该用户的注册申请");
            }else {
                resultInfo.setStatus(false);
                resultInfo.setMessage("同意失败");
            }
        }else {
            resultInfo.setStatus(false);
            resultInfo.setMessage("同意失败");
        }
        return resultInfo;
    }

    @Override
    public Object refuseRegister(int studentId) throws SQLException {
        boolean status = registerApplicationDao.deleteRegisterApplication(studentId);
        if (status){
            StudentUserDao studentUserDao = new StudentUserDaoImpl();
            status = studentUserDao.deleteStudent(studentId);
            if (status){
                resultInfo.setStatus(true);
                resultInfo.setMessage("拒绝成功!");
            }else {
                resultInfo.setStatus(false);
                resultInfo.setMessage("拒绝失败!");
            }
        }else {
            resultInfo.setStatus(false);
            resultInfo.setMessage("拒绝失败!");
        }
        return resultInfo;
    }
}
