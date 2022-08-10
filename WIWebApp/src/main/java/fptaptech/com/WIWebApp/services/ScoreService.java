/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fptaptech.com.WIWebApp.services;

import fptaptech.com.WIWebApp.firebaseframework.fkfirebase;
import fptaptech.com.WIWebApp.interfaces.IScore;
import fptaptech.com.WIWebApp.model.ScoreDetails;
import fptaptech.com.WIWebApp.model.User;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author lenovo
 */
@Service
public class ScoreService implements IScore{
private final fkfirebase<ScoreDetails> _scoreRepo = new fkfirebase<>(new ScoreDetails());
    
    @Override
    public List<ScoreDetails> findAll() {
        return _scoreRepo.findAll();
    }

    @Override
    public String create(ScoreDetails scoreDetails) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
