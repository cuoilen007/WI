/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fptaptech.com.WIWebApp.interfaces;

import fptaptech.com.WIWebApp.model.StudentData;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface IStudentData {
    List<StudentData> findAll();
    //StudentData findOne(Long id);
    String create(StudentData student);
    
}
