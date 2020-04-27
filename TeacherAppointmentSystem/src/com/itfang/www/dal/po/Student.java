package com.itfang.www.dal.po;

/**
 * @author it-fang
 */
public class Student {
    private int id;
    private String number;
    private String name;
    private String sex;
    private String college;
    private String major;
    private String clas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                ", clas='" + clas + '\'' +
                '}';
    }
}
