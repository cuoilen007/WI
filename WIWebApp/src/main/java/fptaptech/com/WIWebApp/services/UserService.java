/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fptaptech.com.WIWebApp.services;

import fptaptech.com.WIWebApp.firebaseframework.fkfirebase;
import fptaptech.com.WIWebApp.interfaces.IUser;
import fptaptech.com.WIWebApp.model.User;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author CHIEN
 */
@Service
public class UserService implements IUser{
    private final fkfirebase<User> _userRepo = new fkfirebase<>(new User());
    
    @Override
    public List<User> getList() {
        return _userRepo.findAll();
    }

    @Override
    public User getUserbymail(String mail) {
        try {
            return _userRepo.getUserbymail(mail);
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
