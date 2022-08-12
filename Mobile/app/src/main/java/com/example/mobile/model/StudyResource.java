package com.example.mobile.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class StudyResource implements Serializable {
    private String link;
    private String subject;
    private String teacher;
    private String topic;

    public StudyResource() {
    }

    public StudyResource(String link, String subject, String teacher, String topic) {
        this.link = link;
        this.subject = subject;
        this.teacher = teacher;
        this.topic = topic;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
