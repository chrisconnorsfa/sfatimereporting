/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.utils;

import com.scottishfriendly.timereporting.jsonservices.jira.Jira;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chriconn
 */
public class MockJiraData {
    
    public static List<Jira> mockjiraresultslist_1(){
        
            Jira jira1 = mockjiraresult_1();
            Jira jira2 = mockjiraresult_2();
            Jira jira3 = mockjiraresult_3();

            return Arrays.asList(jira1,jira2,jira3);
        
    }
    
    public static Jira mockjiraresult_1(){
        
            Calendar cal = Calendar.getInstance();
            Date closeddate = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, -15);
            Date openeddate = cal.getTime();
           
            Jira jira1 = new Jira();
          
            jira1.setId("SFATOGR-925");
          

            return jira1;
    }
    
    public static Jira mockjiraresult_2(){
        
            Calendar cal = Calendar.getInstance();
            Date closeddate = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, -12);
            Date openeddate = cal.getTime();
           
            Jira jira1 = new Jira();
            
            jira1.setId("SFATOGR-725");
          

            return jira1;
    }
    
    public static Jira mockjiraresult_3(){
        
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, -7);
            Date closeddate = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, -12);
            Date openeddate = cal.getTime();
           
            Jira jira1 = new Jira();
          
            jira1.setId("SFATOGR-626");
           

            return jira1;
    }
    
}
