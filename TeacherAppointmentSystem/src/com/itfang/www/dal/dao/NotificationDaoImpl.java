package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.Notification;
import com.itfang.www.dal.po.Student;
import com.itfang.www.dal.po.Teacher;
import com.itfang.www.util.JdbcUtil;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author it-fang
 * 公告类对象对数据库的相关操作
 */
public class NotificationDaoImpl implements NotificationDao{

    /**
     * 向公告表中插入教师id和公告目前的状态
     * @param teacher
     * @param message
     * @return
     * @throws SQLException
     * @throws ParseException
     */
    @Override
    public boolean insertTeacherId(Teacher teacher,String message) throws SQLException, ParseException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "insert into notification " +
                "(message,teacher_id,status,date) " +
                "value(?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,message);
        preparedStatement.setInt(2,teacher.getId());
        preparedStatement.setInt(3,0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String _date = format.format(new java.util.Date());
        java.util.Date date = format.parse(_date);
        preparedStatement.setDate(4,new Date(date.getTime()));
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
        return true;
    }

    /**
     * 向公告表中插入学生id和公告目前的状态
     * @param student
     * @param message
     * @return
     * @throws SQLException
     * @throws ParseException
     */
    @Override
    public boolean insertStudentId(Student student, String message) throws SQLException, ParseException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "insert into notification " +
                "(message,student_id,status,date) " +
                "value(?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,message);
        preparedStatement.setInt(2,student.getId());
        preparedStatement.setInt(3,0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String _date = format.format(new java.util.Date());
        java.util.Date date = format.parse(_date);
        preparedStatement.setDate(4,new Date(date.getTime()));
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
        return true;
    }

    /**
     * 根据学生Id和消息状态从数据库中获得公告对象
     * @param studentId
     * @param status
     * @return
     * @throws SQLException
     */
    @Override
    public Notification getStudentInformation(int studentId,int status) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from notification where student_id = ? and status = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,studentId);
        preparedStatement.setInt(2,status);
        ResultSet resultSet = preparedStatement.executeQuery();
        Notification notification = new Notification();
        while (resultSet.next()){
            notification.setId(resultSet.getInt("id"));
            notification.setMessage(resultSet.getString("message"));
            notification.setStudentId(resultSet.getInt("student_id"));
            notification.setTeacherId(resultSet.getInt("teacher_id"));
            notification.setStatus(resultSet.getInt("status"));
            notification.setDate(resultSet.getDate("date"));
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return notification;
    }

    /**
     * 根据学生Id将公告表的数据设置成已读状态
     * @param studentId
     * @throws SQLException
     */
    @Override
    public void setStudentAlready(int studentId) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "update notification " +
                "set status = 1 " +
                "where student_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,studentId);
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
    }

    /**
     * 根据教师Id和消息状态从数据库中获得公告对象
     * @param teacherId
     * @param status
     * @return
     * @throws SQLException
     */
    @Override
    public Notification getTeacherInformation(int teacherId, int status) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from notification where teacher_id = ? and status = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,teacherId);
        preparedStatement.setInt(2,status);
        ResultSet resultSet = preparedStatement.executeQuery();
        Notification notification = new Notification();
        while (resultSet.next()){
            notification.setId(resultSet.getInt("id"));
            notification.setMessage(resultSet.getString("message"));
            notification.setStudentId(resultSet.getInt("student_id"));
            notification.setTeacherId(resultSet.getInt("teacher_id"));
            notification.setStatus(resultSet.getInt("status"));
            notification.setDate(resultSet.getDate("date"));
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return notification;
    }

    /**
     * 根据教师Id将公告表的数据设置成已读状态
     * @param teacherId
     * @throws SQLException
     */
    @Override
    public void setTeacherAlready(int teacherId) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "update notification " +
                "set status = 1 " +
                "where teacher_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,teacherId);
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
    }

    /**
     * 清空公告表中数据
     * @throws SQLException
     */
    @Override
    public void clearNotification() throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "delete from notification";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
    }
}
