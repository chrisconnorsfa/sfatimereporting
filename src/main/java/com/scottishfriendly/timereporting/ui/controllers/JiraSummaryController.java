/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.ui.controllers;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueField;
import com.scottishfriendly.timereporting.jsonservices.freckle.Entry;
import com.scottishfriendly.timereporting.jsonservices.freckle.FreckleService;
import com.scottishfriendly.timereporting.jsonservices.freckle.PostEntry;
import com.scottishfriendly.timereporting.jsonservices.freckle.Tag;
import com.scottishfriendly.timereporting.jsonservices.jira.Jira;
import com.scottishfriendly.timereporting.jsonservices.jira.JiraService;
import com.scottishfriendly.timereporting.model.JiraIssue;
import com.scottishfriendly.timereporting.services.ReportingService;
import com.scottishfriendly.timereporting.utils.IStandardFields;
import com.scottishfriendly.timereporting.utils.IStandardFields.Project;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author chriconn
 */

@Controller
@RequestMapping("/jirasummary")
public class JiraSummaryController {
    private static final String BASE_VIEW = "jirasummary";
    
    @Autowired
    FreckleService freckle;
    
    @Autowired
    JiraService jiraservice;
    
    @Autowired 
    ReportingService reportingService;
    
    @RequestMapping(value = "{jiraId}", method = RequestMethod.GET)
    public String doGet(ModelMap modelMap, @PathVariable String jiraId, @ModelAttribute("entryScreen") JiraSummaryScreen entryScreen, BindingResult bindingResult){
        modelMap.addAllAttributes(populateModel(modelMap, entryScreen, jiraId));
        return BASE_VIEW;
    }
    
    @RequestMapping(value="{jiraId}", method = RequestMethod.POST)
    public String doPost(ModelMap modelMap, @PathVariable String jiraId, @ModelAttribute("entryScreen") JiraSummaryScreen searchScreen, BindingResult bindingResult){
        modelMap.addAllAttributes(populateModel(modelMap, searchScreen, jiraId));
        
        return BASE_VIEW;
    }
    
    @RequestMapping(value="/delete/{jiraId}/{timeentryId}", method = RequestMethod.POST)
    public ModelAndView doDelete(ModelMap modelMap, @PathVariable String jiraId, @PathVariable Long timeentryId, JiraSummaryScreen searchScreen, BindingResult bindingResult){
        
        // Logic disabled for testing
        //boolean isSuccess = deleteEntry(timeentryId);
        modelMap.addAllAttributes(populateModel(modelMap, searchScreen, jiraId));
        
        ModelAndView modelandview = new ModelAndView(BASE_VIEW+"/"+jiraId);
         
        return modelandview; 
    }
    
    @RequestMapping(value="/create/{jiraId}/{projectId}", method = RequestMethod.POST)
    public ModelAndView doCreate(ModelMap modelMap, @PathVariable String projectId,@PathVariable String jiraId, @Valid @ModelAttribute("entryScreen") JiraSummaryScreen entryScreen, BindingResult bindingResult){
        
        TimePostingValidator validator = new TimePostingValidator();
        
        ValidationUtils.invokeValidator(validator, entryScreen, bindingResult);
        // Logic disabled for testing
        if(!bindingResult.hasErrors()){
            boolean isSuccess = createTimeEntry(entryScreen,jiraId,projectId);
            modelMap.addAllAttributes(populateModel(modelMap, resetEntryFields(entryScreen), jiraId));
        }
        
        RedirectView rv = new RedirectView("/"+BASE_VIEW+"/"+jiraId);
        rv.setExposeModelAttributes(false);
        rv.setContextRelative(true);
        ModelAndView modelandview = new ModelAndView(rv);
      
        
        return modelandview;
    }
    
    private boolean createTimeEntry(JiraSummaryScreen entryScreen, String jiraId,String projectId){
        PostEntry entry = createEntry(entryScreen, jiraId,projectId);
        
        // Call service to write entry and return true / false
        freckle.create(entry);
        return true;
    }
    
    private void getUserId(String userName){
        
    }
    
    private PostEntry createEntry(JiraSummaryScreen entryScreen, String jiraId, String projectId){
        
        PostEntry entry = new PostEntry();
        entry.setUser(entryScreen.getUser());
       
        entry.setDescription(entryScreen.getDescription());
        String[] tags = {jiraId,entryScreen.getDescription()};
        entry.setDescription(jiraId+", "+entryScreen.getDescription());
        String minsString = Integer.toString(entryScreen.getMins()+(entryScreen.getHours()*60));
        entry.setMinutes(minsString);
        String email = reportingService.getCurrentFirstName()+"."+reportingService.getCurrentLastName()+"@scottishfriendly.co.uk";
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        entry.setDate(dt1.format(entryScreen.getDate()));
        
        entry.setUser(email);
        Project project = IStandardFields.Project.valueOf(projectId);
        entry.setAllowhashtags(true);
        entry.setProjectid(project.getId());
        
        return entry;
    }
    
    private JiraSummaryScreen resetEntryFields(JiraSummaryScreen entryScreen){
        entryScreen.setDate(null);
        entryScreen.setDescription(null);
        entryScreen.setHours(0);
        entryScreen.setMins(0);
        return entryScreen;
    }
    
    private boolean deleteEntry(Long id){
        
        return freckle.delete(id);
    }
    
    private ModelMap populateModel(ModelMap modelMap, JiraSummaryScreen screen, String jiraId){
        modelMap.put("jiraid", jiraId);
        Issue issue = this.jiraservice.getJIRAById(jiraId);
                
        Double hleValue = this.jiraservice.getHLE(issue);
        
        Tag tag = new Tag();
        tag.setName(jiraId);
        
        List<Tag> tags = Arrays.asList(tag);
        
        List<Entry> entries = this.freckle.getEntriesByTags(tags);
        screen.setProject(issue.getProject());
        screen.setUser(issue.getAssignee().getName());
        Double hours = this.freckle.getTotalhours();
        modelMap.put("hourslogged", hours);
        modelMap.put("issue", issue);
        modelMap.put("entries", entries);
        modelMap.addAttribute("active",2);
        modelMap.addAttribute("projectkey", issue.getProject().getKey());
        modelMap.addAttribute("hle",hleValue);
        //modelMap.addAttribute("entryScreen",screen);
        return modelMap;
    }
    
    
}
