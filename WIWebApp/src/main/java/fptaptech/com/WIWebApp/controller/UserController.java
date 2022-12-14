/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fptaptech.com.WIWebApp.controller;

import fptaptech.com.WIWebApp.interfaces.IUser;
import fptaptech.com.WIWebApp.model.User;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CHIEN
 */
@Controller
public class UserController {

    @Autowired
    private IUser _userService;

    @GetMapping("/test")
    public String test() {
        return "layouts/register";
    }
    
    @PostMapping(value = "/login")
    public String findUserByName(@RequestParam("mail") String mail, @RequestParam("pass") String pass ){
        if (_userService.getUserbymail(mail).getPassword().equals(pass)) {
            return "admin/index";
        }
        return "layouts/register";
    }
    @GetMapping(value = "/login")
    public String findUserByName(){
        return "layouts/login";
    }
    
    @PostMapping(value = "user/create-student")
    public String createStudend(@RequestBody()User user){
        _userService.create(user);
        return "layouts/login";
    }
    @PostMapping(value = "user/create-teacher")
    public String createTeacher(@RequestBody()User user){
//        user.se
        _userService.create(user);
        return "layouts/login";
    }
}
