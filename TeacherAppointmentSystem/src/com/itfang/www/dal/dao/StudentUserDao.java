package com.itfang.www.dal.dao;

import java.sql.SQLException;

/**
 * @author it-fang
 * 查询学生用户对象的相关操作
 */
public interface StudentUserDao {
    /**
     * 判断用户名是否存在
     * @param username
     * @throws SQLException
     * @return status
     */
    boolean isExit(String username) throws SQLException;
}
