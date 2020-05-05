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

    boolean saveStudentUser(StudentUser studentUser) throws SQLException;

    boolean deleteRegisterApplication(StudentUser studentUser) throws SQLException;
}
