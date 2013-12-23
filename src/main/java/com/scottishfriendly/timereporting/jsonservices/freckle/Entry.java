package com.scottishfriendly.timereporting.jsonservices.freckle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author chriconn
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entry implements Serializable{
    private Date created_at;
    private Boolean billable;
    private List<Tag> tags;
    private Integer minutes;
    private Double hours;
    private Date updated_at;
    private Date recently_updated_at;
    private Integer project_id;
    private String projectname;
    private Integer import_id;
    private String url;
    private Long id;
    private Integer time_to;
    private Integer user_id;
    private String username;
    private String formatted_description;
    private String description_text;
    private String money_status;
    private Date date;
    private String billable_status;
    private String invoiced_at;
    private String tagstring="";
    
    public Entry() {
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Boolean getBillable() {
        return billable;
    }

    public void setBillable(Boolean billable) {
        this.billable = billable;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getRecently_updated_at() {
        return recently_updated_at;
    }

    public void setRecently_updated_at(Date recently_updated_at) {
        this.recently_updated_at = recently_updated_at;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public Integer getImport_id() {
        return import_id;
    }

    public void setImport_id(Integer import_id) {
        this.import_id = import_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTime_to() {
        return time_to;
    }

    public void setTime_to(Integer time_to) {
        this.time_to = time_to;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFormatted_description() {
        return formatted_description;
    }

    public void setFormatted_description(String formatted_description) {
        this.formatted_description = formatted_description;
    }

    public String getDescription_text() {
        return description_text;
    }

    public void setDescription_text(String description_text) {
        this.description_text = description_text;
    }

    public String getMoney_status() {
        return money_status;
    }

    public void setMoney_status(String money_status) {
        this.money_status = money_status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBillable_status() {
        return billable_status;
    }

    public void setBillable_status(String billable_status) {
        this.billable_status = billable_status;
    }

    public String getInvoiced_at() {
        return invoiced_at;
    }

    public void setInvoiced_at(String invoiced_at) {
        this.invoiced_at = invoiced_at;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getTagstring() {
        return tagstring;
    }

    public void setTagstring(String tagstring) {
        this.tagstring = tagstring;
    }
    
    
    
    
}
