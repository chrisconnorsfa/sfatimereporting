/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.ui.controllers;

import com.atlassian.jira.rest.client.api.domain.BasicProject;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author chriconn
 */

public class JiraSummaryScreen {

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;
    private String description;
    private String tag;
    private Integer hours;
    private Integer mins;
    private BasicProject project;
    private String user;
    
    public JiraSummaryScreen() {
    
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMins() {
        return mins;
    }

    public void setMins(Integer mins) {
        this.mins = mins;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BasicProject getProject() {
        return project;
    }

    public void setProject(BasicProject project) {
        this.project = project;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    
    
}
