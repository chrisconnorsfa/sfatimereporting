<%-- 
    Document   : index
    Created on : 27-Aug-2013, 09:34:45
    Author     : chriconn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Scottish Friendly Time Reporting</title>
    </head>
    <body>
        <div class="jumbotron">
            <div class="container">
              <h2>Welcome ${currentusername} to the Scottish Friendly Time Reporting Application</h2>
              <div style="text-align: right"><img src="/frontend/resources/img/logo.gif" /></div>
              <p>This application allows you to query recorded information using JIRA references, User Information and Date Ranges using the links at the top of the screen.</p>
             
              
              <p>Feedback on features / approaches around this more than welcome - <a href="mailto:chris.connor@scottishfriendly.co.uk">Email Me</a>
            </div>
        </div>
              
        <div class="panel panel-success">
            <div class="panel-heading">
              <h3 class="panel-title">My Current JIRAs</h3>
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
                        <c:forEach items="${mycurrentjiras}" var="item">
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
              
        <div class="panel panel-success">
            <div class="panel-heading">
              <h3 class="panel-title">My Current Watched JIRAs</h3>
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
                        <c:forEach items="${mywatchedcurrentjiras}" var="item">
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
