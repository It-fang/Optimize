package com.itfang.www.bbl.servic;

import com.itfang.www.dal.po.AdminUser;
import com.itfang.www.dal.po.ResultInfo;

import java.sql.SQLException;

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

    ResultInfo queryRegister() throws SQLException;
}
