<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="body-title">
    <c:url var="newsLink" value="/newsList"/>
    <a href="${newsLink}"> <spring:message code="local.loclink.name.news"/> >> </a>
    <spring:message code="local.loclink.name.view_news"/>

</div>

<div class="add-table-margin">
    <table class="news_text_format">
        <tr>
            <td class="space_around_title_text"><spring:message code="local.loclabel.name.title"/></td>

            <td class="space_around_view_text">
                <div class="word-breaker">
                    <c:out value="${requestScope.news.title }"/>
                </div>
            </td>
        </tr>
        <tr>
            <td class="space_around_title_text"><spring:message code="local.loclabel.name.date"/></td>

            <td class="space_around_view_text">
                <div class="word-breaker">
                    <c:out value="${requestScope.news.newsDate }"/>
                </div>
            </td>
        </tr>
        <tr>
            <td class="space_around_title_text"><spring:message code="local.loclabel.name.brief"/></td>
            <td class="space_around_view_text">
                <div class="word-breaker">
                    <c:out value="${requestScope.news.briefNews }"/>
                </div>
            </td>
        </tr>
        <tr>
            <td class="space_around_title_text"><spring:message code="local.loclabel.name.content"/></td>
            <td class="space_around_view_text">
                <div class="word-breaker">
                    <c:out value="${requestScope.news.content }"/>
                </div>
            </td>
        </tr>
    </table>
</div>

  <security:authorize access="hasRole('ROLE_ADMIN')">
    <div class="first-view-button">
        <c:url var="editLink" value="/news/editNews/${requestScope.news.idNews }"> </c:url>
        <a href="${editLink}"><input type="submit" value="<spring:message code="local.locbutton.name.edit"/>"/></a>
    </div>

    <div class="second-view-button">
        <c:url var="deleteLink" value="/news/delete">
            <c:param name="id" value="${requestScope.news.idNews }"/></c:url>
        <a href="${deleteLink}"><input type="submit"  onclick="if (!(confirm('<spring:message code="local.clickDelete.text"/>'))) return false" 
        value="<spring:message code="local.locbutton.name.delete"/>"/></a>

    </div>
  </security:authorize>	