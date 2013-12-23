package com.bssuk.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author chriconn
 */
@Controller
@RequestMapping("/api/time/")
public class TimeController {
    @RequestMapping(value = "/user/entries/{name}", method = RequestMethod.GET)
	public String getTimeentriesbyname(@PathVariable String name, ModelMap model) {
        return "";   
    }
        
    @RequestMapping(value = "/project/entries/{name}", method = RequestMethod.GET)
	public String getProjectentriesbyname(@PathVariable String name, ModelMap model) {
        return "";   
    }
}
