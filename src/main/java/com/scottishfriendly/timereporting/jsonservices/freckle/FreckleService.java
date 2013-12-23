package com.scottishfriendly.timereporting.jsonservices.freckle;

import com.scottishfriendly.timereporting.utils.GeneralUtils;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author chriconn
 */

@Service
public class FreckleService implements IFreckleService{
    private RestTemplate restTemplate;
    private User user;
    private String token = "m6sxk96nhg7w5eois160csas2fkiuv9";
    private Integer projectquerytotal = 0;
    private Map<Integer,String> allusernames=new  HashMap<Integer, String>();
    private Map<Integer,String> allprojectnames=new  HashMap<Integer, String>();
    private Double totalhours = new Double(0);
    
    public FreckleService() { }

    @Override
    public List<User> getAllUsers() {
        this.allusernames.clear();
        
        this.restTemplate=new RestTemplate();
        
        List userlist = new ArrayList();
        
        MappingJacksonHttpMessageConverter json = new MappingJacksonHttpMessageConverter();
              
        String url = "https://scottishfriendly.letsfreckle.com/api/users";
        
        List<HttpMessageConverter<?>> mc = restTemplate.getMessageConverters();
        
        mc.add(new FormHttpMessageConverter());
        mc.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(mc);
        HttpHeaders headers = new HttpHeaders();
       
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();     

        headers.add("X-FreckleToken", token);
        
        // Note the body object as first parameter!
        HttpEntity<?> httpEntity = new HttpEntity<Object>(body, headers);
        try{
            Reply[] replies = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Reply[].class).getBody();
            
            for(Reply reply : replies){
                User user =  reply.getUser();
                userlist.add(user);
                this.allusernames.put(Integer.parseInt(user.getId()),user.getFirst_name()+" "+user.getLast_name());
            }
  
        }catch(HttpClientErrorException re){
            System.out.println(re.getMessage());
            System.out.println(re.getStatusCode());
        }
        
        return userlist;
    }
    
    public static void main(String[] args) throws IOException {
        FreckleService controller = new FreckleService();
        for(User user:controller.getAllUsers()){
            System.out.println(user.getLast_name());
        }
        
        System.out.println("Projects size="+controller.getAllProjects().size());
    }

    public Map<Integer,String> getAllusernames() {
        getAllUsers();
        return GeneralUtils.sortByValue(this.allusernames);
    }

    public Map<Integer, String> getAllprojectnames() {
        getAllProjects();
        return GeneralUtils.sortByValue(allprojectnames);
    }

    @Override
    public List<Project> getAllProjects() {
        this.projectquerytotal=0;
        this.allprojectnames.clear();
        
        this.restTemplate=new RestTemplate();
        
        List projectlist = new ArrayList();
        
        MappingJacksonHttpMessageConverter json = new MappingJacksonHttpMessageConverter();
              
        String url = "https://scottishfriendly.letsfreckle.com/api/projects";
        
        List<HttpMessageConverter<?>> mc = restTemplate.getMessageConverters();
        
        mc.add(new FormHttpMessageConverter());
        mc.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(mc);
        HttpHeaders headers = new HttpHeaders();
       
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();     

        headers.add("X-FreckleToken", token);
        
        // Note the body object as first parameter!
        HttpEntity<?> httpEntity = new HttpEntity<Object>(body, headers);
        try{
            
            Reply[] replies = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Reply[].class).getBody();
            
            for(Reply reply : replies){
                Project project =  reply.getProject();
               
                projectlist.add(project);
                this.allprojectnames.put(project.getId(),project.getName());
                this.projectquerytotal+=project.getMinutes();
            }
  
        }catch(HttpClientErrorException re){
            System.out.println(re.getMessage());
            System.out.println(re.getStatusCode());
        }
        
        return projectlist;
    }

    @Override
    public List<Entry> getEntriesByUserId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override 
    public List<Entry> getEntriesByTags(List<Tag> tags) {
        this.totalhours=new Double(0);
        this.getAllUsers();
        this.restTemplate=new RestTemplate();
        
        List entries = new ArrayList();
        
        MappingJacksonHttpMessageConverter json = new MappingJacksonHttpMessageConverter();
              
        String url = "https://scottishfriendly.letsfreckle.com/api/entries.json?search[tags]="+buildTagString(tags)+"&per_page=1000";
        
        List<HttpMessageConverter<?>> mc = restTemplate.getMessageConverters();
        
        mc.add(new FormHttpMessageConverter());
        mc.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(mc);
        HttpHeaders headers = new HttpHeaders();
       
       
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();     

        headers.add("X-FreckleToken", token);
        
        // Note the body object as first parameter!
        HttpEntity<?> httpEntity = new HttpEntity<Object>(body, headers);
        try{
            
            Reply[] replies = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Reply[].class).getBody();
            
            for(Reply reply : replies){
                Entry entry =  reply.getEntry();
                entry = updateEntry(entry);
                entries.add(entry);
            }
  
        }catch(HttpClientErrorException re){
            System.out.println(re.getMessage());
            System.out.println(re.getStatusCode());
        }
        
        return entries;
    }

    @Override
    public List<Entry> getEntries(Integer id) {
        this.restTemplate=new RestTemplate();
        
        List entries = new ArrayList();
        
        MappingJacksonHttpMessageConverter json = new MappingJacksonHttpMessageConverter();
              
        String url = "https://scottishfriendly.letsfreckle.com/api/?search["+id+"]"+"&per_page=1000";
        
        List<HttpMessageConverter<?>> mc = restTemplate.getMessageConverters();
        
        mc.add(new FormHttpMessageConverter());
        mc.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(mc);
        HttpHeaders headers = new HttpHeaders();
       
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();     

        headers.add("X-FreckleToken", token);
        
        // Note the body object as first parameter!
        HttpEntity<?> httpEntity = new HttpEntity<Object>(body, headers);
        try{
            
            Reply[] replies = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Reply[].class).getBody();
            
            for(Reply reply : replies){
                Entry entry =  reply.getEntry();
                entries.add(entry);
            }
  
        }catch(HttpClientErrorException re){
            System.out.println(re.getMessage());
            System.out.println(re.getStatusCode());
        }
        
        return entries;
    }
    
    private String buildTagString(List<Tag> tags){
       
        String taglist = "";
        for(Tag tag:tags){
            taglist+=", "+tag.getName();
        }
       
       return taglist.replaceFirst(", ", "");
    }
    
    private String buildSearch(Integer userid, Integer projectid, Date from, Date to){
        
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        
        String fromString=dateformat.format( from );
        String toString=dateformat.format( to );
        
        String url = "";
        
        if(userid.equals(0) && projectid.equals(0)){
            return "https://scottishfriendly.letsfreckle.com/api/entries.json?search[from]="+fromString+"&search[to]="+toString;
        }
        
        if(userid.equals(0)){
            return "https://scottishfriendly.letsfreckle.com/api/entries.json?search[from]="+fromString+"&search[to]="+toString+"&search[projects]="+projectid;
        }
        
        if(projectid.equals(0)){
            return "https://scottishfriendly.letsfreckle.com/api/entries.json?search[people]="+userid+"&search[from]="+fromString+"&search[to]="+toString;
        }
        
        return "https://scottishfriendly.letsfreckle.com/api/entries.json?search[people]="+userid+"&search[from]="+fromString+"&search[to]="+toString+"&search[projects]="+projectid;
        
    }

    @Override
    public boolean create(PostEntry entry) {
       this.restTemplate=new RestTemplate();
        
        List entries = new ArrayList();
        
        this.projectquerytotal=0;
        this.totalhours=0.0;
        entries.clear();
        
        MappingJacksonHttpMessageConverter json = new MappingJacksonHttpMessageConverter();
       
        String url= "https://scottishfriendly.letsfreckle.com/api/entries.json";
        
        List<HttpMessageConverter<?>> mc = restTemplate.getMessageConverters();
        
        mc.add(new FormHttpMessageConverter());
        mc.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(mc);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
       
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();     

        headers.add("X-FreckleToken", token);
        body.add("entry",entry);
        // Note the body object as first parameter!
        HttpEntity<?> httpEntity = new HttpEntity<Object>(body, headers);
        
        try{
           try {
               String jsonString = new ObjectMapper().writeValueAsString(body);
           } catch (IOException ex) {
               Logger.getLogger(FreckleService.class.getName()).log(Level.SEVERE, null, ex);
          
           }
            
            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Void.class);
        }catch(RestClientException re){
            re.printStackTrace();
            return false;
        }
        
        return true;
    }

    
    
    @Override
    public boolean delete(Long id) {
   
        this.restTemplate=new RestTemplate();
        
        List entries = new ArrayList();
        
        this.projectquerytotal=0;
        this.totalhours=0.0;
        entries.clear();
        
        MappingJacksonHttpMessageConverter json = new MappingJacksonHttpMessageConverter();
       
        String url= "https://scottishfriendly.letsfreckle.com/api/entries/"+id.toString()+".json";
        
        List<HttpMessageConverter<?>> mc = restTemplate.getMessageConverters();
        
        mc.add(new FormHttpMessageConverter());
        mc.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(mc);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
       
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();     

        headers.add("X-FreckleToken", token);
        
        // Note the body object as first parameter!
        HttpEntity<?> httpEntity = new HttpEntity<Object>(body, headers);
        
        try{
            ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Void.class);
        }catch(RestClientException re){
            re.printStackTrace();
            return false;
        }
        
        
        return true;
    }
    
    
    @Override
    public List<Entry> getEntries(Integer userid, Integer projectid, Date from, Date to) {
        this.restTemplate=new RestTemplate();
        
        List entries = new ArrayList();
        
        this.projectquerytotal=0;
        this.totalhours=0.0;
        entries.clear();
        
        MappingJacksonHttpMessageConverter json = new MappingJacksonHttpMessageConverter();
       
        String url= buildSearch(userid, projectid, from, to)+"&per_page=1000";
        
        List<HttpMessageConverter<?>> mc = restTemplate.getMessageConverters();
        
        mc.add(new FormHttpMessageConverter());
        mc.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(mc);
        HttpHeaders headers = new HttpHeaders();
       
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();     

        headers.add("X-FreckleToken", token);
        
        // Note the body object as first parameter!
        HttpEntity<?> httpEntity = new HttpEntity<Object>(body, headers);
        try{
            
            Reply[] replies = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Reply[].class).getBody();
            
            for(Reply reply : replies){
                Entry entry =  reply.getEntry();
                entry.setUsername(this.allusernames.get(entry.getUser_id()));
                entry.setProjectname(this.allprojectnames.get(entry.getProject_id()));
                Double minsDouble = entry.getMinutes().doubleValue();
                entry.setHours(minsDouble/60);
                List<Tag> tags = entry.getTags();
                entry.setTagstring(buildTagString(tags));
                this.totalhours+=entry.getHours();
                this.projectquerytotal+=entry.getMinutes();
                entries.add(entry);
            }
            
        }catch(HttpClientErrorException re){
            System.out.println(re.getMessage());
            System.out.println(re.getStatusCode());
        }
        
        return entries;
    }

    private Entry updateEntry(Entry entry){
                entry.setUsername(this.allusernames.get(entry.getUser_id()));
                entry.setProjectname(this.allprojectnames.get(entry.getProject_id()));
                Double minsDouble = entry.getMinutes().doubleValue();
                entry.setHours(minsDouble/60);
                List<Tag> tags = entry.getTags();
                entry.setTagstring(buildTagString(tags));
                this.totalhours+=entry.getHours();
                this.projectquerytotal+=entry.getMinutes();
                return entry;
    }
    
    @Override
    public Project getProjectByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUserByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Project getProjectById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUserById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getProjectquerytotal() {
        return projectquerytotal;
    }

    public void setProjectquerytotal(Integer projectquerytotal) {
        this.projectquerytotal = projectquerytotal;
    }

    public Double getTotalhours() {
        return totalhours;
    }

    public void setTotalhours(Double totalhours) {
        this.totalhours = totalhours;
    }
    
    
}
