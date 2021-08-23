<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilya
  Date: 16.02.2021
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<myTags:head namePage="welcome"/>
<body>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="lang.messages"/>
<fmt:message key="label.newPassword" var="i18nNewPassword"/>
<div class="main">
    <myTags:header login="${sessionScope.sessionLogin}"
                   userLang="${lang}"
                   mainLink="${contpath}welcome.jhtml"
                   userListLink="${contpath}listUsers.jhtml"
                   logoutLink="${contpath}logout.jhtml"
                   roles="${sessionScope.roles}"/>
    <div class="content">
        <p class="app-description"><fmt:message key="label.appDesc"><fmt:param
                value="${sessionScope.sessionLogin}"/></fmt:message></p>
        <form method="post" action="${contpath}editpassword.jhtml" id="form-edit-pass">
            <label>
                <input type="password" placeholder="${i18nNewPassword}" name="newPassword">
            </label>
            <button class="editPass" type="submit"><fmt:message key="label.editPassword"/></button>
        </form>
    </div>

    <%--    </myTags:header>--%>
    <footer>footer</footer>
</div>


</body>
</html>
