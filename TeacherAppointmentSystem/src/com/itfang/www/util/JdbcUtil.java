package com.itfang.www.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author it-fang
 * 获得数据库的连接
 */
public class JdbcUtil {
    public static DataSource ds;
    static {
        try {
            //1,加载配置文件
            Properties pro = new Properties();
            pro.load(JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
            //2,初始化连接词对象
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获得连接池对象
     * @return DataSource ds
     */
    public static DataSource getDataSource(){
        return ds;
    }
    /**
     * 获得Connection对象
     * @return Connection ds.getConnection
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 释放资源
     * @param preparedStatement
     * @param connection
     */
    public static void close(PreparedStatement preparedStatement, Connection connection){
        close(null,preparedStatement,connection);
    }
    public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
