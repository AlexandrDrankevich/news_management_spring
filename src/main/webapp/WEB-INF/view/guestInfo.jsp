<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="body-title">
    <a href=""><spring:message code="local.loclink.name.news"/> >> </a><spring:message
        code="local.loclink.name.latest_news"/>
</div>

<c:forEach var="news" items="${requestScope.news}">
    <div class="single-news-wrapper">
        <div class="single-news-header-wrapper">
            <div class="news-title">
                <c:out value="${news.title}"/>
            </div>
            <div class="news-date">
                <c:out value="${news.newsDate}"/>
            </div>
            <div class="news-content">
                <c:out value="${news.briefNews}"/>
            </div>
        </div>
    </div>
</c:forEach>

<div class="no-news">
    <c:if test="${requestScope.news eq null}">
        <spring:message code="local.loctitle.name.no_news"/>
    </c:if>
</div>


