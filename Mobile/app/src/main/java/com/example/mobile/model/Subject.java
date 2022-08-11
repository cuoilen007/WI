package com.example.mobile.model;

import java.io.Serializable;

public class Subject implements Serializable {
    private String SubjectName;

    public Subject() {
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }
}
