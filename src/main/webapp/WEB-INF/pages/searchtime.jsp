<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Filter Projects</title>
    </head>
    <body>
      
        
        <div class="col-sm-12">
          <div class="panel panel-success">
            <div class="panel-heading">
              <h3 class="panel-title">Query Criteria</h3>
            </div>
            <div class="panel-body">
               <form:form id="searchForm" name="searchForm"  commandName="searchScreen" >
                 <fieldset>
                    <legend>By User / Project</legend>
                    <label class="control-label" for="user">User</label>
                    <form:select class="selectpicker" path="userid" id="user">
                        <form:option value="0" label="--- All ---"/>
                        <form:options items="${usernames}" />
                    </form:select>
           
                    <label class="control-label" for="project">Project</label>
                    <form:select class="selectpicker" path="projectid" id="project">
                        <form:option value="0" label="--- All ---"/>
                        <form:options items="${projectnames}" />
                    </form:select>
                    
                </fieldset>
                <fieldset>
                    <legend>By Date Performed</legend>           
                    <label class="control-label" for="start">Start</label> 
                        <form:input readonly="true" data-date-format="dd/mm/yyyy" type="text" path="startdate" id="start" class="datepicker"/>
                        <span class="add-on"><i class="icon-calendar"></i></span>
                    </span>
                    <label class="control-label" for="end">End</label> 
                        <form:input readonly="true" data-date-format="dd/mm/yyyy" type="text" path="enddate" id="end" class="datepicker"/>
                        <span class="add-on"><i class="icon-th"></i></span>
                    </span>
                </fieldset>
                <p class="text-right">        
                    <button class="btn btn-success" type="submit" value="Search">Search</button>
                </p>
                </form:form> 
            </div>
          </div>
            
          <div class="panel panel-success">
            <div class="panel-heading">
              <h3 class="panel-title">Search Results (Max number of records returned is 1000)</h3>
            </div>
            <div class="panel-body">
                    <table width="100%" class="table dataTable">
                        
                            <thead>
                                <tr role="row">
                                    <th class="sorting" style="font-weight:bold">User</th>
                                    <th class="sorting" style="font-weight:bold">Project</th>
                                    <th class="sorting" style="font-weight:bold">Tags</th>
                                    <th class="sorting" style="font-weight:bold">Date</th>
                                    <th class="sorting" style="font-weight:bold">Hours</th>
                                </tr>
                            </thead>
                        <c:forEach items="${entries}" var="item">
                            <tr>
                                <td>${item.username}</td>
                                <td>${item.projectname}</td>
                                <td>${item.tagstring}</td>
                                <td><fmt:formatDate value="${item.date}" pattern="EEE dd/MM/yyyy" /></td>
                                <td class="itemtotal">${item.hours}</td>
                            </tr>
                        </c:forEach>
                            </table>
                <div style="text-align: right">
                    <c:if test="${searchScreen.total>0}">
                        <h4>Total Hours <c:out value="${searchScreen.total}" /></h4></td> 
                    </c:if>
                </div>
          </div>
        </div>
        
       
            
       
       
     
            
           
        
    </body>
</html>
