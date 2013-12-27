package com.scottishfriendly.timereporting.validation;

/**
 * Copyright (c) 2012 Scottish Friendly Assurance. All Rights Reserved.
 */


import com.scottishfriendly.timereporting.utils.DateUtils;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * @author Iain Rawson
 */
public abstract class BaseValidator implements Validator {

    protected static final String ERROR_REQUIRE = "error.require.";
    protected static final String ERROR_FORMAT = "error.format.";

    protected void require(Object property, Errors errors, String field) {
        checkForNull(property, errors, field, ERROR_REQUIRE + field);
    }

    protected void require(String property, Errors errors, String field) {
        checkForEmpty(property, errors, field, ERROR_REQUIRE + field);
    }

    protected void require(Collection<?> collection, Errors errors, String field) {
        if (collection == null || collection.isEmpty()) {
            errors.rejectValue(field, ERROR_REQUIRE + field);
        }
    }

    private void checkForNull(Object property, Errors errors, String field, String errorCode) {
        if (property == null)  {
            errors.rejectValue(field, errorCode);
        }
    }

    private void checkForEmpty(String property, Errors errors, String field, String errorCode) {
        if (StringUtils.isEmpty(property)) {
            errors.rejectValue(field, errorCode);
        }
    }

    protected void checkLength(String property, int min, int max, Errors errors, String field) {
        int length = property == null ? 0 : property.length();

        if (length < min || length > max) {
            errors.rejectValue(field, "error.length." + field);
        }
    }

    protected void matches(String property, Pattern pattern, Errors errors, String field) {
        if (!pattern.matcher(property).matches()) {
            errors.rejectValue(field, ERROR_FORMAT + field);
        }
    }

    protected void equals(String property, String confirm, Errors errors, String field) {
        if (!property.equals(confirm)) {
            errors.rejectValue(field, "error.match." + field);
        }
    }

    protected  void validDateFormat(String date, Errors errors, String field) {

        Date parsed = DateUtils.parseDate(date);
        if (parsed == null) {
            // If we're here the date has not parsed correctly.
            errors.rejectValue(field, ERROR_FORMAT + field);
        }
    }
}
