/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.jsonservices.jira;

import com.scottishfriendly.timereporting.utils.IStandardFields;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.scottishfriendly.timereporting.model.JiraIssue;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chriconn
 */
public interface IJiraService extends IStandardFields{
    
    public List<Jira> getAllJIRAs();
    public Issue getJIRAById(String id);
    public List<Issue> getCurrentActiveForUser(String username);
    public List<Issue> getWatchedActiveForUser(String username);
    
    public List<Issue> searchJIRAs(String assignedto, Date startdate, Date enddate, Integer status, DateType datetype );
    public List<Issue> searchJIRAs(String query );
    public Double getHLE(Issue issue);
    
}
