<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="logoLink">
    <decorator:getProperty property="meta.logoLink" default="/public/login" />
</c:set>

<sec:authorize access="hasRole('ROLE_USER')">
    <c:set var="logoLink">
        <decorator:getProperty property="meta.logoLink" default="/summary" />
    </c:set>
</sec:authorize>

<a class="sfLogo" href="<spring:url value="${logoLink}" />"><spring:message text="" code="companyName" /></a>
