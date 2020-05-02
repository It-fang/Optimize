package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.Teacher;
import com.itfang.www.dal.po.TeacherUser;
import com.itfang.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author it-fang
 * 教师用户对象访问数据库的相关操作
 */
public class TeacherUserDaoImpl implements TeacherUserDao {

    /**
     * 判断用户名是否存在,存在返回true,不存在返回false
     * @param condition
     * @return status
     * @throws SQLException
     */
    @Override
    public boolean isExit(HashMap condition) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = null;
        Object name = null;
        for (Object key : condition.keySet()){
            name = key;
            if ("username".equals(key)){
                sql = "" +
                        "select count(*) as condition_count from teacher_user where username = ? ";
            }else if ("number".equals(key)){
                sql = "" +
                        "select count(*) as condition_count from teacher where number = ? ";
            }
        }
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, (String) condition.get(name));
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean status = false;
        while(resultSet.next()){
            if (0 == resultSet.getInt("condition_count")){
                status = false;
            }else {
                status = true;
            }
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return status;
    }

    @Override
    public void saveTeacher(Teacher teacher) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "insert into teacher " +
                "(number,name,college,major,clas)   " +
                "value (?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,teacher.getNumber());
        preparedStatement.setString(2,teacher.getName());
        preparedStatement.setString(3,teacher.getCollege());
        preparedStatement.setString(4,teacher.getMajor());
        preparedStatement.setString(5,teacher.getClas());
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
    }

    @Override
    public int getId(String number) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from teacher where number = ? " ;
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,number);
        ResultSet resultSet = preparedStatement.executeQuery();
        int studentId = 0;
        while (resultSet.next()){
            studentId = resultSet.getInt("id");
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return studentId;
    }

    @Override
    public boolean saveTeacherUser(TeacherUser teacherUser) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "insert into teacher_user " +
                "(username,password,teacher_id)   " +
                "value (?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,teacherUser.getUsername());
        preparedStatement.setString(2,teacherUser.getPassword());
        preparedStatement.setInt(3,teacherUser.getTeacherId());
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
        return true;
    }
}
