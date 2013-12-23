/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.services;

import com.scottishfriendly.timereporting.jsonservices.freckle.FreckleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author chriconn
 */

@Service
public class ReportingService {

    @Autowired
    private FreckleService frecklservice;
    private LdapUserDetailsImpl currentUser;
    private String currentUserName;
    
    
    public ReportingService() {
        
    }

    public LdapUserDetailsImpl getCurrentUser() {
        return (LdapUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String getCurrentUserName() {
        return parseUserName(getCurrentUser());
    }
    
    public String getCurrentFirstName(){
        String fullName = getCurrentUser().getDn();
        int endIndex =  fullName.indexOf(",ou=");
        int startIndex = fullName.indexOf("\\, ")+3;
        return fullName.substring(startIndex, endIndex);
    }
    
    public String getCurrentLastName(){
        String fullName = getCurrentUser().getDn();
        int index = fullName.indexOf("\\, ");
        return fullName.substring(3, index);
    }
    
    private String parseUserName(LdapUserDetailsImpl user){
        
        return getCurrentFirstName()+" "+getCurrentLastName();
    }
    
}
