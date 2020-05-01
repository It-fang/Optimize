package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.Teacher;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author it-fang
 * 进行分页查询的相关操作
 */
public interface PageDao {
    /**
     * 根据教师总数获取总记录条数
     *
     * @param condition
     * @return totalCount
     * @throws SQLException
     */
     int findTotalCount(Map<String, String[]> condition) throws SQLException;

    /**
     * 按照分页情况获取教师对象集合
     * @param start
     * @param rows
     * @param condition
     * @return teachers
     * @throws SQLException
     */
    List<Teacher> listTeacherByPage(int start, int rows, Map<String, String[]> condition) throws SQLException;
}
