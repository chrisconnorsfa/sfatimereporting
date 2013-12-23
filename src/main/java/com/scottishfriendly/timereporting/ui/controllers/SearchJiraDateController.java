package com.scottishfriendly.timereporting.ui.controllers;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.scottishfriendly.timereporting.jsonservices.freckle.FreckleService;
import com.scottishfriendly.timereporting.jsonservices.jira.Jira;
import com.scottishfriendly.timereporting.jsonservices.jira.JiraService;
import com.scottishfriendly.timereporting.model.JiraIssue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.scottishfriendly.timereporting.utils.IStandardFields;

/**
 *
 * @author chriconn
 */
@Controller
@RequestMapping("/searchjirasbydate")
public class SearchJiraDateController {
    private static final String BASE_VIEW = "/searchjirasbydate";
    
    @Autowired
    FreckleService freckle;
    
    @Autowired
    JiraService jiraservice;
       
    public SearchJiraDateController() {
        System.out.println("test");
       
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView doSearch(@ModelAttribute("searchScreen") SearchJiraDateScreen searchScreen,BindingResult bindingResult, Model model){
        
        List<Issue> results = this.jiraservice.searchJIRAs(searchScreen.getUsername(), searchScreen.getStartdate(), searchScreen.getEnddate(), searchScreen.getStatus(),searchScreen.getDatetype());
        List usernameslist = new ArrayList(this.freckle.getAllusernames().values());
        model.addAttribute("usernames", usernameslist);
        
        model.addAttribute("searchScreen",searchScreen);
        model.addAttribute("datetype_map",IStandardFields.DateType.getNamesMap(IStandardFields.DateType.values()));
        model.addAttribute("status_map",  IStandardFields.Status.getNamesMap(IStandardFields.Status.values()));
        model.addAttribute("results",results);
        model.addAttribute("active",2);
        ModelAndView modelandview = new ModelAndView(BASE_VIEW);
      
     
        return modelandview;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String showSearch(ModelMap modelMap){
       
        List usernameslist = new ArrayList(this.freckle.getAllusernames().values());
        modelMap.addAttribute("usernames", usernameslist);
          
        modelMap.addAttribute("datetype_map", IStandardFields.DateType.getNamesMap(IStandardFields.DateType.values()));
        modelMap.addAttribute("status_map",  IStandardFields.Status.getNamesMap(IStandardFields.Status.values()));
        
        SearchJiraDateScreen searchScreen = new SearchJiraDateScreen();
 
        searchScreen.setEnddate(new Date());
        searchScreen.setStartdate(new Date());
        modelMap.addAttribute("searchScreen", searchScreen);
        modelMap.addAttribute("active",2);        
        return BASE_VIEW;
    }
    
//    private List<Jira> execute(SearchJiraDateScreen searchScreen){
//        
//        Date startdate = searchScreen.getStartdate();
//        Date enddate = searchScreen.getEnddate();
//        String assignee = searchScreen.getUsername();
//        String status = searchScreen.getStatus();
//        
//        
//    }
}
