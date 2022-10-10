<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="body-title">
    <a href=""><spring:message code="local.loclink.name.news"/> >> </a><spring:message
        code="local.loclink.name.news_list"/>
</div>
<c:url var="deleteNews" value="/news/delete"/>
<form:form action="${deleteNews}" method="get">
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
                        <security:authorize access="hasRole('ROLE_ADMIN')">
                            <a href="news/editNews/${news.idNews}"><spring:message
                                    code="local.loclink.name.edit"/>&nbsp </a>
                       </security:authorize>	

                        <a href="viewNews/${news.idNews}"><spring:message code="local.loclink.name.view"/></a>
                        <security:authorize access="hasRole('ROLE_ADMIN')">
                            <input type="checkbox" name="id" value="${news.idNews }"/>
                          </security:authorize>
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
     <security:authorize access="hasRole('ADMIN')">
    <c:if test="${not(requestScope.news eq null)}"> 
        <div align="right">

            <input type="submit"  onclick="if (!(confirm('<spring:message code="local.clickDelete.text"/>'))) return false" value="<spring:message code="local.locbutton.name.delete"/>"/>
        </div>
    </c:if>
      </security:authorize>	
      </form:form>
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

