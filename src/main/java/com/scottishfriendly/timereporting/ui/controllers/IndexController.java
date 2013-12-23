/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.ui.controllers;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.scottishfriendly.timereporting.jsonservices.jira.JiraService;
import com.scottishfriendly.timereporting.services.ReportingService;
import com.scottishfriendly.timereporting.utils.IStandardFields;
import com.scottishfriendly.timereporting.utils.IStandardFields.DateType;
import org.springframework.security.ldap.userdetails.Person; 
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author chriconn
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    private static final String BASE_VIEW = "/index";
    @Autowired 
    ReportingService reportingService;
    
    @Autowired
    JiraService jiraservice;
    
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap modelMap){
    

      
        
        //Person person = getCurrentPerson();
        //String name = getFullName();
        
        //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        
        
        modelMap.addAttribute("active",0);
        modelMap.addAttribute("currentusername", reportingService.getCurrentUserName());
        List<Issue> results = this.jiraservice.getCurrentActiveForUser(reportingService.getCurrentUserName());
        modelMap.addAttribute("mycurrentjiras", results);
        results = this.jiraservice.getWatchedActiveForUser(reportingService.getCurrentUserName());
        modelMap.addAttribute("mywatchedcurrentjiras", results);
        

        return BASE_VIEW;
        
    }
    
    public Person getCurrentPerson(){
        return (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
     public String getFullName() {  
        Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String[] cn = person.getCn();  
  
        StringBuilder sbuf = new StringBuilder("");  
        if (cn != null  &&  cn.length > 0) {  
            for (String s : cn) {  
                sbuf.append(s).append(" ");  
            }  
        }  
        return sbuf.toString().trim();  
    }  
    
}
