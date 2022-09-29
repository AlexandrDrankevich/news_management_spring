<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="wrapper">
    <div class="newstitle"><spring:message code="local.loctitle.name.management"/></div>

    <div class="local-link">

        <div align="right">
            <a href="?languageVar=en">
<spring:message code="local.locbutton.name.en"/></a> &nbsp;&nbsp; 
<a href="?languageVar=ru"><spring:message code="local.locbutton.name.ru"/></a> <br/> <br/>
        </div>

        <c:if test="${not (sessionScope.user eq 'active')}">

            <div align="right">
             <c:url var="signIn" value="/signIn"></c:url>
                <form action="${signIn}" method="post">
                    <input type="hidden" name="command" value="do_sign_in"/>
                   <spring:message code="local.loclabel.name.login"/> <input type="text" name="login" value="" required
                                        pattern="[a-z 0-9]+@[a-z]+.[a-z]{2,3}"/><br/>
        <spring:message code="local.loclabel.name.password_enter"/> <input
                        type="password" name="password" value="" required
                        pattern="[A-Z a-z 0-9]+"/><br/>

                    <c:if test="${not (param.AuthenticationError eq null)}">
                        <font color="red"> 
                        <spring:message code="local.loctitle.name.authentication_error"/>
                        </font>
                    </c:if>

                    <c:if test="${not (param.regMessage eq null)}">
                       <spring:message code="local.loctitle.name.reg_success"/>
                    </c:if>
                    <c:url var="registrationLink" value="/registration/showForm">
								</c:url>

                    <a href="${registrationLink}"><spring:message code="local.loclink.name.reg"/></a>
                    <input type="submit" value="<spring:message code="local.locbutton.name.sign_in"/>"/><br/>
                </form>
            </div>

        </c:if>

        <c:if test="${sessionScope.user eq 'active'}">

            <div align="right">
            <c:url var="signOut" value="/signOut"/>    
                    <a href="${signOut}"> <input type="submit" value="<spring:message code="local.locbutton.name.sign_out"/>"/><br/></a>
                    <c:if test="${not (param.newsMessage eq null)}">
                        <spring:message code="local.loctitle.name.news_saved"/>
                    </c:if>

                    <c:if test="${not (param.deleteMessage eq null)}">
                       <spring:message code="local.loctitle.name.news_delete"/>
                    </c:if>
                          </div>

        </c:if>
    </div>

</div>
