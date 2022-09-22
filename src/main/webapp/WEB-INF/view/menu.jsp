<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.loclink.name.list"
             var="list"/>
<fmt:message bundle="${loc}" key="local.loclink.name.add"
             var="add"/>
<fmt:message bundle="${loc}" key="local.loclink.name.news"
             var="news"/>

<div class="menu-wrapper">
    <div class="menu-title-wrapper">
        <div class="menu-title">
            ${news}
        </div>
    </div>

    <div class="list-menu-invisible-wrapper">
        <div class="list-menu-wrapper">
            <ul>
                <li >
                    <a href="controller?command=go_to_news_list">${list}</a><br/>
                </li>
                <c:if test="${sessionScope.role eq 'admin'}">
                    <li>
                        <a href="controller?command=go_to_add_news_page">${add} </a>
                        <br/>
                    </li>
                </c:if>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
    <div style="height: 25px;"></div>
</div>

