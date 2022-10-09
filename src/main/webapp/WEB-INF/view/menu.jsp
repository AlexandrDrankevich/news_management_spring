<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="menu-wrapper">
    <div class="menu-title-wrapper">
        <div class="menu-title">
            <spring:message code="local.loclink.name.news"/>
        </div>
    </div>

    <div class="list-menu-invisible-wrapper">
        <div class="list-menu-wrapper">
            <ul>
                <li>
                    <c:url var="newsLink" value="/newsList"/>
                    <a href="${newsLink}"> <spring:message code="local.loclink.name.list"/></a><br/>
                </li>
                	<security:authorize access="hasRole('ROLE_ADMIN')">
                    <li>
                        <c:url var="addNews" value="/news/addNewsForm"/>
                        <a href="${addNews}"><spring:message code="local.loclink.name.add"/></a>
                        <br/>
                    </li>
                </security:authorize>	
            </ul>
        </div>
        <div class="clear"></div>
    </div>
    <div style="height: 25px;"></div>
</div>

