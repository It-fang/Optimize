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
    /**
     * 从数据库中获取注册申请表的所有数据
     * @return
     * @throws SQLException
     */
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

    @Override
    public boolean saveStudentUser(StudentUser studentUser) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "insert into student_user " +
                "(username,password,student_id)   " +
                "value (?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,studentUser.getUsername());
        preparedStatement.setString(2,studentUser.getPassword());
        preparedStatement.setInt(3,studentUser.getStudentId());
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
        return true;
    }

    @Override
    public boolean deleteRegisterApplication(StudentUser studentUser) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "delete from register_application " +
                "where student_id = ?";
        PreparedStatement preparedStatement =conn.prepareStatement(sql);
        preparedStatement.setInt(1,studentUser.getStudentId());
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
        return true;
    }
}
