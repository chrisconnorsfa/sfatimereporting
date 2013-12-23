<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search JIRAs</title>
    </head>
    <body>
      
        
        <div class="col-sm-12">
          <div class="panel panel-success">
            <div class="panel-heading">
              <h3 class="panel-title">Search JIRAs</h3>
            </div>
            <div class="panel-body">
               <form:form id="searchForm" name="searchForm"  commandName="searchScreen" >
               <fieldset>
                    <legend>Search</legend>
                    <label class="control-label" for="user">Summary / Description Text</label>
                    <form:input path="query" id="query" /> (For multiple words - Please enter exact phrase in quotes eg "Talisman Batch Jobs")
                    
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
                                    <th class="sorting" style="font-weight:bold">Status</th>
                                    <th class="sorting" style="font-weight:bold">Assignee</th>
                                    <th class="sorting" style="font-weight:bold">Reporter</th>
                                    <th class="sorting" style="font-weight:bold">Created</th>
                                    <th class="sorting" style="font-weight:bold">Updated</th>
                                    <th class="sorting" style="font-weight:bold">JIRA Ref</th>
                                    <th style="font-weight:bold">HLE</th>
                                    <th class="sorting" style="font-weight:bold">Details</th>
                                </tr>
                            </thead>
                        <c:forEach items="${results}" var="item">
                            <tr>
                                <td>${item.status.name}</td>
                                <td>${item.assignee.displayName}</td>
                                <td>${item.reporter.displayName}</td>
                                <td><joda:format value="${item.creationDate}" pattern="dd/MM/yyyy"  /></td>
                                <td><joda:format value="${item.updateDate}" pattern="dd/MM/yyyy"  /></td>
                                <td><spring:url value="/jirasummary/${item.key}" var="summaryUrl"/><a href="${summaryUrl}">${item.key}</a></td>
                                <td>${item.getField("customfield_10000").getValue()*7}</td>
                                <td><p>${item.summary}</p><p>${item.description}</p></td>
                                
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
