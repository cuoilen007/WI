package com.example.mobile.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class RevisionClass implements Serializable {
    private String subject;
    private String teacher;
    private String grade;
    private String startDate;
    private String endDate;
    private String schedule;

    public RevisionClass() {
    }

    public RevisionClass(String subject, String teacher, String grade, String startDate, String endDate, String schedule) {
        this.subject = subject;
        this.teacher = teacher;
        this.grade = grade;
        this.startDate = startDate;
        this.endDate = endDate;
        this.schedule = schedule;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
