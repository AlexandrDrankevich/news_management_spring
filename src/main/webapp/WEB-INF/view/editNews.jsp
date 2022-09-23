<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.loclink.name.news"
             var="news_link"/>
<fmt:message bundle="${loc}" key="local.loclink.name.edit_news"
             var="edit_news"/>
<fmt:message bundle="${loc}" key="local.loclink.name.view_news"
             var="view_news"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.save"
             var="save"/>
<fmt:message bundle="${loc}" key="local.loclabel.name.title"
             var="title"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.cancel"
             var="cancel"/>
<fmt:message bundle="${loc}" key="local.loclabel.name.date"
             var="date"/>
<fmt:message bundle="${loc}" key="local.loclabel.name.brief"
             var="brief"/>
<fmt:message bundle="${loc}" key="local.loclabel.name.content"
             var="content"/>

<div class="body-title">
    <c:url var="newsLink" value="/newsList"/> 
                    <a href="${newsLink}">${news_link}</a> >>
        <c:if test="${param.editView eq 'active'}">
     <c:url var="viewnewsLink" value="/viewNews/${news.idNews}"></c:url>  
        
        <a href="${viewnewsLink}">${view_news} >>
            </c:if>
        </a>${edit_news}
</div>
<br/>
<div align="center">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="do_edit_news"/>
        <input type="hidden" name="id" value="${news.idNews}"/>
        <div>
            <label for=title>${title}&nbsp</label>
            <input type="text" name="title" id="title" class="form-addnews" value="${news.title}" maxlength="100"
                   required/>
        </div>
        <br/>
        <div>
            <label for=date>${date}&nbsp</label>
            <input type="date" name="date" id="date" class="form-addnews" value="${news.newsDate}"
                   min="2022-01-01" max="2030-01-01" required/>
        </div>
        <br/>
        <p class="formfield">
            <label for="brief">${brief} &nbsp</label>
            <textarea name="brief" id="brief" class="form-addnews" maxlength="500">${news.briefNews}</textarea>
        </p>
        <br/>
        <p class="formfield">
            <label for="content">${content} </label>
            <textarea name="content" id="content" class="form-content" maxlength="2048">${news.content}</textarea>
        <p/>
        <br/>
        <div class="first-view-button">
            <input type="submit" value="${save}"/>
        </div>
    </form>

    <c:if test="${not (param.editView eq 'active')}">
        <div class="second-view-button">
            <a href="controller?command=go_to_news_list"><input type="submit"
                                                                value="${cancel}"/></a>
        </div>
    </c:if>
    <c:if test="${param.editView eq 'active'}">
        <div class="second-view-button">
            <a href="controller?command=go_to_view_news&id=${news.idNews}"><input
                    type="submit" value="${cancel}"/></a>
        </div>
    </c:if>
</div>


