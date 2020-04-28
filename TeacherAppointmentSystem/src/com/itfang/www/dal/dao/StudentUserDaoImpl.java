package com.itfang.www.dal.dao;

import com.itfang.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author it-fang
 * 查询学生用户对象的相关操作
 */
public class StudentUserDaoImpl implements StudentUserDao{
    /**
     * 判断用户名是否存在
     * @param username
     * @throws SQLException
     * @return status
     */
    @Override
    public boolean isExit(String username) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select count(*) as username_count from student_user where username = ? ";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean status = false;
        while(resultSet.next()){
            if (0 == resultSet.getInt("username_count")){
                status = false;
            }else {
                status = true;
            }
        }
        return status;
    }

}
