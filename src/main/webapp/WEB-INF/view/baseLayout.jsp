<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="local.loctitle.name.management"/></title>
    <spring:url value="/resources/styles/newsStyle.css" var="newsStyleCss"/>
    <link href="${newsStyleCss}" rel="stylesheet"/>

</head>

<body>
<div class="page">
    <div class="header">
        <c:import url="/WEB-INF/view/header.jsp"/>
    </div>

    <div class="base-layout-wrapper">
        <div class="menu">

           	<security:authorize access="hasRole('ROLE_ANONYMOUS')">
                <spring:message code="local.loctitle.name.welcome"/>
                <br/>
           </security:authorize>	
           	<security:authorize access="hasRole('ROLE_USER')">
                <c:import url="/WEB-INF/view/menu.jsp"/>
            </security:authorize>	
        </div>

        <div class="content">
            <c:if test="${not (reg eq 'active')}">
               	<security:authorize access="hasRole('ROLE_ANONYMOUS')">
                    <c:import url="/WEB-INF/view/guestInfo.jsp"/>
              </security:authorize>	
               	<security:authorize access="hasRole('ROLE_USER')">
                    <c:import url="/WEB-INF/view/body.jsp"/>
                </security:authorize>	
            </c:if>
            <c:if test="${reg eq 'active'}">
                <c:import url="/WEB-INF/view/registration.jsp"/>
            </c:if>
            <c:if test="${addnews eq 'active'}">
                <c:import url="/WEB-INF/view/addNews.jsp"/>
            </c:if>
        </div>
    </div>

    <div class="footer">
        <c:import url="/WEB-INF/view/footer.jsp"/>
    </div>
</div>
</body>
</html>