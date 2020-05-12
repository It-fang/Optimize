package com.itfang.www.bbl.servic;

import com.itfang.www.dal.po.AdminUser;
import com.itfang.www.dal.po.ResultInfo;
import com.itfang.www.dal.po.StudentUser;

import java.sql.SQLException;
import java.text.ParseException;

/**
 * @author it-fang
 * @Description 超级管理员用户的所有相关操作
 * @date 2020-4-27
 */
public interface AdminService {
    /**
     * 登陆管理员账号
     * @param adminUser
     * @return
     * @throws SQLException
     */
    Object login(AdminUser adminUser) throws SQLException;

    /**
     * 查询所有注册申请
     * @return
     * @throws SQLException
     */
    ResultInfo queryRegister() throws SQLException;

    /**
     * 同意注册申请
     * @param studentUser
     * @return
     * @throws SQLException
     */
    Object agreeRegister(StudentUser studentUser) throws SQLException;

    /**
     * 拒绝注册申请
     * @param studentId
     * @return
     * @throws SQLException
     */
    Object refuseRegister(int studentId) throws SQLException;

    /**
     * 将所有学生和教师对象插入到公告表中去
     * @param message
     * @return
     * @throws SQLException
     * @throws ParseException
     */
    Object sendNotification(String message) throws SQLException, ParseException;
}
