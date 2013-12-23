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
public class Project implements Serializable{
    @JsonProperty("account-id")
    private Integer accountid;
    private Boolean billable;
    @JsonProperty("billable-minutes")
    private Integer billableminutes;
    @JsonProperty("color-hex")
    private String colorhex;
    @JsonProperty("created-at")
    private Date createdat;
    private Boolean enabled;
    private Integer id;
    @JsonProperty("import-id")
    private Integer importid;
    @JsonProperty("import-recipient-details")
    private String invoicerecipient;
    @JsonProperty("invoiced-minutes")
    private Integer invoicedminutes;
    private Integer minutes;
    private String name;
    @JsonProperty("project-group-id")
    private String projectgroupid;
    private Integer stepping;
    @JsonProperty("updated-at")
    private Integer updatedat;
    private Integer userid;
    @JsonProperty("budget-minutes")
    private Integer budgetminutes;
    @JsonProperty("remaining-minutes")
    private Integer remainingminutes;
    @JsonProperty("unbillable-minutes")
    private Integer unbillableminutes;
    @JsonProperty("group-name")
    private String groupname;
    @JsonProperty("cached_tags")
    private String cachedtags;

    public Project() { }
    
    public String getColorhex() {
        return colorhex;
    }

    public void setColorhex(String colorhex) {
        this.colorhex = colorhex;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public String getInvoicerecipient() {
        return invoicerecipient;
    }

    public void setInvoicerecipient(String invoicerecipient) {
        this.invoicerecipient = invoicerecipient;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public Boolean getBillable() {
        return billable;
    }

    public void setBillable(Boolean billable) {
        this.billable = billable;
    }

    public Integer getBillableminutes() {
        return billableminutes;
    }

    public void setBillableminutes(Integer billableminutes) {
        this.billableminutes = billableminutes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImportid() {
        return importid;
    }

    public void setImportid(Integer importid) {
        this.importid = importid;
    }

    public Integer getInvoicedminutes() {
        return invoicedminutes;
    }

    public void setInvoicedminutes(Integer invoicedminutes) {
        this.invoicedminutes = invoicedminutes;
    }

    public Integer getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Integer updatedat) {
        this.updatedat = updatedat;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBudgetminutes() {
        return budgetminutes;
    }

    public void setBudgetminutes(Integer budgetminutes) {
        this.budgetminutes = budgetminutes;
    }

    public Integer getRemainingminutes() {
        return remainingminutes;
    }

    public void setRemainingminutes(Integer remainingminutes) {
        this.remainingminutes = remainingminutes;
    }

    public Integer getUnbillableminutes() {
        return unbillableminutes;
    }

    public void setUnbillableminutes(Integer unbillableminutes) {
        this.unbillableminutes = unbillableminutes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectgroupid() {
        return projectgroupid;
    }

    public void setProjectgroupid(String projectgroupid) {
        this.projectgroupid = projectgroupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getCachedtags() {
        return cachedtags;
    }

    public void setCachedtags(String cachedtags) {
        this.cachedtags = cachedtags;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getStepping() {
        return stepping;
    }

    public void setStepping(Integer stepping) {
        this.stepping = stepping;
    }
}
