package com.itfang.www.dal.dao;

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
}
