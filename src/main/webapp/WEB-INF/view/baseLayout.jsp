<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.loctitle.name.welcome"
             var="welcome"/>
<fmt:message bundle="${loc}" key="local.loctitle.name.management"
             var="management"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${management}</title>
  <spring:url value="/resources/styles/newsStyle.css" var="newsStyleCss"/>
<link href="${newsStyleCss}" rel="stylesheet" />
</head>

<body>
<div class="page">
    <div class="header">
        <c:import url="/WEB-INF/view/header.jsp"/>
    </div>

    <div class="base-layout-wrapper">
        <div class="menu">

            <c:if test="${not (sessionScope.user eq 'active')}">
                ${welcome}
                <br/>
            </c:if>
            <c:if test="${sessionScope.user eq 'active'}">
                <c:import url="/WEB-INF/view/menu.jsp"/>
            </c:if>
        </div>

        <div class="content">
            <c:if test="${not (param.reg eq 'reg')}">
                <c:if test="${not (sessionScope.user eq 'active')}">
                    <c:import url="/WEB-INF/view/guestInfo.jsp"/>
                </c:if>
                <c:if test="${sessionScope.user eq 'active'}">
                    <c:import url="/WEB-INF/view/body.jsp"/>
                </c:if>
            </c:if>
            <c:if test="${param.reg eq 'reg'}">
                <c:import url="/WEB-INF/view/registration.jsp"/>
            </c:if>
            <c:if test="${addnews eq 'active'}">
                <c:import url="/WEB-INF/view/addNews.jsp"/>
            </c:if>
            <c:if test="${editnews eq 'active'}">
                <c:import url="/WEB-INF/view/editNews.jsp"/>
            </c:if>
        </div>
    </div>

    <div class="footer">
        <c:import url="/WEB-INF/view/footer.jsp"/>
    </div>
</div>
</body>
</html>