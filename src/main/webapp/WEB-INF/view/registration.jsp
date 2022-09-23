<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.loclink.name.reg"
             var="registration"/>
<fmt:message bundle="${loc}" key="local.loclabel.name.name"
             var="name"/>
<fmt:message bundle="${loc}" key="local.loclabel.name.surname"
             var="surname"/>
<fmt:message bundle="${loc}" key="local.loclabel.name.logMail"
             var="login"/>
<fmt:message bundle="${loc}" key="local.loclabel.name.birthday"
             var="birthday"/>
<fmt:message bundle="${loc}" key="local.loclabel.name.password"
             var="password"/>
<fmt:message bundle="${loc}" key="local.loclabel.name.exist"
             var="exist"/>
<fmt:message bundle="${loc}" key="local.loclink.name.news"
             var="news_link"/>

<div class="body-title">
    <a href="controller?command=go_to_base_page&regUrl=delete">${news_link} >> </a> ${registration}
</div>

<div class="reg-form-body">
    <form:form action="do_registration" modelAttribute="newUserInfo">
        <div>
            <label for="name">${name} </label>
            <form:input  path="name"  class="form-control" required="required" pattern="[A-Z a-z]+"/>
        </div>
        <br/>
        <div>
            <label for="surname">${surname}</label>
            <form:input  path="surname" class="form-control" required="required" pattern="[A-Z a-z]+"/>
        </div>
        <br/>
        <div>
            <label for="login">${login}
                <c:if test="${not(param.messageLoginExist eq null)}">
                    <font color="red"> <c:out value="${param.messageLoginExist} ${exist}"/></font>
                </c:if>
            </label>
           <form:input type="email" path="login" class="form-control" required="required"/>
        </div>
        <br/>
        <div>
            <label for="birthday">${birthday}</label>
             <form:input  type="date" path="birthday" class="form-control" required="required"/>
        </div>
        <br/>
        <div>
            <label for="password">${password} </label>
          <form:input type="password"  id="password" path="password" class="form-control" required="required" 
          pattern="[A-Z a-z 0-9]+"/>
        </div>
        <br/>
        <div>
            <input type="submit" class="btn" value="${registration}"/>
        </div>
  </form:form>
</div>
