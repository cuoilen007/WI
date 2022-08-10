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
public class Subject implements Serializable{
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
