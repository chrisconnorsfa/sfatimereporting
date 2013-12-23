/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.jsonservices.jira;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author chriconn
 */

public class JiraService_old {
    

    private final String BASE_URL = "https://jira.atlassian.com/rest/api/2/";
    
    

  
    
    private Jira doSearchJIRA(String key){
       
        String url = BASE_URL +"issue/"+key;
        
        RestTemplate restTemplate=new RestTemplate();
              
        MappingJacksonHttpMessageConverter json = new MappingJacksonHttpMessageConverter();
       
        List<HttpMessageConverter<?>> mc = restTemplate.getMessageConverters();
        
        mc.add(json);
        restTemplate.setMessageConverters(mc);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();     

        headers.add("X-FreckleToken", "Blah");
        
        // Note the body object as first parameter!
        HttpEntity<?> httpEntity = new HttpEntity<Object>(body, headers);
       
        Jira issue;
        
        try{
            
            issue = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Jira.class).getBody();
            
        }catch(HttpClientErrorException re){
            System.out.println(re.getMessage());
            System.out.println(re.getStatusCode());
            return null;
        }
        
       return issue;
       
    }
    
    
    private List<Jira> doSearchJiraList(String url){
        
       return null;
        
//        try{
//            
//            List<Jira> returnList = new ArrayList<Jira>();
//            SearchReply reply = this.restTemplate.exchange(url, HttpMethod.GET, this.httpEntity, SearchReply.class).getBody();
//         
//            List<Jira> replies = reply.getIssues();
//            
//            for(Jira issue:replies){
//                returnList.add(issue);
//            }
//            
//            return returnList;
//            
//        }catch(HttpClientErrorException re){
//            System.out.println(re.getMessage());
//            System.out.println(re.getStatusCode());
//        }
//        
//        return entries;
    }
    
   
    
//    private void init(){
//        this.restTemplate = new RestTemplate();
//        this.entries.clear();
//
//        this.json = new MappingJacksonHttpMessageConverter();
//        this.json.setSupportedMediaTypes(new ArrayList<MediaType>(){{
//            add(new MediaType("application", "json"));
//        }});
//               
//        this.mc = restTemplate.getMessageConverters();
//        
//        this.mc.add(new FormHttpMessageConverter());
//        this.mc.add(new StringHttpMessageConverter());
//        this.restTemplate.setMessageConverters(mc);
//        this.headers = new HttpHeaders();
//       
//        this.body = new LinkedMultiValueMap<String, String>();
////        String userpass = this.username+":"+this.password;
////        byte[] encodedBytes = Base64.encodeBase64(userpass.getBytes());
////        String encodedString = new String(encodedBytes);
////        this.headers.add("Authentication", "Basic "+encodedString);
//        this.httpEntity=new HttpEntity<Object>(this.body, this.headers);
//        
//    }
    
}
