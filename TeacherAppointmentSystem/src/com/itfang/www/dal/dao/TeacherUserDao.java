package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.Teacher;
import com.itfang.www.dal.po.TeacherUser;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author it-fang
 * 教师用户对象访问数据库的相关操作
 */
public interface TeacherUserDao {

    /**
     * 判断用户名是否存在,存在返回true,不存在返回false
     * @param condition
     * @return status
     * @throws SQLException
     */
    boolean isExit(HashMap condition) throws SQLException;

    /**
     * 将教师对象存入数据库中
     * @param teacher
     * @throws SQLException
     */
    void saveTeacher(Teacher teacher) throws SQLException;

    /**
     * 通过教师工号获得教师Id
     * @param number
     * @return
     * @throws SQLException
     */
    int getId(String number) throws SQLException;

    /**
     * 将教师用户对象存入数据库中
     * @param teacherUser
     * @return
     * @throws SQLException
     */
    boolean saveTeacherUser(TeacherUser teacherUser) throws SQLException;

    boolean checkPassword(TeacherUser teacherUser) throws SQLException;
}
