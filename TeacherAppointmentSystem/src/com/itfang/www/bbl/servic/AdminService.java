package com.itfang.www.bbl.servic;

import com.itfang.www.dal.po.AdminUser;

import java.sql.SQLException;

/**
 * @author it-fang
 * @Description 超级管理员用户的所有相关操作
 * @date 2020-4-27
 */
public interface AdminService {
    Object login(AdminUser adminUser) throws SQLException;
}
