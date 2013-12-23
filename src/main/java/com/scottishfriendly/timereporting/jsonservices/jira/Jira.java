/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.jsonservices.jira;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author chriconn
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Jira implements Serializable{
    
    private String id;
    private String key;
    private String self;
    //private Map<String,Object> fields;
    private Fields fields;
    
    
    public Jira() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    //    public Map<String, Object> getFields() {
    //        return fields;
    //    }
    //
    //    public void setFields(Map<String, Object> fields) {
    //        this.fields = fields;
    //    }
    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

   
    
}
