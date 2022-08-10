/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fptaptech.com.WIWebApp.model;

import java.io.Serializable;

/**
 *
 * @author lenovo
 */
public class Class implements Serializable{

    private String className;
     private Long grade;

    public Class() {
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    
    

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    

}
