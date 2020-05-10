package com.itfang.www.dal.po;

/**
 * @author it-fang
 * 存放文件存放的相关信息
 */
public class Upload {
    private int id;
    private String fileName;
    private int studentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Upload{" +
                "id=" + id +
                ", url='" + fileName + '\'' +
                ", studentId=" + studentId +
                '}';
    }
}
