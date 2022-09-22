<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
    <form action="controller" method="post">
        <input type="hidden" name="command" value="do_registration">
        <div>
            <label for="name">${name} </label>
            <input type="text" name="name" id="name" class="form-control" value="" required pattern="[A-Z a-z]+"/>
        </div>
        <br/>
        <div>
            <label for="surname">${surname}</label>
            <input type="text" name="surname" id="surname" class="form-control" value="" required
                   pattern="[A-Z a-z]+"/>
        </div>
        <br/>
        <div>
            <label for="login">${login}
                <c:if test="${not(param.messageLoginExist eq null)}">
                    <font color="red"> <c:out value="${param.messageLoginExist} ${exist}"/></font>
                </c:if>
            </label>
            <input type="email" name="login" id="login" class="form-control" value="" required/>
        </div>
        <br/>
        <div>
            <label for="birthday">${birthday}</label>
            <input type="date" name="birthday" id="birthday" class="form-control" min="1900-01-01" max="2010-01-01"
                   required/>
        </div>
        <br/>
        <div>
            <label for="password">${password}</label>
            <input type="password" name="password" id="password" class="form-control" value="" required
                   pattern="[A-Z a-z 0-9]+" maxlength="10"/>
        </div>
        <br/>
        <div>
            <input type="submit" class="btn" value="${registration}"/>
        </div>
    </form>
</div>
