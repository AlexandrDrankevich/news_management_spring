<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.loclink.name.news"
             var="news_link"/>
 <fmt:message bundle="${loc}" key="local.loclink.name.edit_news"
             var="edit_news"/>
<fmt:message bundle="${loc}" key="local.loclink.name.add_news"
             var="add_news"/>
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
                    <a href="${newsLink}">${news_link} >> </a>
                    <c:if test="${not(editView eq 'active')}"> ${add_news}</c:if>
                    <c:if test="${editView eq 'active'}"> ${edit_news}</c:if>
</div>
<br/>
<div align="center">
 <c:url var="addNews" value="/addNews"/> 
    <form:form  action="${addNews}" modelAttribute="news">
   <form:hidden path="idNews" />
        <div>
            <label for=title>${title}&nbsp </label>
            
            <form:input type="text" path="title" id="title" class="form-addnews"  maxlength="100" required="required"/>
        </div>
        <br/>
        <div>
            <label for=date>${date} &nbsp</label>
            <form:input type="date" path="newsDate" id="date" class="form-addnews" min="2022-01-01" max="2030-01-01" required="required"/>
        </div>
        <br/>
        <p class="formfield">
            <label for="brief">${brief} &nbsp</label>
            <form:textarea path="briefNews" id="brief" class="form-addnews" maxlength="500" required="required"/>
        </p>
        <br/>
        <p class="formfield">
            <label for="content">${content} </label>
            <form:textarea path="content" id="content" class="form-content" maxlength="2048" required="required"/>
        </p>
        <br/>
        <div class="first-view-button">
            <input type="submit" value="${save}"/>
        </div>
    </form:form>

    <div class="second-view-button">
       <c:url var="newsLink" value="/newsList"/> 
                    <a href="${newsLink}"><input type="submit" value="${cancel}"/></a>
    </div>
</div>


