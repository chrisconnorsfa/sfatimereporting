/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.jsonservices.freckle;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author chriconn
 */

@XmlRootElement(name="Entry")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostEntry {
    
    private String minutes;
    private String user;
    @JsonProperty("project-id")
    private Integer projectid;
    private String description;
    private String date;
    private boolean allowhashtags=true;

    public PostEntry() {
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isAllowhashtags() {
        return allowhashtags;
    }

    public void setAllowhashtags(boolean allowhashtags) {
        this.allowhashtags = allowhashtags;
    }
    
    
}
