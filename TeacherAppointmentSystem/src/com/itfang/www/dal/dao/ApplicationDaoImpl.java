package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.Application;
import com.itfang.www.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author it-fang
 * 预约申请的相关操作
 */
public class ApplicationDaoImpl implements ApplicationDao{
    /**
     * 将预约请求数据存入数据库中
     * @param application
     * @return true
     * @throws SQLException
     */
    @Override
    public boolean saveApplication(Application application) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "insert into application " +
                "(teacher_id,teacher_name,student_id,student_name,student_number,apply_time) " +
                "value (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,application.getTeacherId());
        preparedStatement.setString(2,application.getTeacherName());
        preparedStatement.setInt(3,application.getStudentId());
        preparedStatement.setString(4,application.getStudentName());
        preparedStatement.setString(5,application.getStudentNumber());
        preparedStatement.setDate(6, new Date(application.getApplyTime().getTime()));
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
        return true;
    }

    @Override
    public List<Application> listResult(int studentId) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from application where student_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,studentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Application> applications = new ArrayList<Application>();
        Application application = null;
        while(resultSet.next()){
            application = new Application();
            application.setId(resultSet.getInt("id"));
            application.setTeacherId(resultSet.getInt("teacher_id"));
            application.setTeacherName(resultSet.getString("teacher_name"));
            application.setTeacherId(resultSet.getInt("student_id"));
            application.setStudentName(resultSet.getString("student_name"));
            application.setStudentNumber(resultSet.getString("student_number"));
            application.setApplyTime(resultSet.getDate("apply_time"));
            application.setIfAgree(resultSet.getString("if_agree"));

            applications.add(application);
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return applications;
    }
}