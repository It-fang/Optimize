package com.itfang.www.dal.po;

import java.util.Date;

/**
 * @author it-fang
 */
public class Application {
    private int id;
    private int teacherId;
    private String teacherName;
    private int studentId;
    private String studentName;
    private String studentNumber;
    private Date applyTime;
    private String ifAgree;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getIfAgree() {
        return ifAgree;
    }

    public void setIfAgree(String ifAgree) {
        this.ifAgree = ifAgree;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", applyTime='" + applyTime + '\'' +
                ", ifAgree='" + ifAgree + '\'' +
                '}';
    }
}
