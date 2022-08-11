package com.example.mobile.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class StudyResource {
    private String name;
    private String link;
    private String subjectId;

    public StudyResource(String name, String link, String subjectId) {
        this.name = name;
        this.link = link;
        this.subjectId = subjectId;
    }

    public StudyResource() {
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
