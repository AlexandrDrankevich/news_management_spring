<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Error Page</title>
    <spring:url value="/resources/styles/newsStyle.css" var="newsStyleCss"/>
    <link href="${newsStyleCss}" rel="stylesheet"/>
</head>
<body>
<div align="center">
    <h4>Opps! Sorry Error.</h4>
    <c:set var="user" value="not active" scope="session"/>
    <c:url var="basePage" value="/base_page"/>
    <a href="${basePage}"> <input type="submit" class="btn"
                                  value="<spring:message code="local.locbutton.name.back"/>"/></a>
</div>
</body>
</html>