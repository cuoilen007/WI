package com.example.mobile.model;

import com.google.firebase.database.IgnoreExtraProperties;

public class StudyResource {
    private Long id;
    private String name;
    private String link;
    private String subjectId;

    public StudyResource(String name, String link, String subjectId) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.subjectId = subjectId;
    }

    public StudyResource() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}
