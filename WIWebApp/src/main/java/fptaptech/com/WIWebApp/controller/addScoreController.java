/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fptaptech.com.WIWebApp.controller;

import fptaptech.com.WIWebApp.interfaces.IClass;
import fptaptech.com.WIWebApp.interfaces.IScore;
import fptaptech.com.WIWebApp.interfaces.IStudentData;
import fptaptech.com.WIWebApp.interfaces.ISubject;
import fptaptech.com.WIWebApp.interfaces.ITest;
import fptaptech.com.WIWebApp.model.ScoreDetails;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lenovo
 */
@Controller
public class addScoreController {
    @Autowired
    private ITest _testService;
    @Autowired
    private ISubject _subjectService;
    @Autowired
    private IClass _classService;
    @Autowired
    private IScore _scoreService;
    @Autowired
    private IStudentData _studentService;
    
    @RequestMapping("/addScore")
    public String addScore(Model model) {
        ScoreDetails score=new ScoreDetails();
        model.addAttribute("score",score);
        model.addAttribute("subject",_subjectService.getList());
        model.addAttribute("class", _classService.getList());
        model.addAttribute("test",_testService.getList());
        model.addAttribute("student",_studentService.findAll());
        return "addScore";
    }
    @PostMapping("/creatScore")
    public String createScore(Model model,ScoreDetails scoreDetails,@RequestParam("userId")String[] userId,@RequestParam("score")int[] scoreReceived) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        scoreDetails.setDate(dateFormat.format(new Date()));
        for(int i=0;i<userId.length;i++){
            scoreDetails.setStudentid(Long.parseLong(userId[i]));
            scoreDetails.setScoreReceived(scoreReceived[i]);
            _scoreService.create(scoreDetails);
        }
        return "redirect:/scoreList";
    }
   
   @RequestMapping("/scoreList")
    public String scoreList(Model model) {
        model.addAttribute("list",_scoreService.findAll());
        return "scoreList";
    }
    
}
