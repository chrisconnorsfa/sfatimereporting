/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.ui.controllers;

import com.scottishfriendly.timereporting.utils.IStandardFields;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author chriconn
 */
public class SearchJiraDateScreen implements IStandardFields{
    
    private String username;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startdate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date enddate;
    private Double total;
    private DateType datetype;
    private Integer status;

    public SearchJiraDateScreen() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public DateType getDatetype() {
        return datetype;
    }

    public void setDatetype(DateType datetype) {
        this.datetype = datetype;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    
    
    
}
