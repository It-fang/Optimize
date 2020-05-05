package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.AdminUser;
import com.itfang.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author it-fang
 * 管理员用户对于数据库的相关操作
 */
public class AdminUserDaoImpl implements AdminUserDao {

    /**
     * 判断传入的用户名是否在数据库中
     * @param username
     * @return
     * @throws SQLException
     */
    @Override
    public boolean isExit(String username) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select count(*) as username_count from admin_user where username = ? ";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean status = false;
        while(resultSet.next()){
            if (0 == resultSet.getInt("username_count")){
                status = false;
            }else {
                status = true;
            }
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return status;
    }

    /**
     * 判断用户名对应的密码是否正确
     * @param adminUser
     * @return
     * @throws SQLException
     */
    @Override
    public boolean checkPassword(AdminUser adminUser) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from admin_user where username = ? " ;
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,adminUser.getUsername());
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean status = false;
        while (resultSet.next()){
            if (resultSet.getString("password").equals(adminUser.getPassword())){
                adminUser.setId(resultSet.getInt("id"));
                status = true;
            }else {
                status = false;
            }
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return status;
    }
}
