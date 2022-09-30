<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="body-title">
    <c:url var="newsLink" value="/newsList"/>
    <a href="${newsLink}"><spring:message code="local.loclink.name.news"/> >> </a>
    <c:if test="${not(editView eq 'active')}"> <spring:message code="local.loclink.name.add_news"/></c:if>
    <c:if test="${editView eq 'active'}"><spring:message code="local.loclink.name.edit_news"/></c:if>
</div>
<br/>
<div align="center">
    <c:url var="addNews" value="/news/saveNews"/>
    <form:form action="${addNews}" modelAttribute="news">
        <form:hidden path="idNews"/>
        <div>
            <label for=title><spring:message code="local.loclabel.name.title"/>&nbsp </label>

            <form:input type="text" path="title" id="title" class="form-addnews" maxlength="100" required="required"/>
        </div>
        <br/>
        <div>
            <label for=date><spring:message code="local.loclabel.name.date"/> &nbsp</label>
            <form:input type="date" path="newsDate" id="date" class="form-addnews" min="2022-01-01" max="2030-01-01"
                        required="required"/>
        </div>
        <br/>
        <p class="formfield">
            <label for="brief"><spring:message code="local.loclabel.name.brief"/> &nbsp</label>
            <form:textarea path="briefNews" id="brief" class="form-addnews" maxlength="500" required="required"/>
        </p>
        <br/>
        <p class="formfield">
            <label for="content"><spring:message code="local.loclabel.name.content"/> </label>
            <form:textarea path="content" id="content" class="form-content" maxlength="2048" required="required"/>
        </p>
        <br/>
        <div class="first-view-button">
            <input type="submit" value="<spring:message code="local.locbutton.name.save"/>"/>
        </div>
    </form:form>

    <div class="second-view-button">
        <c:url var="newsLink" value="/newsList"/>
        <a href="${newsLink}"><input type="submit" value="<spring:message code="local.locbutton.name.cancel"/>"/></a>
    </div>
</div>


