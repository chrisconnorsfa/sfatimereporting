<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row-fluid">
    <div class="span12">
		<div class="container-fluid">
			
			<%@include file="logo.jsp" %>

			<div class="header-bits">
		
				<%-- SHOW CLIENT FOR BACKOFFICE USER --%>
                  <sec:authorize access="hasRole('ROLE_BACK_OFFICE_USER')">
                      <sec:authentication property="client" var="viewClient" />
                      <c:if test="${viewClient!=null}">
                          <div class="viewUser">
                              <spring:message code="navbar.viewing.client" arguments="${viewClient.displayName},${viewClient.id}" var="displayClient" />
                              <c:out value="${displayClient}" />
                          </div>
                      </c:if>
                  </sec:authorize>
                  
                  <%-- Display telephone number logic --%>
                  <c:set var="displayTelNo">
                      <decorator:getProperty property="meta.displayTelNo" default="false" />
                  </c:set>
                  <sec:authorize access="(hasRole('ROLE_USER') and !hasRole('ROLE_BACK_OFFICE_USER'))">
                      <c:set var="displayTelNo" value="true" />
                  </sec:authorize>
                  <c:if test="${displayTelNo}">
                      <div class="tel tel-icon">
                          <spring:message text="" code="telephoneNo" />
                      </div>
                  </c:if>
                                    
                  <%-- SHOW SEARCH FORM / FAQ --%>
                  <div class="search-form-main">
	                  <form id="search-form" method="post" name="faqform" onSubmit="faqtAgent_lightbox(escape(document.getElementById('fa_autosuggest_input').value),'search');return false;">
	                      <fieldset>
	                          <input id="fa_autosuggest_input" class="fa_autosuggest_input default" type="text" name="fa_autosuggest_input" onblur="if(allow_autosuggest_hide==true) hide_autosuggest();" onkeyup="autosuggest(event);" />
	                          <button id="fa_autosuggest_submit" type="submit" value="Submit" >Go</button>
	                          <div id="fa_autosuggest" class="fa_autosuggest" onmouseout="allow_autosuggest_hide=true;document.getElementById('fa_autosuggest_input').focus();" onmouseover="allow_autosuggest_hide=false;"></div>
	                      </fieldset>
	                  </form>
                  </div>

			</div>
			
			<%-- LOGGED IN NAVIGATION --%>
	        <sec:authorize access="isAuthenticated() and !hasRole('ROLE_PARTIALLY_AUTHENTICATED') and !hasRole('ROLE_LOCKED') and !hasRole('ROLE_RESET_CREDENTIALS')">
	        	
	        	<%-- DESKTOP NAVIGATION --%>
	        	<nav class="main-navigation">
		        	<div class="row-fluid">
		        		<div class="span12">
		        			<ul class=" nav nav-tabs">
		                        <c:set var="currentTab">
		                            <decorator:getProperty property="meta.tabIndex" default="myProducts" />
		                        </c:set>
		
		                        <sec:authorize access="hasRole('ROLE_BACK_OFFICE_USER')">
		                            <li class="${currentTab=='backOfficeHome'?'active':''}">
		                                <a href="<spring:url value="/backoffice/home" />">
		                                    <spring:message text="" code="navbar.backOfficeHome" />
		                                </a>
		                            </li>
		                        </sec:authorize>
		
		                        <c:if test="${currentTab!='backOfficeHome'}">
		                            <li class="${currentTab=='myProducts'?'active':''}"><a id="myProducts" href="<spring:url value="/summary" />"><spring:message text="" code="navbar.myProducts" /></a></li>
		                            <li class="${currentTab=='myCorrespondence'?'active':''}"><a id="myCorrespondence" href="<spring:url value="/correspondence" />"><spring:message text="" code="navbar.myCorrespondence" /></a></li>
		                            <li class="${currentTab=='myDetails'?'active':''}"><a id="myDetails" href="<spring:url value="/details" />"><spring:message text="" code="navbar.myDetails" /></a></li>
		                            <li class="${currentTab=='contactUs'?'active':''}"><a id="contactUs" href="<spring:message text="" code="navbar.contactUs.url"/>" target="_blank"><spring:message text="" code="navbar.contactUs" /></a></li>
		                            <sec:authorize access="hasRole('ROLE_BACK_OFFICE_USER')">
		                                <li class="${currentTab=='editClientLoginDetails'?'active':''}"><a id="editClientLoginDetails" href="<spring:url value="/user/edit" />"><spring:message text="" code="navbar.editClientLoginDetails" /></a></li>
		                            </sec:authorize>
		                        </c:if>
		
		                        <li class="dropdown ${currentTab=='loggedInAs'?'active':''} pull-right">
		                            <a id="dropDownMenu" class="dropdown-toggle" data-toggle="dropdown" href="#">
		                                <spring:message text="" code="navbar.loggedInAs" />
		                                <sec:authentication property="username" />
		                                <b class="caret"></b>
		                            </a>
		                            <ul class="dropdown-menu">
		                                <sec:authorize access="hasRole('ROLE_USER') and !hasRole('ROLE_BACK_OFFICE_USER')">
		                                    <li>
		                                        <a href="<spring:url value="/user/edit" />">
		                                            <spring:message text="" code="navbar.submenu.editUser" />
		                                        </a>
		                                    </li>
		                                    <li class="divider"></li>
		                                    <li>
		                                        <a id="logout" href="<spring:url value="/public/j_spring_security_logout" />">
		                                            <spring:message text="" code="navbar.submenu.logout" />
		                                        </a>
		                                    </li>
		                                </sec:authorize>
		                                <sec:authorize access="hasRole('ROLE_BACK_OFFICE_USER')">
		                                    <li>
		                                        <a id="logout" href="<spring:url value="/backoffice/j_spring_security_logout" />">
		                                            <spring:message text="" code="navbar.submenu.logout" />
		                                        </a>
		                                    </li>
		                                </sec:authorize>
		                            </ul>
		                        </li>
		                	</ul>
		        		</div>
		        	</div>
	        	</nav>
	                  
	       	 </sec:authorize>
	       	 <%-- END LOGGED IN NAVIGATION --%>
       	 
	       	 <%-- PUBLIC / NOT AUTHENTICATED NAVIGATION --%>
	       	 <sec:authorize access="!isAuthenticated() or hasRole('ROLE_PARTIALLY_AUTHENTICATED') or hasRole('ROLE_LOCKED') or hasRole('ROLE_RESET_CREDENTIALS')">
	         
	         	<nav class="public-navigation"></nav>
	         
	         </sec:authorize>
              
          </div>
  		</div>
  	</div>