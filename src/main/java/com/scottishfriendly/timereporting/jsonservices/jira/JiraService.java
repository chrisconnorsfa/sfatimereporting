/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.jsonservices.jira;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueField;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;
import com.scottishfriendly.timereporting.model.JiraIssue;
import com.scottishfriendly.timereporting.utils.GeneralUtils;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpHeaders;

import org.springframework.http.converter.HttpMessageConverter;

import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Service;

import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.http.HttpEntity;


/**
 *
 * @author chriconn
 */
@Service
public class JiraService implements IJiraService{
    
    private RestTemplate restTemplate;
    private MappingJacksonHttpMessageConverter json;
    private List<HttpMessageConverter<?>> mc;
    private HttpHeaders headers=new HttpHeaders();
    private String username = "ChriConn";
    private String password = "java1234";
    private List entries = new ArrayList();
    private HttpEntity<?> httpEntity ;
    private MultiValueMap<String, String> body;
    //private final String BASE_URL = "https://jira.atlassian.com/rest/api/2/";
    private final String BASE_URL = "https://itjira.scottishfriendly.co.uk/";
    
    @Override
    public List<Jira> getAllJIRAs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Issue getJIRAById(String id) {
        return doSearchJIRA(id);
    }

    @Override
    public Double getHLE(Issue issue){
        IssueField hleField=issue.getField("customfield_10000");
        if(hleField==null || hleField.getValue()==null){
            return new Double(0);
        }
        return (Double)hleField.getValue();
    }
    
    private Issue doSearchJIRA(String key){
         
    try {
                    //GeneralUtils.disableCertificateValidation();
                    final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
                    final URI jiraServerUri = new URI(BASE_URL); 
                    final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, "ChriConn", "java1234");
                    try {
                            final IssueRestClient issueRestClient = restClient.getIssueClient();
                            final Promise<Issue> promise = issueRestClient.getIssue(key);
                                       
                            try {
                                return promise.get();
                           
                            } catch (InterruptedException ex) {
                                Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ExecutionException ex) {
                                Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                return null;
                    } finally {
                        try {
                            restClient.close();
                            
                        } catch (IOException ex) {
                            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
                            return null;
                        }
                    }
            } catch (URISyntaxException ex) {
            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
    }
   
    
      
    private String buildQuery(String assignedto, Date startdate, Date enddate, Integer status, DateType datetype ){
        
        String retval = "";
        
        if(!assignedto.equalsIgnoreCase("all")){
            retval+="assignee='"+assignedto+"'";
        }
        
        String statusstring=status.toString();
        
        if(!statusstring.equalsIgnoreCase("0")){
            retval+="&status="+status;
        }
        
        String datestring=datetype.toString();
        
        if(!datestring.equalsIgnoreCase("all")){
            retval+="&"+datetype+">"+GeneralUtils.reverseDate(startdate)+"&"+datetype+"<"+GeneralUtils.reverseDate(enddate);
            // Less than and equals to here...
        }
        
//        try{
//            retval = URLEncoder.encode(retval, "ISO-8859-1");
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
//            return null;
//        }
        
        if(retval.startsWith("&")){
            retval=retval.substring(1);
        }
        
        return retval;
         
    }
    

    
   
//    public List<Jira> searchJIRAs(String assignedto, Date startdate, Date enddate, Status status, DateType datetype) {
//         
//        init();
//              
//        String queryString = buildQuery(assignedto, startdate, enddate, status, datetype);
//        
//        if(queryString==null || queryString.equals("")){
//            return null;
//        }
//        
//        String url = BASE_URL +"search?jql="+queryString;
//        String turl = "https://itjira.scottishfriendly.co.uk/rest/api/2/search?jql=assignee%3D%27Brian+Robertson%27";
//        try{
//            
//            List<Jira> returnList = new ArrayList<Jira>();
//        //    SearchReply reply = this.restTemplate.exchange(url, HttpMethod.GET, this.httpEntity, SearchReply.class).getBody();
//            ResponseEntity response = this.restTemplate.exchange(turl, HttpMethod.GET, this.httpEntity, SearchReply.class);
//           
//            SearchReply reply = (SearchReply) response.getBody();
//         
////            List<Jira> replies = reply.getIssues();
////            
////            for(Jira issue:replies){
////                returnList.add(issue);
////            }
////            
////            return returnList;
////            
//        }catch(RestClientException re){
//            System.out.println(re.getMessage());
//            
//            //System.out.println(re.getStatusCode());
//        }
        
//        return entries;
//    }

   
    @Override
    public List<Issue> searchJIRAs(String assignedto, Date startdate, Date enddate, Integer status, DateType datetype) {
               try {
                    //GeneralUtils.disableCertificateValidation();
                    final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
                    final URI jiraServerUri = new URI(BASE_URL); 
                    final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, "ChriConn", "java1234");
                    try {
                            final IssueRestClient issueClient = restClient.getIssueClient();
                            final SearchRestClient searchClient = restClient.getSearchClient();

                            Promise<SearchResult> promise = searchClient.searchJql(buildQuery(assignedto, startdate, enddate, status, datetype));
                        try {
                            SearchResult searchResult = promise.get();
                            Iterable<Issue> iterable = searchResult.getIssues();
                            Iterator iterator = iterable.iterator();
                            return IteratorUtils.toList(iterator);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ExecutionException ex) {
                            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            return null;
                    } finally {
                        try {
                            restClient.close();
                            
                        } catch (IOException ex) {
                            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
                            return null;
                        }
                    }
            } catch (URISyntaxException ex) {
            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
    
    
    @Override
    public List<Issue> getCurrentActiveForUser(String username) {
               try {
                    //GeneralUtils.disableCertificateValidation();
                    final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
                    final URI jiraServerUri = new URI(BASE_URL); 
                    final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, "ChriConn", "java1234");
                    try {
                            final IssueRestClient issueClient = restClient.getIssueClient();
                            final SearchRestClient searchClient = restClient.getSearchClient();

                            Promise<SearchResult> promise = searchClient.searchJql("assignee='"+username+"'&status!=Closed");
                        try {
                            SearchResult searchResult = promise.get();
                            Iterable<Issue> iterable = searchResult.getIssues();
                            Iterator iterator = iterable.iterator();
                            return IteratorUtils.toList(iterator);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ExecutionException ex) {
                            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            return null;
                    } finally {
                        try {
                            restClient.close();
                            
                        } catch (IOException ex) {
                            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
                            return null;
                        }
                    }
            } catch (URISyntaxException ex) {
            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
    
    @Override
    public List<Issue> getWatchedActiveForUser(String username) {
               try {
                    //GeneralUtils.disableCertificateValidation();
                    final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
                    final URI jiraServerUri = new URI(BASE_URL); 
                    final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, "ChriConn", "java1234");
                    try {
                            final IssueRestClient issueClient = restClient.getIssueClient();
                            final SearchRestClient searchClient = restClient.getSearchClient();

                            Promise<SearchResult> promise = searchClient.searchJql("watcher='"+username+"'&status!=Closed");
                        try {
                            SearchResult searchResult = promise.get();
                            Iterable<Issue> iterable = searchResult.getIssues();
                            Iterator iterator = iterable.iterator();
                            return IteratorUtils.toList(iterator);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ExecutionException ex) {
                            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            return null;
                    } finally {
                        try {
                            restClient.close();
                            
                        } catch (IOException ex) {
                            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
                            return null;
                        }
                    }
            } catch (URISyntaxException ex) {
            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
    
    @Override 
    public List<Issue> searchJIRAs(String query) {
               try {
                    //GeneralUtils.disableCertificateValidation();
                    final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
                    final URI jiraServerUri = new URI(BASE_URL); 
                    final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, "ChriConn", "java1234");
                    try {
                            final IssueRestClient issueClient = restClient.getIssueClient();
                            final SearchRestClient searchClient = restClient.getSearchClient();
                            
                            final String querystring = "description ~ "+query+" OR summary ~"+query;
                            Promise<SearchResult> promise = searchClient.searchJql(querystring);
                            
                        try {
                            SearchResult searchResult = promise.get();
                            Iterable<Issue> iterable = searchResult.getIssues();
                            
                            Iterator iterator = iterable.iterator();
                                                        
                            return IteratorUtils.toList(iterator);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ExecutionException ex) {
                            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            return null;
                    } finally {
                        try {
                            restClient.close();
                            
                        } catch (IOException ex) {
                            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
                            return null;
                        }
                    }
            } catch (URISyntaxException ex) {
            Logger.getLogger(JiraService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
    
    
    
    
    
//    private void init2(){
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
//        this.headers.setContentType(MediaType.APPLICATION_JSON);
//       
//        this.body = new LinkedMultiValueMap<String, String>();
//        String userpass = this.username+":"+this.password;
//        byte[] encodedBytes = Base64.encodeBase64(userpass.getBytes());
//        String encodedString = new String(encodedBytes);
//        this.headers.add("Authorization", "Basic "+encodedString);
//        this.httpEntity=new HttpEntity<Object>(this.body, this.headers);
//        GeneralUtils.disableCertificateValidation();
//    }
//    
    }
//}
