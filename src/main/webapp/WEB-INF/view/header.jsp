<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="wrapper">
    <div class="newstitle"><spring:message code="local.loctitle.name.management"/></div>

    <div class="local-link">

        <div align="right">
            <a href="?languageVar=en">
                <spring:message code="local.locbutton.name.en"/></a> &nbsp;&nbsp;
            <a href="?languageVar=ru"><spring:message code="local.locbutton.name.ru"/></a> <br/> <br/>
        </div>

      <security:authorize access="hasRole('ROLE_ANONYMOUS')">

            <div align="right">
                 <form action="${pageContext.request.contextPath}/authenticateTheUser" method="post" >
                    <spring:message code="local.loclabel.name.login"/> 
                    <input type="text" name="username" value="" required pattern="[a-z 0-9]+@[a-z]+.[a-z]{2,3}"/><br/>
                    <spring:message code="local.loclabel.name.password_enter"/> <input
                        type="password" name="password" value="" required
                        pattern="[A-Z a-z 0-9]+"/><br/>

                    <c:if test="${param.error != null}">
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
               <input type="hidden"
							   name="${_csrf.parameterName}"
							   value="${_csrf.token}" />
          </form>
            </div>
     </security:authorize>	

        	<security:authorize access="hasRole('ROLE_USER')">

            <div align="right">
            
            	<form:form action="${pageContext.request.contextPath}/logout" 
			   method="POST">
	
		<input type="submit" value="<spring:message code="local.locbutton.name.sign_out"/>" />
	
	</form:form>
	                                             
                 <br/>                            
                <c:if test="${not (param.newsMessage eq null)}">
                    <spring:message code="local.loctitle.name.news_saved"/>
                </c:if>

                <c:if test="${not (param.deleteMessage eq null)}">
                    <spring:message code="local.loctitle.name.news_delete"/>
                </c:if>
            </div>

           </security:authorize>	
    </div>

</div>

