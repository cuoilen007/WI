package fptaptech.com.WIWebApp.model;

import java.io.Serializable;


public class ScoreDetails implements Serializable {

    private String testId;
    private String classId;
    private Long studentid;
    private String subjectid;
    private int scoreReceived;
    private String date;

    public ScoreDetails() {
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Long getStudentid() {
        return studentid;
    }

    public void setStudentid(Long studentid) {
        this.studentid = studentid;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
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
