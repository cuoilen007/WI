/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fptaptech.com.WIWebApp.services;

import fptaptech.com.WIWebApp.firebaseframework.fkfirebase;
import fptaptech.com.WIWebApp.interfaces.IStudentData;
import fptaptech.com.WIWebApp.model.StudentData;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class StudentDataService implements IStudentData{

    private final fkfirebase<StudentData> _studentRepo = new fkfirebase<>(new StudentData());
    
    @Override
    public List<StudentData> findAll() {
        return _studentRepo.findAll();
    }

//    @Override
//    public StudentData findOne(String id) {
//        try {
//            return _studentRepo.getCRUD(id);
//        } catch (InterruptedException | ExecutionException ex) {
//            Logger.getLogger(StudentDataService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }

    @Override
    public String create(StudentData student) {
      return _studentRepo.create(student);
    }
    
}
