package com.itfang.www.dal.po;

import java.util.Date;

/**
 * @author it-fang
 * 教师对象
 */
public class Teacher {
    private int id;
    private String name;
    private String number;
    private String college;
    private String major;
    private String clas;
    private Date freeTime;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public Date getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(Date freeTime) {
        this.freeTime = freeTime;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                ", clas='" + clas + '\'' +
                ", freetime=" + freeTime +
                '}';
    }
}
