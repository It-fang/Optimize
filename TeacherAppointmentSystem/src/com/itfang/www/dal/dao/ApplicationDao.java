package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.Application;

import java.sql.SQLException;

/**
 * @author it-fang
 * 预约申请的相关操作
 */
public interface ApplicationDao {

    /**
     * 将预约请求数据存入数据库中
     * @param application
     * @return true
     * @throws SQLException
     */
    boolean saveApplication(Application application) throws SQLException;
}
