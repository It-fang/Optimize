package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.StudentUser;

import java.sql.SQLException;
import java.util.List;

/**
 * @author it-fang
 * 对注册申请表的相关操作
 */
public interface RegisterApplicationDao {
    /**
     * 从数据库中获取注册申请表的所有数据
     * @return
     * @throws SQLException
     */
    List<StudentUser> listRegisterApplication() throws SQLException;

    /**
     * 将studentUser对象存入数据库的student_user表中
     * @param studentUser
     * @return
     * @throws SQLException
     */
    boolean saveStudentUser(StudentUser studentUser) throws SQLException;

    /**
     * 根据studentId删除register_application表中对应字段
     * @param studentId
     * @return
     * @throws SQLException
     */
    boolean deleteRegisterApplication(int studentId) throws SQLException;

}
