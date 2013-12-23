/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.jsonservices.jira;

import com.scottishfriendly.timereporting.jsonservices.freckle.FreckleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author chriconn
 */
public class JiraClient {

    @Autowired
    JiraService_old js;
    
    @Autowired
    FreckleService freckle;
    
    public JiraClient() {
    
    }
    
    
    
   
}
