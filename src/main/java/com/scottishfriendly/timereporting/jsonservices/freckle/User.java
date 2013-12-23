package com.scottishfriendly.timereporting.jsonservices.freckle;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author chriconn
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable{
   // private String name;
    private String email;
    private String permissions;
    private String id;
    private String last_name;
    private String current_state;
    private String time_format;
    private String login;
    private String first_name;
   // private String id;

    public User() { }
    
    public User(String email, String permissions, String id, String last_name, String first_name, String login, String current_state){
        this.email=email;
        this.permissions=permissions;
        this.id=id;
        this.last_name=last_name;
        this.first_name=first_name;
        this.login=login;
        this.current_state=current_state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCurrent_state() {
        return current_state;
    }

    public void setCurrent_state(String current_state) {
        this.current_state = current_state;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
}
