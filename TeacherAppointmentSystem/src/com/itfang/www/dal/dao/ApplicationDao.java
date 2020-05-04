package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.Application;

import java.sql.SQLException;
import java.util.List;

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

    /**
     * 查询预约结果
     * @param studentId
     * @return applications
     * @throws SQLException
     */
    List<Application> listResult(int studentId) throws SQLException;

    /**
     * 根据教师Id从数据库中查询预约请求
     * @param teacherId
     * @return
     * @throws SQLException
     */
    List<Application> listApplication(int teacherId) throws SQLException;

    /**
     * 将教师的审批结果存入数据库中
     * @param studentId
     * @param teacherId
     * @param ifAgree
     * @return
     * @throws SQLException
     */
    boolean saveAgree(int studentId, int teacherId, String ifAgree) throws SQLException;

    boolean deleteApplication(int studentId, int teacherId) throws SQLException;
}
