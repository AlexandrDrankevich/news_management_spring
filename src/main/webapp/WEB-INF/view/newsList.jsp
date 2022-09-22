<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.loclink.name.news"
             var="news"/>
<fmt:message bundle="${loc}" key="local.loclink.name.news_list"
             var="news_list"/>
<fmt:message bundle="${loc}" key="local.loclink.name.edit"
             var="edit"/>
<fmt:message bundle="${loc}" key="local.loclink.name.view"
             var="view"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.delete"
             var="delete"/>
<fmt:message bundle="${loc}" key="local.loctitle.name.no_news"
             var="no_news"/>
<fmt:message bundle="${loc}" key="local.loctitle.news_on_page"
             var="news_on_page"/>

<div class="body-title">
    <a href="">${news} >> </a>${news_list}
</div>
<form action="controller" method="post">
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
                            <a href="controller?command=go_to_edit_news&id=${news.idNews}">${edit}&nbsp </a>
                        </c:if>
                        <a href="controller?command=go_to_view_news&id=${news.idNews}&editView=active">${view} </a>
                        <c:if test="${sessionScope.role eq 'admin'}">
                            <input type="checkbox" name="id" value="${news.idNews }" />
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

    </c:forEach>

    <div class="no-news">
        <c:if test="${requestScope.news eq null}">
            ${no_news}
        </c:if>
    </div>
    <c:if test="${(sessionScope.role eq 'admin')&&not(requestScope.news eq null)}">
        <div align="right">
            <input type="hidden" name="command" value="do_delete_news"/>
            <input type="submit" value="${delete}"/>
        </div>
    </c:if>
    <br/><div align="center">
     <c:if test="${requestScope.PageCount.size()>1}">
     <c:forEach var="pageNumber" items="${requestScope.PageCount}">
          <a href="controller?command=go_to_news_list&pageNumber=${pageNumber}">${pageNumber}&nbsp </a>
        </c:forEach>
             </c:if>
               </div>
      <c:if test="${requestScope.news.size()>4}">
		<br /> ${news_on_page}
        <a href="controller?command=go_to_news_list&newsCount=5&pageNumber=1">5</a>
		<a href="controller?command=go_to_news_list&newsCount=10&pageNumber=1">10</a>
	</c:if>
</form>
