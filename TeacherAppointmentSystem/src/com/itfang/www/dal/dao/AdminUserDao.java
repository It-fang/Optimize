package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.AdminUser;

import java.sql.SQLException;

/**
 * @author it-fang
 * 管理员用户对于数据库的相关操作
 */
public interface AdminUserDao {
    /**
     * 判断传入的用户名是否在数据库中
     * @param username
     * @return
     * @throws SQLException
     */
    boolean isExit(String username) throws SQLException;

    /**
     * 判断用户名对应的密码是否正确
     * @param adminUser
     * @return
     * @throws SQLException
     */
    boolean checkPassword(AdminUser adminUser) throws SQLException;
}
