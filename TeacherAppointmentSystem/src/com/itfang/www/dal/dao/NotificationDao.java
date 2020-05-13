package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.Notification;
import com.itfang.www.dal.po.Student;
import com.itfang.www.dal.po.Teacher;

import java.sql.SQLException;
import java.text.ParseException;

/**
 * @author it-fang
 * 公告类对象对数据库的相关操作
 */
public interface NotificationDao {

    /**
     * 向公告表中插入教师id和公告目前的状态
     * @param teacher
     * @param message
     * @return
     * @throws SQLException
     * @throws ParseException
     */
    boolean insertTeacherId(Teacher teacher,String message) throws SQLException, ParseException;

    /**
     * 向公告表中插入学生id和公告目前的状态
     * @param student
     * @param message
     * @return
     * @throws SQLException
     * @throws ParseException
     */
    boolean insertStudentId(Student student, String message) throws SQLException, ParseException;

    /**
     * 根据学生Id和消息状态从数据库中获得公告对象
     * @param studentId
     * @param status
     * @return
     * @throws SQLException
     */
    Notification getStudentInformation(int studentId,int status) throws SQLException;

    /**
     * 根据学生Id将公告表的数据设置成已读状态
     * @param studentId
     * @throws SQLException
     */
    void setStudentAlready(int studentId) throws SQLException;

    /**
     * 根据教师Id和消息状态从数据库中获得公告对象
     * @param teacherId
     * @param status
     * @return
     * @throws SQLException
     */
    Notification getTeacherInformation(int teacherId, int status) throws SQLException;

    /**
     * 根据教师Id将公告表的数据设置成已读状态
     * @param teacherId
     * @throws SQLException
     */
    void setTeacherAlready(int teacherId) throws SQLException;

    /**
     * 清空公告表中数据
     * @throws SQLException
     */
    void clearNotification() throws SQLException;

}
