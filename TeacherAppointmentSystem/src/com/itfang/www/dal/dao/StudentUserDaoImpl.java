package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.Student;
import com.itfang.www.dal.po.StudentUser;
import com.itfang.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author it-fang
 * 学生用户对象访问数据库的相关操作
 */
public class StudentUserDaoImpl implements StudentUserDao{
    /**
     * 判断用户名或者学号是否存在
     * @param condition
     * @throws SQLException
     * @return status
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
                        "select count(*) as condition_count from student_user where username = ? ";
            }else if ("number".equals(key)){
                sql = "" +
                        "select count(*) as condition_count from student where number = ? ";
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
     * 将学生对象数据存入
     * @param student
     * @throws SQLException
     */
    @Override
    public void saveStudent(Student student) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "insert into student " +
                "(number,name,sex,college,major,clas)   " +
                "value (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,student.getNumber());
        preparedStatement.setString(2,student.getName());
        preparedStatement.setString(3,student.getSex());
        preparedStatement.setString(4,student.getCollege());
        preparedStatement.setString(5,student.getMajor());
        preparedStatement.setString(6,student.getClas());
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
    }

    /**
     * 根据学号获取学生对象Id
     * @param number
     * @return studentId
     * @throws SQLException
     */
    @Override
    public int getId(String number) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from student where number = ? " ;
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
     * 将学生用户对象存入数据库中
     * @param studentUser
     * @return status
     * @throws SQLException
     */
    @Override
    public boolean saveStudentUser(StudentUser studentUser) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "insert into register_application " +
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

    /**
     *检查学生用户密码是否正确
     * @param studentUser
     * @return status
     * @throws SQLException
     */
    @Override
    public boolean checkPassword(StudentUser studentUser) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from student_user where username = ? " ;
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,studentUser.getUsername());
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean status = false;
        while (resultSet.next()){
            if (resultSet.getString("password").equals(studentUser.getPassword())){
                studentUser.setStudentId(resultSet.getInt("student_id"));
                status = true;
            }else {
                status = false;
            }
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return status;
    }

    /**
     * 根据学生Id从数据库中获取学生对象信息
     * @param studentId
     * @return
     * @throws SQLException
     */
    @Override
    public Student getStudent(int studentId) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from student where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,studentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Student student = null;
        while(resultSet.next()){
            student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setNumber(resultSet.getString("number"));
            student.setName(resultSet.getString("name"));
            student.setSex(resultSet.getString("sex"));
            student.setCollege(resultSet.getString("college"));
            student.setMajor(resultSet.getString("major"));
            student.setClas(resultSet.getString("clas"));
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return student;
    }

    /**
     * 根据学生Id从数据库中删除学生对象信息
     * @param studentId
     * @return
     * @throws SQLException
     */
    @Override
    public boolean deleteStudent(int studentId) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "delete from student " +
                "where id = ?";
        PreparedStatement preparedStatement =conn.prepareStatement(sql);
        preparedStatement.setInt(1,studentId);
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
        return true;
    }

    /**
     * 获取数据库中所有的学生对象
     * @return
     * @throws SQLException
     */
    @Override
    public List<Student> listAllStudents() throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from student";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Student> students = new ArrayList<Student>();
        while (resultSet.next()){
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setNumber(resultSet.getString("number"));
            student.setName(resultSet.getString("name"));
            student.setSex(resultSet.getString("sex"));
            student.setCollege(resultSet.getString("college"));
            student.setMajor(resultSet.getString("major"));
            student.setClas(resultSet.getString("clas"));

            students.add(student);
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return students;
    }
}
