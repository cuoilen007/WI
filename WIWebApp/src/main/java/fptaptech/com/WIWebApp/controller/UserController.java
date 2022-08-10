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
        return "admin/index";
    }

//    @PostMapping("/create")
//    public String createCRUD(@RequestBody crud_user user) {
//        return userreposytory.create(user);
//    }
//
//    @GetMapping("/get")
//    public crud_user getCRUD(@RequestParam String documentId) throws InterruptedException, ExecutionException {
//        crud_user a = userreposytory.getCRUD(documentId);
//        return a;
//    }
//
//    @PutMapping("/update")
//    public String updateCRUD(@RequestBody crud_user user){
//        return userreposytory.updateCRUD(user, "7oWgQChwezGI0qNwbn7k");
//    }
//
//    @DeleteMapping("/delete")
//    public String deleteCRUD(@RequestParam String documentId) {
//        return userreposytory.deleteCRUD(documentId);
//    }
//    @GetMapping("/test")
//    public List<User> test() throws ClassNotFoundException {
//        return _userService.getList();
//    }
}
