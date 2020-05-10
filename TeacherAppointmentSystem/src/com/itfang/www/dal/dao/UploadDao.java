package com.itfang.www.dal.dao;

import com.itfang.www.dal.po.Upload;

import java.sql.SQLException;

/**
 * @author it-fang
 * 上传文件的相关存放信息
 */
public interface UploadDao {

    /**
     * 将上传的文件的相关信息保存到数据库中
     * @param upload
     * @return
     * @throws SQLException
     */
    boolean saveUpload(Upload upload) throws SQLException;
}
