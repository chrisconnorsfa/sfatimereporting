/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author chriconn
 */
public class SecurityUtils {

/**
* Returns the domain User object for the currently logged in user, or null
* if no User is logged in.
* 
* @return User object for the currently logged in user, or null if no User
*         is logged in.
*/
public static User getCurrentUser() {

   return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  
}


/**
 * Utility method to determine if the current user is logged in /
 * authenticated.
 * <p>
 * Equivalent of calling:
 * <p>
 * <code>getCurrentUser() != null</code>
 * 
 * @return if user is logged in
 */
public static boolean isLoggedIn() {
    return getCurrentUser() != null;
}    

}
