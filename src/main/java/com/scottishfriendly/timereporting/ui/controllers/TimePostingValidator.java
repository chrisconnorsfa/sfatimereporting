/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scottishfriendly.timereporting.ui.controllers;

import com.scottishfriendly.timereporting.validation.BaseValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author chriconn
 */
@Component
public class TimePostingValidator extends BaseValidator implements Validator{
    
    @Override
    public boolean supports(Class<?> clazz) {
        return JiraSummaryScreen.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
       JiraSummaryScreen screen = (JiraSummaryScreen) o;
        if(screen.isDateEmpty()){
            errors.reject(ERROR_REQUIRE+".date");
        }
        if(screen.isDescriptionEmpty()){
            errors.reject(ERROR_REQUIRE+".description");
        }
        if(screen.isHoursEmpty()){
            errors.reject(ERROR_REQUIRE+".hours");
        }
        if(screen.isMinsEmpty()){
            errors.reject(ERROR_REQUIRE+".mins");
        }
       
    }
    
}
