package com.scottishfriendly.timereporting.ui.controllers;

import com.scottishfriendly.timereporting.jsonservices.freckle.FreckleService;
import com.scottishfriendly.timereporting.jsonservices.jira.JiraService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author chriconn
 */
@Controller
@RequestMapping("/searchtime")
public class SearchTimeController {
    private static final String BASE_VIEW = "/searchtime";
    
    @Autowired
    FreckleService freckle;
    
    @Autowired
    JiraService jiraservice;
       
    public SearchTimeController() {
        System.out.println("test");
       
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView doSearch(@ModelAttribute("searchScreen") SearchTimeScreen searchScreen, BindingResult bindingResult, Model model){
        model.addAttribute("response", searchScreen.getProjectid());
        System.out.println("Done userid="+searchScreen.getUserid()+" project = "+ searchScreen.getProjectid());
        
        model.addAttribute("projectnames", this.freckle.getAllprojectnames());
        model.addAttribute("usernames", this.freckle.getAllusernames());
        model.addAttribute("entries",this.freckle.getEntries(searchScreen.getUserid(), searchScreen.getProjectid(), searchScreen.getStartdate(), searchScreen.getEnddate()));
        searchScreen.setTotal(this.freckle.getTotalhours());
        model.addAttribute("searchScreen",searchScreen);
        model.addAttribute("active",1);
        ModelAndView modelandview = new ModelAndView(BASE_VIEW);
        return modelandview;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String showSearch(ModelMap modelMap){
        
        
        modelMap.addAttribute("projectnames", this.freckle.getAllprojectnames());
        modelMap.addAttribute("usernames", this.freckle.getAllusernames());
        
        
        SearchTimeScreen searchScreen = new SearchTimeScreen();
        searchScreen.setEnddate(new Date());
        searchScreen.setStartdate(new Date());
        modelMap.addAttribute("searchScreen", searchScreen);
        modelMap.addAttribute("active",1);        
        return BASE_VIEW;
    }
}
