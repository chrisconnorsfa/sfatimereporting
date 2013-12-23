/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.model;

import com.atlassian.jira.rest.client.api.domain.Attachment;
import com.atlassian.jira.rest.client.api.domain.BasicComponent;
import com.atlassian.jira.rest.client.api.domain.BasicIssueType;
import com.atlassian.jira.rest.client.api.domain.BasicPriority;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.BasicResolution;
import com.atlassian.jira.rest.client.api.domain.BasicStatus;
import com.atlassian.jira.rest.client.api.domain.BasicVotes;
import com.atlassian.jira.rest.client.api.domain.BasicWatchers;
import com.atlassian.jira.rest.client.api.domain.ChangelogGroup;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueField;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.Subtask;
import com.atlassian.jira.rest.client.api.domain.TimeTracking;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.api.domain.Version;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import java.net.URI;
import java.util.Collection;
import java.util.Set;
import org.joda.time.DateTime;

/**
 *
 * @author chriconn
 */
public class JiraIssue{

    private Double estimate;
    private Issue issue;

    public JiraIssue(Issue issue) {
        this.issue=issue;
        this.estimate=getHLE();
    }
    

    private Double getHLE() {
       IssueField hleField=this.issue.getField("customfield_10000");
        if(hleField==null || hleField.getValue()==null){
            return new Double(0);
        }
        return (Double)hleField.getValue();
    }

    public Double getEstimate() {
        return getHLE();
    }

    public Issue getIssue() {
        return issue;
    }
    
    
    
}
