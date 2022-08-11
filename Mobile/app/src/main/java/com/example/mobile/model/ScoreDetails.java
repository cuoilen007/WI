package com.example.mobile.model;

import java.io.Serializable;

public class ScoreDetails implements Serializable {
    private String testname;
    private String className;
    private Long studentid;
    private String SubjectName;
    private int scoreReceived;
    private String date;

    public ScoreDetails() {
    }


    public Long getStudentid() {
        return studentid;
    }

    public void setStudentid(Long studentid) {
        this.studentid = studentid;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public int getScoreReceived() {
        return scoreReceived;
    }

    public void setScoreReceived(int scoreReceived) {
        this.scoreReceived = scoreReceived;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
