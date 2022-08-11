package com.example.mobile.model;

import java.io.Serializable;

public class Class implements Serializable {
    private String className;
    private Long grade;

    public Class() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }
}
