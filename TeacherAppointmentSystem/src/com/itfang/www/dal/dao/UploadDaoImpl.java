package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.Upload;
import com.itfang.www.util.JdbcUtil;

import java.sql.*;

/**
 * @author it-fang
 * 上传文件的相关存放信息
 */
public class UploadDaoImpl implements UploadDao {

    /**
     * 将上传的文件的相关信息保存到数据库中
     * @param upload
     * @return
     * @throws SQLException
     */
    @Override
    public boolean saveUpload(Upload upload) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "insert into upload " +
                "(file_name,student_id) " +
                "value (?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,upload.getFileName());
        preparedStatement.setInt(2,upload.getStudentId());
        preparedStatement.execute();
        JdbcUtil.close(preparedStatement,conn);
        return true;
    }

    /**
     * 获得上传的文件的信息
     * @param studentId
     * @return
     * @throws SQLException
     */
    @Override
    public Upload getUpload(int studentId) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql = "" +
                "select * from upload where student_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,studentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Upload upload = new Upload();
        while(resultSet.next()){
            upload.setId(resultSet.getInt("id"));
            upload.setFileName(resultSet.getString("file_name"));
            upload.setStudentId(studentId);
        }
        JdbcUtil.close(resultSet,preparedStatement,conn);
        return upload;
    }
}
