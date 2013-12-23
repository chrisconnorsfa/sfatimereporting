<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JIRA Summary ${jiraid}</title>
    </head>
    <body>
        <form:form id="entryScreen" name="entryScreen" commandName="entryScreen">
      <div class="panel panel-success">
            <div class="panel-heading">
              <h3 class="panel-title">JIRA Information <a target="_newwin" href="https://itjira.scottishfriendly.co.uk/browse/${jiraid}" title="Click to open JIRA ${jiraid} in new window">${jiraid}</a> - ${issue.status.name}</h3>
            </div>
         <div class="panel-body">
            <div class="row">
                <div class="col-md-3">JIRA Reference:</div>
                <div class="col-md-3">${jiraid}</div>
                <div class="col-md-3">Current Assigee:</div>
                <div class="col-md-3">${issue.assignee.displayName}</div>
            </div>
            <div class="row">
                <div class="col-md-3">Reported By:</div>
                <div class="col-md-3">${issue.reporter.displayName}</div>
                <div class="col-md-3">Project:</div>
                <div class="col-md-3">${issue.project.name}</div>
            </div>
            <div class="row">
                <div class="col-md-3">Created Date:</div>
                <div class="col-md-3"><joda:format value="${issue.creationDate}" pattern="dd/MM/yyyy"  /></div>
                <div class="col-md-3">Updated Date:</div>
                <div class="col-md-3"><joda:format value="${issue.updateDate}" pattern="dd/MM/yyyy"  /></div>
            </div>
            <div class="row">
                <div class="col-md-3" style="font-weight:bold">High Level Estimate:</div>
                <div class="col-md-3" style="font-weight:bold">${hle*7}</div>
                <div class="col-md-3" style="font-weight:bold">Time Recorded to Date:</div>
                <div class="col-md-3" style="font-weight:bold">${hourslogged}</div>
            </div>
            <div class="row">
                <div class="col-md-3">Summary:</div>
                <div class="col-md-9">${issue.summary}</div>
            </div>
            <div class="row">
                <div class="col-md-3">Description:</div>
                <div class="col-md-9">${issue.description}</div>
            </div>
         </div>
      </div>
     
    <div class="panel panel-success">
            <div class="panel-heading">
              <h3 class="panel-title">Post Time for current Assignee <a target="_newwin" href="https://itjira.scottishfriendly.co.uk/browse/${jiraid}" title="Click to open JIRA ${jiraid} in new window">${jiraid}</a> - ${issue.status.name}</h3>
            </div>
         <div class="panel-body">
            <div class="row">
                <div class="col-md-2">Date:</div>
                <div class="col-md-6">Description:</div>
                <div class="col-md-1">Hours</div>
                <div class="col-md-1">Mins</div>
                <div class="col-md-2"></div>
            </div>
            <div class="row">
                <div class="col-md-2"><form:input readonly="true" data-date-format="dd/mm/yyyy" type="text" path="date" id="date" class="datepicker"/></span></div>
                <div class="col-md-6"><form:input path="description"  style="width:100%;height:33px"/></div>
                <div class="col-md-1"><form:input path="hours" style="width:90px;height:33px"/></div>
                <div class="col-md-1"><form:input path="mins" style="width:90px;height:33px" /></div>
                <div class="col-md-2"><button class="btn btn-success" type="submit" formaction="create/${jiraid}/${projectkey}" value="Create">Create</button></div>
            </div>
        </div>
    </div>
        
    <div class="panel panel-success">
            <div class="panel-heading">
              <h3 class="panel-title">Time Recorded for <a target="_newwin" href="https://itjira.scottishfriendly.co.uk/browse/${jiraid}" title="Click to open JIRA ${jiraid} in new window">${jiraid}</a></h3>
            </div>
            <div class="panel-body">
                    <table width="100%" class="table dataTable">
                            
                            <thead>
                                <tr role="row">
                                    <th class="sorting" style="font-weight:bold">User</th>
                                    <th class="sorting" style="font-weight:bold">Item Tags</th>
                                    <th class="sorting" style="font-weight:bold">Date</th>
                                    <th class="sorting" style="font-weight:bold">Hours</th>
                                    <th></th>
                                </tr>
                            </thead>
                        <c:forEach items="${entries}" var="item">
                            <tr>
                                    <td>${item.username}</td>
                                    <td>${item.tagstring}</td>
                                    <td><fmt:formatDate value="${item.date}" pattern="EEE dd/MM/yyyy" /></td>
                                    <td class="itemtotal">${item.hours}</td>
                                    <td><button disabled="true" value="Delete" class="btn btn-success" type="submit" formaction="delete/${jiraid}/${item.id}">Delete</button></td>
                                
                            </tr>
                        </c:forEach>
                            </table>
               
          </div>
        </div>  
    </form:form>
    </body>
</html>
