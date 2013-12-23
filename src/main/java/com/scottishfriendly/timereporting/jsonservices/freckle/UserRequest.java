package com.scottishfriendly.timereporting.jsonservices.freckle;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author chriconn
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserRequest {
    public static final String key="m6sxk96nhg7w5eois160csas2fkiuv9";
    private User user;

    public UserRequest() { }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
