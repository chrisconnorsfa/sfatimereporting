<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.scottishfriendly.co.uk/opensymphony/sitemesh/decorator" prefix="decorator-ext"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title><decorator:title default="INTRANET" /></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        
        <%-- Determine resource usage --%>
        <c:set var="usingTable">
            <decorator:getProperty property="meta.usingTable" default="false" />
        </c:set>
        <c:set var="usingDatePicker">
            <decorator:getProperty property="meta.usingDatePicker" default="false" />
        </c:set>

        <!-- Stylesheets -->
        <link href="<spring:url value="/resources/vendor/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" type="text/css">
        <link href="<spring:url value="/resources/vendor/bootstrap/css/bootstrap-theme.min.css" />" rel="stylesheet" type="text/css">
        <link href="<spring:url value="/resources/vendor/jquery/datepicker/css/datepicker.css" />" rel="stylesheet" type="text/css">
        <link href="<spring:url value="/resources/vendor/jquery/dropdown/bootstrap-select.min.css" />" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="<spring:url value="/resources/vendor/datatables/css/DT_bootstrap.css" />" type="text/css"/>
		
        <link href="<spring:url value="/resources/css/main.css" />" rel="stylesheet" type="text/css">

        <!-- Favicons -->
        <link rel="shortcut icon" href="<spring:url value="/resources/img/favicon.ico" />"/>

        <decorator:head />
        
        <!-- External javascript files -->
        <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
              
       
    </head>
    <body >
    
        <div id="spinneroverlay" class="spinneroverlay" style="display:none;"></div>
        <div id="spinnerbox" class="spinnerbox" style="display:none;">
            <img id="spinnerimg" class="spinnerimg" src="<spring:url value="/resources/img/ajax-loader.gif" />" alt="Loading" height="32" width="32"/>
            <br/>
            <span class="spinnertext"><spring:message code="spinner.text" /></span>
        </div>
        
       
      
	       	<!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/frontend/index.html">Scottish Friendly Time Reporting</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
           
            <li class="${active==2?'active':''}"><a href="/frontend/searchjirasbydate.html">Filter JIRAs By Assigned to</a></li>
            <li class="${active==3?'active':''}"><a href="/frontend/searchjirasbytext.html">Search JIRAs</a></li>
            <li class="${active==1?'active':''}"><a href="/frontend/searchtime.html">Filter Freckle Time</a></li>
            <li class="${active==4?'active':''}"><a href="/frontend/searchjirasbytext.html">Summary Reports</a></li>
            
                  
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
            
            
                <div class="container theme-showcase">
                    
                    <decorator:body />
                </div>
            </div>  
        

		</div>
		
        <script src="<spring:url value="/resources/vendor/jquery/jquery-1.10.2.min.js" />" type="text/javascript"></script>
        <script src="<spring:url value="/resources/vendor/bootstrap/js/bootstrap.min.js" />" type="text/javascript"></script>
        <script src="<spring:url value="/resources/vendor/jquery/datepicker/js/bootstrap-datepicker.js" />" type="text/javascript"></script>
        <script src="<spring:url value="/resources/vendor/jquery/dropdown/bootstrap-select.min.js" />" type="text/javascript"></script>
        <script src="<spring:url value="/resources/vendor/lodash/lodash.min.js" />" type="text/javascript"></script>
        <script src="<spring:url value="/resources/vendor/datatables/js/jquery.dataTables.min.js" />" type="text/javascript"></script>
        <script src="<spring:url value="/resources/vendor/datatables/js/DT_bootstrap.js" />" type="text/javascript"></script>

        
       
        <script src="<spring:url value="/resources/javascript/main.js" />" type="text/javascript"></script>


  

        <decorator-ext:scripts/>
        
         <script type="text/javascript">
            $(document).ready(function(){
                 $('.datepicker').datepicker();
                 $('.selectpicker').selectpicker();
                 $('.dataTable').dataTable( {
                    "aLengthMenu": [[10, 100, 250, 500, -1], [10, 100, 250, 500, "All"]],
                     
    } );       
            
            
            });
        </script>
        
    </body>
</html>
