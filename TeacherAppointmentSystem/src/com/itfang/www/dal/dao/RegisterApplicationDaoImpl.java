package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.StudentUser;
import com.itfang.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author it-fang
 * 对注册申请表的相关操作
 */
public class RegisterApplicationDaoImpl implements RegisterApplicationDao {
    @Override
    public List<StudentUser> listRegisterApplication() throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from register_application ";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<StudentUser> studentUsers = new ArrayList<StudentUser>();
        StudentUser studentUser = null;
        while(resultSet.next()){
            studentUser = new StudentUser();
            studentUser.setId(resultSet.getInt("id"));
            studentUser.setUsername(resultSet.getString("username"));
            studentUser.setPassword(resultSet.getString("password"));
            studentUser.setStudentId(resultSet.getInt("student_id"));
            studentUsers.add(studentUser);
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return studentUsers;
    }
}
