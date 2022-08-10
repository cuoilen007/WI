/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fptaptech.com.WIWebApp.services;

import fptaptech.com.WIWebApp.firebaseframework.fkfirebase;
import fptaptech.com.WIWebApp.interfaces.IClass;
import fptaptech.com.WIWebApp.model.Class;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class ClassService implements IClass{
     private final fkfirebase<Class> _classRepo = new fkfirebase<>(new Class());

    @Override
    public List<Class> getList() {
        return _classRepo.findAll();
    }
}
