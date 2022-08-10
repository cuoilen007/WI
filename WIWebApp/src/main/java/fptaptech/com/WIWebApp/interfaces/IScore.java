/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fptaptech.com.WIWebApp.interfaces;

import fptaptech.com.WIWebApp.model.ScoreDetails;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface IScore {
      List<ScoreDetails> findAll();
      String create(ScoreDetails scoreDetails);
}
