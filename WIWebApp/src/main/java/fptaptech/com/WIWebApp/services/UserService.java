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
public class UserService implements IUser {

    private final fkfirebase<User> _userRepo = new fkfirebase<>(new User());

    @Override
    public List<User> getList() {
        return _userRepo.findAll();
    }

    @Override
    public User getUserbymail(String mail) {
        return _userRepo.getByField("email", mail);
    }

    @Override
    public User create(User user) {
        if (_userRepo.getByField("email", user.getEmail()) == null) {
            if (!(_userRepo.create(user).equals("fail"))) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User update(User user) {
        if (!(_userRepo.getByField("email" ,user.getEmail()) == null)) {
            if (!(_userRepo.update(user, user.getEmail()).equals("fail"))) {
                return user;
            }
        }
        return null;
    }

}
