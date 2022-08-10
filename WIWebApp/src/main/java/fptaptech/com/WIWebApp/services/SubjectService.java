/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fptaptech.com.WIWebApp.services;

import fptaptech.com.WIWebApp.firebaseframework.fkfirebase;
import fptaptech.com.WIWebApp.interfaces.ISubject;
import fptaptech.com.WIWebApp.model.Subject;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class SubjectService implements ISubject{
    private final fkfirebase<Subject> _subjectRepo = new fkfirebase<>(new Subject());

    @Override
    public List<Subject> getList() {
        return _subjectRepo.findAll();
    }
}
