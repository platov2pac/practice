<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<sec:authentication property="principal" var="authUser"/>
<body>
<spring:message code="label.newPassword" var="i18nNewPassword"/>
<div class="main">
    <myTags:header login="${authUser.username}"
                   mainLink="${contpath}welcome.jhtml"
                   userListLink="${contpath}listUsers.jhtml"
                   logoutLink="${contpath}logout.jhtml"
                   roles="${authUser.authorities}"/>
    <div class="content">
        <p class="app-description"><spring:message code="label.appDesc"
                                                   arguments="${authUser.username}"/></p>
        <form method="post" action="${contpath}editpassword.jhtml" id="form-edit-pass">
            <label>
                <input type="password" placeholder="${i18nNewPassword}" name="newPassword">
            </label>
            <button class="editPass" type="submit" name="sessionLogin" value="${authUser.username}"><spring:message
                    code="label.editPassword"/></button>
        </form>
    </div>

    <%--    </myTags:header>--%>
    <footer>footer</footer>
</div>


</body>
</html>
