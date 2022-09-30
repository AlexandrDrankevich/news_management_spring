<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="body-title">
    <c:url var="basePageLink" value="/base_page"></c:url>
    <a href="${ basePageLink}"><spring:message code="local.loclink.name.news"/> >> </a>
    <spring:message code="local.loclink.name.reg"/>
</div>

<div class="reg-form-body">
    <form:form action="do_registration" modelAttribute="newUserInfo">
        <form:hidden path="id"/>
        <div>
            <label for="name"><spring:message code="local.loclabel.name.name"/> </label>
            <form:input path="name" class="form-control" required="required" pattern="[A-Z a-z]+"/>
        </div>
        <br/>
        <div>
            <label for="surname"><spring:message code="local.loclabel.name.surname"/></label>
            <form:input path="surname" class="form-control" required="required" pattern="[A-Z a-z]+"/>
        </div>
        <br/>
        <div>
            <label for="login"><spring:message code="local.loclabel.name.logMail"/>
                <c:if test="${not(messageLoginExist eq null)}">
                    <font color="red"> <c:out value="${messageLoginExist}"/>
                        <spring:message code="local.loclabel.name.exist"/></font>
                </c:if>
            </label>
            <form:input type="email" path="login" class="form-control" required="required"/>
        </div>
        <br/>
        <div>
            <label for="birthday"><spring:message code="local.loclabel.name.birthday"/></label>
            <form:input type="date" path="birthday" class="form-control" required="required"/>
        </div>
        <br/>
        <div>
            <label for="password"><spring:message code="local.loclabel.name.password"/> </label>
            <form:input type="password" id="password" path="password" class="form-control" required="required"
                        pattern="[A-Z a-z 0-9]+"/>
        </div>
        <br/>
        <div>
            <input type="submit" class="btn" value="<spring:message code="local.loclink.name.reg"/>"/>
        </div>
    </form:form>
</div>
