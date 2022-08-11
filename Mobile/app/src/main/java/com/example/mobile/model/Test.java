package com.example.mobile.model;

import java.io.Serializable;

public class Test implements Serializable {
    private String testname;

    public Test() {
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

}
