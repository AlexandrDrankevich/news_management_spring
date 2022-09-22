<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
             var="ru_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
             var="en_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.sign_in"
             var="sign_in"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.sign_out"
             var="sign_out"/>
<fmt:message bundle="${loc}" key="local.loclink.name.reg"
             var="registration"/>
<fmt:message bundle="${loc}" key="local.loclabel.name.login"
             var="login"/>
<fmt:message bundle="${loc}" key="local.loclabel.name.password_enter"
             var="password"/>
<fmt:message bundle="${loc}" key="local.loctitle.name.management"
             var="management"/>
<fmt:message bundle="${loc}" key="local.loctitle.name.reg_success"
             var="reg_success"/>
<fmt:message bundle="${loc}" key="local.loctitle.name.news_saved"
             var="news_saved"/>
<fmt:message bundle="${loc}" key="local.loctitle.name.news_delete"
             var="news_delete"/>
<fmt:message bundle="${loc}" key="local.loctitle.name.authentication_error"
             var="authentication_error"/>

<div class="wrapper">
    <div class="newstitle">${management}</div>

    <div class="local-link">

        <div align="right">
            <a href="controller?command=do_change_local&local=en">${en_button}</a> &nbsp;&nbsp; <a
                href="controller?command=do_change_local&local=ru">${ru_button}</a> <br/> <br/>
        </div>

        <c:if test="${not (sessionScope.user eq 'active')}">

            <div align="right">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="do_sign_in"/>
                        ${login} <input type="text" name="login" value="" required
                                        pattern="[a-z 0-9]+@[a-z]+.[a-z]{2,3}"/><br/>${password} <input
                        type="password" name="password" value="" required
                        pattern="[A-Z a-z 0-9]+"/><br/>

                    <c:if test="${not (param.AuthenticationError eq null)}">
                        <font color="red"> <c:out
                                value="${authentication_error}"/>
                        </font>
                    </c:if>

                    <c:if test="${not (param.regMessage eq null)}">
                        <c:out value="${reg_success}"/>
                    </c:if>
                    

                    <a href="/registration/showForm">${registration}</a>
                    <input type="submit" value="${sign_in}"/><br/>
                </form>
            </div>

        </c:if>

        <c:if test="${sessionScope.user eq 'active'}">

            <div align="right">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="do_sign_out"/> <input
                        type="submit" value="${sign_out}"/><br/>
                    <c:if test="${not (param.newsMessage eq null)}">
                        <c:out value="${news_saved}"/>
                    </c:if>

                    <c:if test="${not (param.deleteMessage eq null)}">
                        <c:out value="${news_delete}"/>
                    </c:if>
                </form>
            </div>

        </c:if>
    </div>

</div>
