package com.scottishfriendly.timereporting.jsonservices.freckle;

import java.io.Serializable;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author chriconn
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag implements Serializable{
   
    private String name;
    private String billable;
    private String id;

    public Tag() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBillable() {
        return billable;
    }

    public void setBillable(String billable) {
        this.billable = billable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
   
}
