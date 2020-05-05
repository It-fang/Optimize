package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.StudentUser;

import java.sql.SQLException;
import java.util.List;

/**
 * @author it-fang
 * 对注册申请表的相关操作
 */
public interface RegisterApplicationDao {
    List<StudentUser> listRegisterApplication() throws SQLException;
}
