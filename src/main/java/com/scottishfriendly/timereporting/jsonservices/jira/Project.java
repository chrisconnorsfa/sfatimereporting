/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.jsonservices.jira;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author chriconn
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {
    
    String self;
    Integer id;
    String key;
    String name;
    
    public Project() {
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

 
    
    
    
}
