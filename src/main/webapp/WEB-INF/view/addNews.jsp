<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.loclink.name.news"
             var="news_link"/>
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
    <a href="controller?command=go_to_news_list">${news_link} >> </a> ${add_news}
</div>
<br/>
<div align="center">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="do_add_news"/>
        <div>
            <label for=title>${title}&nbsp </label>
            <input type="text" name="title" id="title" class="form-addnews" value="" maxlength="100" required/>
        </div>
        <br/>
        <div>
            <label for=date>${date} &nbsp</label>
            <input type="date" name="date" id="date" class="form-addnews" min="2022-01-01" max="2030-01-01" required/>
        </div>
        <br/>
        <p class="formfield">
            <label for="brief">${brief} &nbsp</label>
            <textarea name="brief" id="brief" class="form-addnews" maxlength="500" required></textarea>
        </p>
        <br/>
        <p class="formfield">
            <label for="content">${content} </label>
            <textarea name="content" id="content" class="form-content" maxlength="2048" required></textarea>
        </p>
        <br/>
        <div class="first-view-button">
            <input type="submit" value="${save}"/>
        </div>
    </form>

    <div class="second-view-button">
        <a href="controller?command=go_to_news_list"><input type="submit" value="${cancel}"/></a>
    </div>
</div>


