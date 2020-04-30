package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.Student;
import com.itfang.www.dal.po.StudentUser;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author it-fang
 * 查询学生用户对象的相关操作
 */
public interface StudentUserDao {
    /**
     * 判断用户名是否存在
     * @param condition
     * @throws SQLException
     * @return status
     */
    boolean isExit(HashMap condition) throws SQLException;

    /**
     * 将学生对象数据存入
     * @param student
     * @throws SQLException
     */
    void saveStudent(Student student) throws SQLException;

    /**
     * 根据学号获取学生对象Id
     * @param number
     * @return studentId
     * @throws SQLException
     */
    int getId(String number) throws SQLException;

    /**
     * 将学生用户对象存入数据库中
     * @param studentUser
     * @return status
     * @throws SQLException
     */
    boolean saveStudentUser(StudentUser studentUser) throws SQLException;

    /**
     *检查学生用户密码是否正确
     * @param studentUser
     * @return status
     * @throws SQLException
     */
    boolean checkPassword(StudentUser studentUser) throws SQLException;

}
