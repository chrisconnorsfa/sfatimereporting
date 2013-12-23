/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author chriconn
 */



public class MyUserDetails extends User {

   // extra instance variables
   final String fullname;
   final String email;
   final String title;

   public MyUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
         boolean credentialsNonExpired, boolean accountNonLocked,
         Collection<GrantedAuthority> authorities, String fullname,
         String email, String title) {

      super(username, password, enabled, accountNonExpired, credentialsNonExpired,
            accountNonLocked, authorities);

      this.fullname = fullname;
      this.email = email;
      this.title = title;
   }

}
