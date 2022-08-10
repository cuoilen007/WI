/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fptaptech.com.WIWebApp.controller;

import fptaptech.com.WIWebApp.interfaces.IClass;
import fptaptech.com.WIWebApp.interfaces.IScore;
import fptaptech.com.WIWebApp.interfaces.ISubject;
import fptaptech.com.WIWebApp.interfaces.ITest;
import fptaptech.com.WIWebApp.model.*;
import fptaptech.com.WIWebApp.model.Class;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lenovo
 */
@RestController
public class ScoreController {
    @Autowired
    private ITest _testService;
    @Autowired
    private ISubject _subjectService;
    @Autowired
    private IClass _classService;
    @Autowired
    private IScore _scoreService;
    
    @GetMapping("/test/list")
    public List<Test> testList() throws ClassNotFoundException {
        return _testService.getList();
    }
    @GetMapping("/Subject/list")
    public List<Subject> subjectList() throws ClassNotFoundException {
        return _subjectService.getList();
    }
    @GetMapping("/class/list")
    public List<Class> classList() throws ClassNotFoundException {
        return _classService.getList();
    }
    @PostMapping("/api/addScore")
    public String addScore() throws ClassNotFoundException {
        ScoreDetails scoreDetails=new ScoreDetails();
        scoreDetails.setDate("10/08/20022");
        scoreDetails.setScoreReceived(8);
        scoreDetails.setClassId("10A");
        scoreDetails.setStudentid(Long.parseLong("2"));
        scoreDetails.setTestId("15 minutes");
        return _scoreService.create(scoreDetails);
    }
    
    
    
    @GetMapping("/score/list")
    public List<ScoreDetails> scoreList() throws ClassNotFoundException {
        return _scoreService.findAll();
    }
    
    
}
