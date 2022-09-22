<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.loclink.name.news"
             var="news_link"/>
<fmt:message bundle="${loc}" key="local.loclink.name.view_news"
             var="view_news"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.edit"
             var="edit"/>
<fmt:message bundle="${loc}" key="local.loclabel.name.title"
             var="title"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.delete"
             var="delete"/>
<fmt:message bundle="${loc}" key="local.loclabel.name.date"
             var="date"/>
<fmt:message bundle="${loc}" key="local.loclabel.name.brief"
             var="brief"/>
<fmt:message bundle="${loc}" key="local.loclabel.name.content"
             var="content"/>

<div class="body-title">
    <a href="controller?command=go_to_news_list">${news_link} >> </a> ${view_news}
</div>

<div class="add-table-margin">
    <table class="news_text_format">
        <tr>
            <td class="space_around_title_text">${title}</td>

            <td class="space_around_view_text">
                <div class="word-breaker">
                    <c:out value="${requestScope.news.title }"/>
                </div>
            </td>
        </tr>
        <tr>
            <td class="space_around_title_text">${date}</td>

            <td class="space_around_view_text">
                <div class="word-breaker">
                    <c:out value="${requestScope.news.newsDate }"/>
                </div>
            </td>
        </tr>
        <tr>
            <td class="space_around_title_text">${brief}</td>
            <td class="space_around_view_text">
                <div class="word-breaker">
                    <c:out value="${requestScope.news.briefNews }"/>
                </div>
            </td>
        </tr>
        <tr>
            <td class="space_around_title_text">${content}</td>
            <td class="space_around_view_text">
                <div class="word-breaker">
                    <c:out value="${requestScope.news.content }"/>
                </div>
            </td>
        </tr>
    </table>
</div>

<c:if test="${sessionScope.role eq 'admin'}">
    <div class="first-view-button">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="go_to_edit_news"/> <input
                type="hidden" name="id" value="${requestScope.news.idNews }"/>
            <input type="hidden" name="editView" value="active"/>
            <input type="submit" value="${edit}"/>
        </form>
    </div>

    <div class="second-view-button">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="do_delete_news"/>
            <input type="hidden" name="id" value="${requestScope.news.idNews }"/>
            <input type="submit" value="${delete}"/>
        </form>
    </div>
</c:if>