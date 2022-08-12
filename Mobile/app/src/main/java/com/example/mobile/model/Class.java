package com.example.mobile.model;

import java.io.Serializable;

public class Class implements Serializable {
    private String className;
    private String grade;

    public Class() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
