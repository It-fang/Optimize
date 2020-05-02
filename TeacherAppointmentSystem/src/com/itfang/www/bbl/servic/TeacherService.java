package com.itfang.www.bbl.servic;

import com.itfang.www.dal.po.Teacher;
import com.itfang.www.dal.po.TeacherUser;

import java.sql.SQLException;

/**
 * @author it-fang
 * @Description 教师用户的所有相关操作
 * @date 2020-4-27
 */
public interface TeacherService {

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     * @throws SQLException
     */
    Object checkUsername(String username) throws SQLException;

    Object register(Teacher teacher, TeacherUser teacherUser) throws SQLException;
}
