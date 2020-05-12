package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.Student;
import com.itfang.www.dal.po.Teacher;
import com.itfang.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
