package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.Teacher;
import com.itfang.www.dal.po.TeacherUser;
import com.itfang.www.util.JdbcUtil;

import java.sql.*;
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

    /**
     * 将教师对象存入数据库中
     * @param teacher
     * @throws SQLException
     */
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

    /**
     * 通过教师工号获得教师Id
     * @param number
     * @return
     * @throws SQLException
     */
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

    /**
     * 将教师用户对象存入数据库中
     * @param teacherUser
     * @return
     * @throws SQLException
     */
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

    /**
     * 检查密码是否正确
     * @param teacherUser
     * @return
     * @throws SQLException
     */
    @Override
    public boolean checkPassword(TeacherUser teacherUser) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from teacher_user where username = ? " ;
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,teacherUser.getUsername());
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean status = false;
        while (resultSet.next()){
            if (resultSet.getString("password").equals(teacherUser.getPassword())){
                teacherUser.setTeacherId(resultSet.getInt("teacher_id"));
                status = true;
            }else {
                status = false;
            }
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return status;
    }

    /**
     * 根据教师Id从数据库中获取教师对象
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public Teacher getTeacher(int teacherId) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from teacher where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,teacherId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Teacher teacher = null;
        while(resultSet.next()){
            teacher = new Teacher();
            teacher.setId(resultSet.getInt("id"));
            teacher.setName(resultSet.getString("name"));
            teacher.setNumber(resultSet.getString("number"));
            teacher.setCollege(resultSet.getString("college"));
            teacher.setMajor(resultSet.getString("major"));
            teacher.setClas(resultSet.getString("clas"));
            teacher.setFreeTime(resultSet.getDate("free_time"));
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return teacher;

    }

    /**
     * 根据教师Id，在数据库中更新教师用户信息
     * @param teacher
     * @param teacherId
     * @return
     * @throws SQLException
     */
    @Override
    public boolean updateTeacher(Teacher teacher, int teacherId) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "update teacher " +
                "set " +
                "name=?,number=?,college=?,major=?,clas=?,free_time=?" +
                "where id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,teacher.getName());
        preparedStatement.setString(2,teacher.getNumber());
        preparedStatement.setString(3,teacher.getCollege());
        preparedStatement.setString(4,teacher.getMajor());
        preparedStatement.setString(5,teacher.getClas());
        if (teacher.getFreeTime() == null){
            preparedStatement.setDate(6,Date.valueOf("1900-01-02"));
        }else {
            preparedStatement.setDate(6,new Date(teacher.getFreeTime().getTime()));
        }
        preparedStatement.setInt(7,teacherId);
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
        return true;
    }
}
