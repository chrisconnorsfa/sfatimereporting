package com.scottishfriendly.timereporting.jsonservices.jira;

import java.io.Serializable;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author chriconn
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchReply implements Serializable{
    
    private List<Jira> issues;
    private Integer size;
    
    @JsonProperty("max-results")
    private Integer maxresults;
    
    public SearchReply() { }

    public List<Jira> getIssues() {
        return issues;
    }

    public void setIssues(List<Jira> issues) {
        this.issues = issues;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getMaxresults() {
        return maxresults;
    }

    public void setMaxresults(Integer maxresults) {
        this.maxresults = maxresults;
    }

   

   
}
