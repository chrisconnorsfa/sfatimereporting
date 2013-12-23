/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.ui.controllers;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author chriconn
 */
public class SearchTimeScreen {
    
    private Integer userid;
    private Integer projectid;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startdate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date enddate;
    private Double total;

    public SearchTimeScreen() { }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
}
