package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.AdminUser;

import java.sql.SQLException;

/**
 * @author it-fang
 * 管理员用户对于数据库的相关操作
 */
public interface AdminUserDao {

    boolean isExit(String username) throws SQLException;

    boolean checkPassword(AdminUser adminUser) throws SQLException;
}
