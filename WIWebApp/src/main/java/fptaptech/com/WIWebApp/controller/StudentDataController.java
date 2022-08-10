/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fptaptech.com.WIWebApp.controller;

import fptaptech.com.WIWebApp.interfaces.IStudentData;
import fptaptech.com.WIWebApp.model.StudentData;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentDataController {
    
     @Autowired
    private IStudentData _studentService;
     
   @GetMapping("/student")
    public List<StudentData> studentList() throws ClassNotFoundException {
        return _studentService.findAll();
    }
    
}
