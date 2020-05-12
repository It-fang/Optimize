package com.itfang.www.dal.po;

import java.util.Date;

/**
 * @author it-fang
 * 公告对象
 */
public class Notification {
    private int id;
    private String message;
    private int studentId;
    private int teacherId;
    private int status;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", studentId=" + studentId +
                ", teacherId=" + teacherId +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
