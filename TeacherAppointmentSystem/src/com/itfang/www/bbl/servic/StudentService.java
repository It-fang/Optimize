package com.itfang.www.bbl.servic;

import com.itfang.www.dal.po.Student;
import com.itfang.www.dal.po.StudentUser;

import java.sql.SQLException;

/**
 * @author it-fang
 * @Description 学生用户的所有相关操作
 * @date 2020-4-27
 */
public interface StudentService {
    /**
     * 检查用户名是否存在
     * @param username
     * @throws SQLException
     * @return resultInfo
     */
    Object checkUsername(String username) throws SQLException;

    /**
     * 注册学生账号,并将数据存入
     * @param student
     * @param studentUser
     * @return resultInfo
     * @throws SQLException
     */
    Object register(Student student, StudentUser studentUser) throws SQLException;
}
