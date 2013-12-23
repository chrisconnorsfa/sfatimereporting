/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.services;

import java.util.List;

/**
 *
 * @author chriconn
 */
public class SummaryEntry {
    
    private String description;
    private Integer hours;
    private Object summary;
    private List<Object> detailentries;
    
    public SummaryEntry() {
        
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Object getSummary() {
        return summary;
    }

    public void setSummary(Object summary) {
        this.summary = summary;
    }

    public List<Object> getDetailentries() {
        return detailentries;
    }

    public void setDetailentries(List<Object> detailentries) {
        this.detailentries = detailentries;
    }
    
    
    
}
