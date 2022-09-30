<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="body-title">
    <a href=""><spring:message code="local.loclink.name.news"/> >> </a><spring:message
        code="local.loclink.name.news_list"/>
</div>
<c:url var="deleteNews" value="/news/delete"/>
<form action="${deleteNews}" method="post">
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
                <div class="news-link-to-wrapper">
                    <div class="link-position">
                        <c:if test="${sessionScope.role eq 'admin'}">
                            <a href="news/editNews/${news.idNews}"><spring:message
                                    code="local.loclink.name.edit"/>&nbsp </a>
                        </c:if>

                        <a href="viewNews/${news.idNews}"><spring:message code="local.loclink.name.view"/></a>
                        <c:if test="${sessionScope.role eq 'admin'}">
                            <input type="checkbox" name="id" value="${news.idNews }"/>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

    </c:forEach>

    <div class="no-news">
        <c:if test="${requestScope.news eq null}">
            <spring:message code="local.loctitle.name.no_news"/>
        </c:if>
    </div>
    <c:if test="${(sessionScope.role eq 'admin')&&not(requestScope.news eq null)}">
        <div align="right">

            <input type="submit" value="<spring:message code="local.locbutton.name.delete"/>"/>
        </div>
    </c:if>
    <br/>
    <div align="center">
        <c:if test="${requestScope.PageCount.size()>1}">
            <c:forEach var="pageNumber" items="${requestScope.PageCount}">

                <c:url var="pageLink" value="/newsList">
                    <c:param name="pageNumber" value="${pageNumber}"/></c:url>
                <a href="${pageLink}">${pageNumber}&nbsp</a>

            </c:forEach>
        </c:if>
    </div>
    <c:if test="${requestScope.news.size()>4}">
        <br/> <spring:message code="local.loctitle.news_on_page"/>
        <c:url var="newsNumberLink5" value="/newsList">
            <c:param name="pageNumber" value="1"/>
            <c:param name="newsCount" value="5"/>
        </c:url>

        <c:url var="newsNumberLink10" value="/newsList">
            <c:param name="pageNumber" value="1"/>
            <c:param name="newsCount" value="10"/>
        </c:url>
        <a href="${newsNumberLink5}">5</a>
        <a href="${newsNumberLink10}">10</a>
    </c:if>
</form>
