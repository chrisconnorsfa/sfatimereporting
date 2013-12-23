<%-- MOBILE NAVIGATION --%>
<nav class="mobile-navigation">
	<div class="row-fluid">
		<div class="span12">
			<div class="navbar">
				<div class="navbar-inner">
				
					<%@include file="logo.jsp" %>
					
					<sec:authorize access="isAuthenticated() and !hasRole('ROLE_PARTIALLY_AUTHENTICATED') and !hasRole('ROLE_LOCKED') and !hasRole('ROLE_RESET_CREDENTIALS')">
						<a class="btn btn-navbar menu-mobile" data-toggle="collapse" data-target=".nav-collapse">Menu</a>
					</sec:authorize>
				  	
				  	<a class="btn btn-navbar search-mobile" href="#">Search FAQs</a>
				  	
					 <%-- Display telephone number logic --%>
	                  <c:set var="displayTelNo">
	                      <decorator:getProperty property="meta.displayTelNo" default="false" />
	                  </c:set>
	                  <sec:authorize access="(hasRole('ROLE_USER') and !hasRole('ROLE_BACK_OFFICE_USER'))">
	                      <c:set var="displayTelNo" value="true" />
	                  </sec:authorize>
	                  <c:if test="${displayTelNo}">
	                      <a class="btn btn-navbar tel-mobile" href="tel:<spring:message text="" code="telephoneNo" />"><spring:message text="" code="telephoneNo" /></a>
	                  </c:if>
	                  
	                  <%-- LOGGED IN NAVIGATION --%>
			        <sec:authorize access="isAuthenticated() and !hasRole('ROLE_PARTIALLY_AUTHENTICATED') and !hasRole('ROLE_LOCKED') and !hasRole('ROLE_RESET_CREDENTIALS')">
			        	
			        	<div class="nav-collapse collapse">
					    	<ul class="nav">
					    	
					    		<%-- BACK OFFICE ENTRY --%>
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
		                        <%-- END BACK OFFICE ENTRY --%>
		                        
		                        <%-- SECTIONS --%>
		                         <c:if test="${currentTab!='backOfficeHome'}">
		                            <li class="${currentTab=='myProducts'?'active':''}"><a id="myProducts" href="<spring:url value="/summary" />"><spring:message text="" code="navbar.myProducts" /></a></li>
		                            <li class="${currentTab=='myCorrespondence'?'active':''}"><a id="myCorrespondence" href="<spring:url value="/correspondence" />"><spring:message text="" code="navbar.myCorrespondence" /></a></li>
		                            <li class="${currentTab=='myDetails'?'active':''}"><a id="myDetails" href="<spring:url value="/details" />"><spring:message text="" code="navbar.myDetails" /></a></li>
		                            <li class="${currentTab=='contactUs'?'active':''}"><a id="contactUs" href="<spring:message text="" code="navbar.contactUs.url"/>" target="_blank"><spring:message text="" code="navbar.contactUs" /></a></li>
		                            <sec:authorize access="hasRole('ROLE_BACK_OFFICE_USER')">
		                                <li class="${currentTab=='editClientLoginDetails'?'active':''}"><a id="editClientLoginDetails" href="<spring:url value="/user/edit" />"><spring:message text="" code="navbar.editClientLoginDetails" /></a></li>
		                            </sec:authorize>
		                        </c:if>
		                        <%-- END SECTIONS --%>
		                        
		                        <%-- USER ACTIONS --%>
		                        <sec:authorize access="hasRole('ROLE_USER') and !hasRole('ROLE_BACK_OFFICE_USER')">
                                   <li>
                                       <a href="<spring:url value="/user/edit" />">
                                           <spring:message text="" code="navbar.submenu.editUser" />
                                       </a>
                                   </li>
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
                               <%-- END USER ACTIONS --%>
					    	
					    	</ul>
				    	</div>
			        				                  
			       	 </sec:authorize>
			       	 <%-- END LOGGED IN NAVIGATION --%>

				  	
				</div>
			</div>
						       	 
	       	 <%-- SPACER FOR FAQS --%>
	       	 <div class="search-form-mobile">
	       	 </div>
			       	 
		</div>
	</div>
</nav>